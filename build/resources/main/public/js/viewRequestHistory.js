/**
 * Retrieves and JSON object from the server containing all reimbursement requests
 * and builds a table on the page with the results.
 */
const history = ()=> {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            let table = document.getElementById("requestHistoryTable");
            let data = Object.keys(response[0]);
            createTHead(table, data);
            createTable(table, response);
        }
    };
    xhttp.open("POST", "/RequestData", true);
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
            let cell = row.insertCell();
            let text = document.createTextNode(record[key]);
            if (record.status === 'PENDING'){ row.setAttribute("name","PENDING"); }
            else if (record.status === "DENIED") { row.setAttribute("name","DENIED"); }
            else { row.setAttribute("name", "APPROVED"); }
            cell.appendChild(text);
        }
    }
}

history();