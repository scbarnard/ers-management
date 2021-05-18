/**
 * Retrieves and JSON object from the server containing all resolved requests
 * and builds a table on the page with the results.
 */

const allResolvedRequests = ()=> {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            let table = document.getElementById("resolvedRequestsTable");
            let data = Object.keys(response[0]);
            createTHead(table, data);
            createTable(table, response);
        }
    };
    xhttp.open("POST", "/ResolvedRequests", true);
    xhttp.send();
}

/**
 * Takes in a table on the page and JSON data to build the head of the table.
 * @param table
 * @param data
 */
const createTHead = (table, data)=> {
    let thead = table.createTHead();
    let row = thead.insertRow();
    for (let key of data) {
        let th = document.createElement("th");
        switch (key) {
            case "user":
                key = "User";
                break;
            case "purpose":
                key = "Purpose";
                break;
            case "expense":
                key = "Expense";
                break;
            case "resolvingManager":
                key = "Resolving Manager";
                break;
            case "status":
                key = "Status";
                break;
            default:
                key = "";
                break;
        }
        if (key !== "") {
            let text = document.createTextNode(key);
            th.appendChild(text);
            row.appendChild(th);
        }
    }
}

/**
 * Takes in a table on the page and the JSON data to fill in the table on the page.
 * @param table
 * @param data
 */
const createTable = (table, data) => {
    for (let record of data) {
        let row = table.insertRow();
        for (let key in record) {
            if(key === "user" || key === "purpose" || key === "expense" || key === "resolvingManager" || key === "status") {
                let cell = row.insertCell();
                let text = document.createTextNode(record[key]);
                cell.appendChild(text);
            }
        }
    }
}

allResolvedRequests();