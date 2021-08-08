const URL = "http://localhost:8080/project1/";

let loginButton = document.getElementById("loginButton");

loginButton.onclick = login;


function getUser(){
    let usernameInput = document.getElementById("username").value;
    let passwordInput = document.getElementById("password").value;

    let userInfo = {
        ersUsername: usernameInput,
        ersPassword: passwordInput
    }

    return userInfo;
}


// https://www.w3schools.com/jsref/met_loc_replace.asp
// this helped switch pages, either to employee or manager
async function login(){
    let user = getUser();

    let response = await fetch(URL + "userLogin",{
        method:'POST',
        body: JSON.stringify(user),
           // credentials: 'include'
    });

    if(response.status===200){
        console.log("successful log in");
        //document.location.href = 'userLandingScreen.html';
            let user2 = await response.json();
            console.log(user);
           // Once again, thanks to w3 schools for helping me with the login and cookies stuff <3
            document.cookie = `ersUsername=${user2.ersUsername}`;
            document.cookie = `userRole=${user2.userRole}`;
            console.log(getCookie("userRole"));
            if(getCookie("userRole") == 'employee'){
                window.location.replace("http://localhost:8080/static/userLandingScreen.html");
            } else if(getCookie("userRole") == 'manager'){
                window.location.replace("http://localhost:8080/static/reimburseManager.html");
            }
        } else {
            alert("login failed"); 
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


 