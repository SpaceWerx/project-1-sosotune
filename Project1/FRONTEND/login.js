const url = "http://localhost:3000/";
document.getElementById("loginButton").addEventListener("click", login);
//login function authenticates with backend to verify user exists in database and password matches
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
        window.location.href="employee.html";
        window.location.href;
    }
    if(response.status === 202){
        window.location.href="manager.html";
        window.location.href;
    }
    
    
}
// const loginForm = document.getElementById("display-form");
// const loginButton = document.getElementById("loginbutton");
// loginButton.addEventListener("click", async (login) => {

//     login.preventDefault();
//     let usern = loginForm.username.value;
//     let userp = loginForm.password.value;

//     let user = {
//         username:usern,
//         password:userp
//     }
//     console.log(user);
//     let response = await fetch(url+ "login", {
//         method:"POST",
//         body: JSON.stringify(user),
//         credentials:"include"
//     });
    
//     console.log(response.status);
//     if(response.status===201){
//         alert("sucessfully logged in");
//         window.location.href("employee.html");
//     } else if(response.status===202){
//         alert("sucessfully logged in");
//         window.location.href("employee.html");
//     }else{
//         alert("username or password incorrect");
//     }
    



// });