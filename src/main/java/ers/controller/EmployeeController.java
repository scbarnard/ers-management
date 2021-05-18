package ers.controller;

import ers.model.Employee;
import ers.services.EmployeeServicesImpl;
import io.javalin.http.Context;

import java.util.List;
import ers.util.Logger;

public class EmployeeController {
    public String username = "";
    EmployeeServicesImpl service;

    /**
     *
     * @param service
     */
    public EmployeeController(EmployeeServicesImpl service){
        this.service = service;
    }

    /**
     *
     * @param ctx
     */
    public void jsonifyEmployeeInfo(Context ctx){
        Employee employee = service.getDao().getEmployee(username);
        ctx.json(employee);
    }

    /**
     *
     * @return String
     */
    public String getCurrentUser(){
        return this.username;
    }

    /**
     *
     * @param username
     */
    public void setCurrentUser(String username){
        this.username = username;
        Logger.logger.info("Employee " +username+ " has logged in.");
    }

    /**
     *
     * @return boolean
     */
    public boolean isEmployee(){
        if(this.username == null || this.username.equals("")){
            return false;
        }
        return true;
    }

    /**
     * Scrapes data from profile.html form and updates the user's profile data
     * @param ctx
     */
    public void updateEmployeeProfile(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String empID = ctx.formParam("empID");
        Employee emp = new Employee();
        emp.setPassword(password);
        emp.setUsername(username);
        emp.setEmpID(empID);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        service.getDao().updateEmployee(empID, emp);
        Logger.logger.info("Updated employee ID#" + empID);
        ctx.redirect("/profile.html");
    }

    /**
     * Sends a JSON object containing all employees in the DB
     * @param ctx
     */
    public void jsonifyAllEmployeeInfo(Context ctx){
        List<Employee> employees = service.getDao().getAllEmployeesList();
        ctx.json(employees);
    }
}
