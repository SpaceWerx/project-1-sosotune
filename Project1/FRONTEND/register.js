document.getElementById("loginButton").addEventListener("click", registeruser);

async function registeruser(){

let usern = document.getElementById("username").value;
let userp = document.getElementById("password").value;
let userr = document.getElementById("typeInput").value;



let user = {
    "username" : usern,
    "password": userp,
    "role" : userr
    
};

console.log(user);
let response = await fetch("http://localhost:3000/register", {
    method: "POST",
    body: JSON.stringify(user),
    credentials:"include"
});
console.log(response.status);

}