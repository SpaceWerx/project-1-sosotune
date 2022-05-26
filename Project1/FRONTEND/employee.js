
document.getElementById("getreimbursements").addEventListener("click",getreimbursmentsbyid);
//creates function to get reimbursement, user id is save on login to pull their reimbursements
async function getreimbursmentsbyid(){
    let response = await fetch("http://localhost:3000/usersreimbursement", { 
        method:'GET',
        credentials:"include"
    });
    console.log(response);
    if(response.status ===200){
        let data = await response.json();
        console.log(data);
        for(let reimbursement of data){
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell); 
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.type;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.description;
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.amount;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.status;
            row.appendChild(cell5);
            document.getElementById("reimbursementbody").appendChild(row);
        }
    }
}