/**
 * Retrieves and JSON object from the server containing all employees and builds a table on the page
 * with the results.
 */
const allEmployees = ()=> {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
            let table = document.getElementById("employeesTable");
            let data = Object.keys(response[0]);
            createTHead(table, data);
            createTable(table, response);
        }
    };
    xhttp.open("POST", "/AllEmployeesData", true);
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
            case "firstName":
                key = "First Name";
                break;
            case "lastName":
                key = "Last Name";
                break;
            case "username":
                key = "Username";
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
            if (key === "firstName" || key === "lastName" || key === "username"){
            let cell = row.insertCell();
            let text = document.createTextNode(record[key]);
            cell.appendChild(text);
            }
        }
    }
}

allEmployees();