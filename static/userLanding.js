const URL = 'http://localhost:8080/project1/';

let getAllReimbursementsButton = document.getElementById("getAllReimbursementsButton");
let logoutButton = document.getElementById("logoutEmployeeButton");
let newReimbursementButton = document.getElementById("newReimbursementButton");


getAllReimbursementsButton.onclick = getAllEmployeeReimbursements;
logoutButton.onclick = logout;
newReimbursementButton.onclick = newReimbursement;

async function getAllEmployeeReimbursements(id){
    let response = await fetch(URL + 'employee/' + id);

    if(response.status === 200){
        let reimbs = await response.json();
        populateEmployeeTable(reimbs);
    }else{
        console.log("error");
    }
}

function populateEmployeeTable(data){
    let body = document.getElementById("employeeTicketsTableBody");

    body.innerHTML="";

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

        body.appendChild(row);
    }
}

async function logout(){
    let response = await fetch(URL + "logout");
    document.location.href = "index.html";
}

async function getReimbInfo(){
    let amount = document.getElementById("reimbAmount");
    let type = document.getElementById("reimbType");
    let desc = document.getElementById("reimbDescription");
    let author = getCookie("ersUsername");

    let newReimb = {
        reimbAmount: amount,
        reimbTypeId: type,
        reimbDescription: desc,
        reimbAuthor: author
    }

    return newReimb;
}

async function newReimbursement(){
    let reimb = getReimbInfo();

    let response = await fetch(URL + "reimbursement", {
        method:'POST',
        body: JSON.stringify(reimb),
        credentials: 'include'
    });

    if(response.status === 201){
        document.getElementById("reimbAmount").value;
        document.getElementById("reimbType").value;
        document.getElementById("reimbDescription").value;
        findAllReimbursements();

        console.log("created new reimbursement");
    } else {
        console.log("something went wrong");
    }
}





//did this so user can be persistent through pages,
//thank to ----- https://www.w3schools.com/js/js_cookies.asp
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
