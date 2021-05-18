package ers.services;

import ers.dao.EmployeeDaoImpl;
import ers.dao.ManagerDaoImpl;
import ers.model.Employee;
import ers.model.Manager;

public class LoginServiceImpl implements LoginService{
    private EmployeeDaoImpl employees;
    private ManagerDaoImpl managers;

    public LoginServiceImpl(EmployeeDaoImpl employees, ManagerDaoImpl managers){
        this.employees = employees;
        this.managers = managers;

    }

    /**
     * Checks the given username and password across both manager and employee collections
     * and sets the isUser field to an in integer based on the result of the search.
     * @param username
     * @param password
     * @return
     */
    @Override
    public int userLoginCheck(String username, String password) {
        int isUser = -1;
        for (Manager manager : managers.getAllManagers().find()) {
            if(username.equals(manager.getUsername()) && password.equals(manager.getPassword())){
                isUser = 0;
            }
            if (isUser == -1) {
                for (Employee employee : employees.getAllEmployeesCollection().find()) {
                    if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
                        isUser = 1;
                    }
                }
            }
        }
        return isUser;
    }
}
