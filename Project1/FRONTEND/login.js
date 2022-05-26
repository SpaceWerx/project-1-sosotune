const url = "http://localhost:3000/";
document.getElementById("login-anchor").addEventListener("click", login);

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
    
    if(response.status === 201){
        window.location.href="employee.html";
        window.location.href;
    }
    
}