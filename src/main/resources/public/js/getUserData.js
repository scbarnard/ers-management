/**
 * Sends an XMLHttpRequest to the javalin server to get the current logged-in user
 */
function getUserData() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            document.getElementById("username").value =
                response.username;
        }
    };
    xhttp.open("POST", "/UserData", true);
    xhttp.send();
}

getUserData();