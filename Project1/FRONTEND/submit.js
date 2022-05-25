

document.getElementById("logoutbutton").addEventListener("click", Logout);

function Logout(){
    LocalStorage.removeItem("current-user");
    window.Location=href="./login.html";

}

document.getElementById("submit-button").addEventListener("click", submitReimbursement);




