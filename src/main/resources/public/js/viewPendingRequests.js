/**
 * For assigning a to-be-resolved request a deciding manager. Value is stored in a hidden input
 * field on the form.
 */
const getManagerData = ()=> {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            document.getElementById("username").value =
                response.username;
        }
    };
    xhttp.open("POST", "/ManagerData", true);
    xhttp.send();
}

getManagerData();

/**
 * Retrieves all pending request data and builds a table OR displays a message
 * if there are no pending requests.
 */
const allPendingRequests = ()=> {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            let table = document.getElementById("pendingRequestsTable");
            if(response.length > 0){
                let data = Object.keys(response[0]);
                createTHead(table, data);
                createTable(table, response);
            } else {
                table.style.display = "none";
                let banner = document.createElement("h3");
                let banner_div = document.getElementById("banner");
                banner.innerText = "No pending requests to display";
                banner_div.append(banner);
            }
        }
    };
    xhttp.open("POST", "/PendingRequests", true);
    xhttp.send();
}

/**
 * Builds the head of the table using the request JSON data.
 * @param table
 * @param data
 */
const createTHead = (table, data)=> {
    let thead = table.createTHead();
    let row = thead.insertRow();
    for (let key of data) {
        let th = document.createElement("th");
        switch(key){
            case "requestID":
                key = "ID";
                break;
            case "purpose":
                key = "Purpose";
                break;
            case "expense":
                key = "Amount";
                break;
            case "status":
                key = "Status";
                break;
            case "resolvingManager":
                key = "Resolving Manager";
                break;
            case "user":
                key = "User";
                break;
        }
        let text = document.createTextNode(key);
        th.appendChild(text);
        row.appendChild(th);
    }
    let th = document.createElement("th");
    let text = document.createTextNode("Approve/Deny");
    th.appendChild(text);
    row.appendChild(th);
}

/**
 * Populates the table with the request JSON data.
 * @param table
 * @param data
 */
const createTable = (table, data) => {
    let index = 0;
    for (let record of data) {
        let row = table.insertRow();
        for (let key in record) {
            let cell = row.insertCell();
            let text = document.createTextNode(record[key]);
            if (key === "requestID") {
                let requestIDInput = document.createElement("input");
                requestIDInput.setAttribute("type", "hidden");
                requestIDInput.setAttribute("id", "requestID" + index);
                requestIDInput.setAttribute("name", "requestID" + index);
                requestIDInput.setAttribute("value", record[key]);
                cell.appendChild(requestIDInput);
            }
            cell.appendChild(text);
        }
        let btnCell = row.insertCell();

        let btnApprove = document.createElement("button");
        let btnDeny = document.createElement("button");

        btnApprove.setAttribute("class","btnAD");
        btnDeny.setAttribute("class", "btnAD");
        btnApprove.innerHTML= "Approve";
        btnDeny.innerHTML = "Deny";

        btnApprove.setAttribute("id", "btnApprove"+index);
        btnDeny.setAttribute("id", "btnDeny"+index);

        btnApprove.setAttribute("onclick", "approve(id)");
        btnDeny.setAttribute("onclick", "deny(id)");

        btnCell.append(btnApprove);
        btnCell.append(btnDeny);

        row.setAttribute("id", "index"+index);
        index++;
    }
}

/**
 * Function to approve a request.
 * @param id
 */
const approve = (id) =>{
    let index = id.replace("btnApprove","");
    let approvedReq = document.getElementById("requestID"+index).value;
    document.getElementById("approvedReq").value = approvedReq;

}

/**
 * Function to deny a request.
 * @param id
 */
const deny = (id) =>{
    let index = id.replace("btnDeny","");
    let deniedReq = document.getElementById("requestID"+index).value;
    document.getElementById("deniedReq").value = deniedReq;
}

allPendingRequests();