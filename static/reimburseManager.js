const URL = 'http://localhost:8080/project1/';

let getReimbs = document.getElementById("getReimbs");

getReimbs.onclick = getAllReimbursements;

let userData;


async function getAllReimbursements(id){
    let response = await fetch(URL + 'manager');

    if(response.status === 200){
        let reimbs = await response.json();
        populateManagerTable(reimbs, id);
    }else{
        console.log("error");
    }
}

function populateManagerTable(data, managerID) {
    let body = document.getElementById("ticketManagerBody");

    body.innerHTML = "";

    for (let ticket of data) {
        let row = document.createElement("tr");

        for(let reimb of data){
            let row = document.createElement("tr");
            console.log(reimb);
    
            let reimbID = document.createElement("td");
            reimbID.innerText = reimb["reimbId"];
            row.appendChild(reimbID);
    
            let reimbAmount = document.createElement("td");
            reimbAmount.innerText = "$" + reimb["reimbAmount"];
            row.appendChild(reimbAmount);
    
            let reimbStatusId = document.createElement("td");
            reimbStatusId.innerText = reimb["reimbStatusId"];
            row.appendChild(reimbStatusId);
    
            let reimbDescription = document.createElement("td");
            reimbDescription.innerText = reimb["reimbDescription"];
            row.appendChild(reimbDescription);
    
            let reimbAuthor = document.createElement("td");
            reimbAuthor.innerText = reimb["reimbAuthor"];
            row.appendChild(reimbAuthor);


            let reimbResolver = document.createElement("td");
            if (reimb["reimbResolver"] == null) {
                reimbResolver.innerText = "Waiting on Approval";
            } else {
                reimbResolver.innerText = reimb["reimbResolver"];
            }
            row.appendChild(reimbResolver);
    
            let reimbTypeId = document.createElement("td");
            reimbTypeId.innerText = reimb["reimbTypeId"];
            row.appendChild(reimbTypeId);
        }
    }
}
