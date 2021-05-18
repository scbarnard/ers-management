/**
 * Unpacks the JSON response object and assigns to respective form field
 */
const getProfileData = ()=> {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            document.getElementById("empID").value = response.empID;
            document.getElementById("username").value = response.username;
            document.getElementById("password").value = response.password;
            document.getElementById("firstName").value = response.firstName;
            document.getElementById("lastName").value = response.lastName;
        }
    };
    xhttp.open("POST", "/UserData", true);
    xhttp.send();
}

getProfileData();