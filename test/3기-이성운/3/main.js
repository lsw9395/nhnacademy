window.addEventListener("DOMContentLoaded",function(){
    const formContainer = document.getElementById("formId");
    const validateFrom = async function(form){
        const userId = document.getElementById("userId");
        const nameId = document.getElementById("nameId");
        const password1Id = document.getElementById("password1Id");
        const passwrod2Id = document.getElementById("password2Id");
        console.log("11");
        if(form['userId'].value.trim() ==""){
            alert("아이디가 비어있습니다!");
            form['userId'].focus();
            return false;
        }
        if(form['nameId'].value.trim() ==""){
            alert("이름이 비어있습니다!");
            form['userId'].focus();
            return false;
        }
        if(form['password1Id'].value.trim()==""){
            alert("비밀번호1가 비어있습니다!");
            form['password1Id'].focus();
            return false;
        }
        if(form['password2Id'].value.trim()==""){
            alert("비밀번호2가 비어있습니다!");
            form['password2Id'].focus();
            return false;
        }
        if(form['password1Id'].value.trim()!=form['password2Id'].value.trim()){
            alert("비밀번호1과 비밀번호2가 다릅니다.");
            return false;
        }
        console.log("22");
        const url = "http://133.186.144.236:8100/api/users/"+userId.value+"/exist";
        const option={
            method:"POST",
            headers:{
                "content-type":"application/json"
            }
        }
        const result = await fetch(url,option);
        const response = await result.json();

        console.log("33");
        if(response.result){
            alert("중복된 아이디입니다.");
            return false;
        }
    }

    async function signIn(user, userName,userPassword){
        const url = "http://133.186.144.236:8100/api/users";
        const userdata={
            "userId":user,
            "userName":userName,
            "userPassword":userPassword
        };
        const option ={
            method:"POST",
            headers:{
                "content-type":"application/json"
            },
            body : JSON.stringify(userdata)
        }
        const result = await fetch(url,option);
    }

    formContainer.addEventListener("submit",async function(event){
        event.preventDefault();
        if(await validateFrom(event.target)== false ){
            return ;
        }

        const userId = document.getElementById("userId");
        const nameId = document.getElementById("nameId");
        const password1Id = document.getElementById("password1Id");

        const user = userId.value;
        const userName = nameId.value;
        const userPassword = password1Id.value;

        await signIn(user,userName,userPassword);
    })





})