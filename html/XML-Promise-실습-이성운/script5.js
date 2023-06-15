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
    function getList(userId, cartId, display){
        const url = "http://133.186.144.236:8100/api/nhnmart/shopping-cart/"+cartId;
        const xhr = new XMLHttpRequest();
        xhr.open("GET",url);
        xhr.setRequestHeader("X-USER-ID",userId)
        xhr.setRequestHeader("content-type","application/json");
        xhr.send('');
        xhr.addEventListener("load",function(){
            if(this.status==200){
                const items = JSON.parse(this.responseText);
                display(items);
            } else{
                alert("잘못된 접근입니다.");
            }
        });
        xhr.addEventListener("error",function(){
            console.log("network error");
        })
    }
    window.addEventListener("submit",function(event){
        event.preventDefault();
        if(loginValidate(event.target)==false){
            return ;
        }
        const userId = event.target["id"].value;
        const userPassword = event.target["password"].value;

        doLogin(userId, userPassword, function(user){//user<<는 doLogin에서 연결에 성공해서 넘겨 받은 유저 데이터임.
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
                getList(user.userId,user.cartId,function(items){//itms<<는 getList에서 연결에 성공해서 넘겨 받은 장바구니 데이터임.
                    const cartable = document.getElementById("cartTable");
                    const tbody = cartable.getElementsByTagName("tbody")[0];
                    for(const item of items){
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
        });
    });
    function doLogin(userId,userPassword,loginSuccess){
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
                loginSuccess(user2);
            }
            else {
                console.log("error");
            }
        });
        xhr.addEventListener("error",function(){
            console.log("network error");
        })
    }
});