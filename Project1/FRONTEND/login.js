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
    
   

    
    
    if(response.status === 202){
        window.location.replace("C:\Users\tslxg\OneDrive\Desktop\Project1\project-1-sosotune\Project1\FRONTEND\manager.html");
        
        
    } else{
        document.getElementById("input-group mb-3").innerText = "Login Failed, please refresh the page!";
    }

    
}