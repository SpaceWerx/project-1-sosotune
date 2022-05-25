const url = "http://localhost:3000/";
document.getElementById("loginButton").addEventListener("click", login);

async function login(){
    let usern = document.getElementById("Username").value;
    let userp = document.getElementById("Password").value;

    let user = {
        username:usern,
        password:userp
    }
    
    console.log(user);
    let response = await fetch(url+ "login", {
        method:"POST",
        body: JSON.stringify(user),
        credentials:"include"
    });

    console.log(response.status);
    
   

    if(response.status === 201){
        document.getElementById("input-group mb-3").innerText = "Login successful";
    }
    
    else{
        document.getElementById("input-group mb-3").innerText = "Login Failed, please refresh the page!";
    }

    
}