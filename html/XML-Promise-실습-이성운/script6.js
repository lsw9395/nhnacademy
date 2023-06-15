window.addEventListener("DOMContentLoaded",function(){
    const loginForm = document.getElementById("loginFormContainer");
    const loginValidate = function(form){
        if(form['id'].value.trim() ==""){
            alert("아이디가 비어있습니다!");
            form['id'].focus();
            return false;
        }

        if(form['password'].value.trim()==""){
            alert("비밀번호가 비어있습니다!");
            form['password'].focus();
            return false;
        }
    };
    window.addEventListener("submit",function(event){
        event.preventDefault();
        if(loginValidate(event.target)== false){
            return ;
        }
        const userId = event.target["id"].value;
        const userPassword = event.target["password"].value;
        doLogin(userId,userPassword).then((user)=>{
            //처음에 doLogin에서 promise로 post로 아이디 비번 확인 후 같으면 resolve-> then/ 다르면 reject ->catch
            const loginFromContainer = document.getElementById("loginFormContainer");
            const loginSuccessContainer = document.getElementById("loginSuccessContainer");
            loginFromContainer.setAttribute("style","display:none");
            loginSuccessContainer.setAttribute("style","display:block");
            const id = document.getElementById("userId");
            const name = document.getElementById("userName");
            const cartId = document.getElementById("cartID");
            id.innerText = user.userId;
            name.innerText = user.userName;
            cartId.innerText = user.cartId;
            return user;// 다음 then에 넘겨줄 user 변수 return
        }).catch(e=>{
            alert("로그인을 다시 확인해주세요.");
        }).then((user)=>{
            return getList(user.userId,user.cartId); // api 연결에 성공하면 resolve->then인데 return으로 데이터를 넘겨서 다음 then으로 이동/ 실패->catch;
        }).catch(e=>{
            alert(e.message);
        }).then((items)=>{
            const cartable = document.getElementById("cartTable");
            const tbody = cartable.getElementsByTagName("tbody")[0];
            for(const item of items){//2번째then에서 넘겨받은 데이터를 가지고 테이블 데이터 채우기.
                const tr = document.createElement("tr");
                const td1 = document.createElement("td");
                const td2 = document.createElement("td");
                const td3 = document.createElement("td");
                const td4 = document.createElement("td");
                const td5 = document.createElement("td");
                td1.innerText = item.productId;
                td2.innerText = item.name;
                td3.innerText = item.price;
                td4.innerText = item.amount;
                td5.innerText = item.totalPrice;
                tr.append(td1,td2,td3,td4,td5);
                tbody.append(tr);
            }
        })
    })
    function doLogin(userId, userPassword){
        return new Promise(function(resolve,reject){
            const url ="http://133.186.144.236:8100/api/users/login";
            const user={
                "userId" : userId,
                "userPassword" : userPassword
            }
            const xhr = new XMLHttpRequest();
            xhr.open("POST",url);
            xhr.setRequestHeader("content-type","application/json");
            //xhr.responseType= "json";
            xhr.send(JSON.stringify(user));
            xhr.addEventListener("load",function(){
                if(this.status===200){
                    console.log(this.response.cartId);
                    const user2 = JSON.parse(this.responseText);

                    resolve(user2);
                }
                else {
                    reject(new Error("login error"));
                }
            });
            xhr.addEventListener("error",function(){
                console.log("network error");
            })
        })
    }
    function getList(userId, cartId){
        return new Promise(function(resolve, reject){
            const url = "http://133.186.144.236:8100/api/nhnmart/shopping-cart/"+cartId;
            const xhr = new XMLHttpRequest();
            xhr.open("GET",url);
            xhr.setRequestHeader("X-USER-ID",userId)
            xhr.setRequestHeader("content-type","application/json");
            xhr.send('');
            xhr.addEventListener("load",function(){
                if(this.status==200){
                    const items = JSON.parse(this.responseText);
                    resolve(items);
                }else {
                    reject(new Error("데이터를 받아오지 못했습니다."));
                }
            });
            xhr.addEventListener("error",function(){
                console.log("network error");
            })
        })
    }
})