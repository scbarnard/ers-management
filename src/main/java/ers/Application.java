package ers;

import ers.constants.Constants;
import ers.controller.EmployeeController;
import ers.controller.LoginController;
import ers.controller.ManagerController;
import ers.controller.ReimbursementRequestController;
import ers.dao.EmployeeDaoImpl;
import ers.dao.ManagerDaoImpl;
import ers.dao.ReimbursementRequestDaoImpl;
import ers.services.*;
import ers.view.*;
import io.javalin.Javalin;
import ers.util.AbstractApplication;

import java.util.HashMap;

public class Application extends AbstractApplication {

    private Javalin javalin;
    private HashMap<String, Object> data = new HashMap<>();

    public Application(Javalin javalin){
        this.javalin = javalin;
        init();
    }

    @Override
    public void init() {
        //DAOS
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(Constants.DB);
        ManagerDaoImpl managerDao = new ManagerDaoImpl(Constants.DB);
        ReimbursementRequestDaoImpl reimbursementRequestDao = new ReimbursementRequestDaoImpl(Constants.DB);

        //Services
        EmployeeServicesImpl employeeServices = new EmployeeServicesImpl(employeeDao);
        ManagerServicesImpl managerServices = new ManagerServicesImpl(managerDao);
        ReimbursementRequestServicesImpl reimbursementRequestServices = new ReimbursementRequestServicesImpl(reimbursementRequestDao);
        LoginServiceImpl loginService = new LoginServiceImpl(employeeDao, managerDao);

        //Controllers
        ManagerController managerController = new ManagerController(managerServices);
        EmployeeController employeeController = new EmployeeController(employeeServices);
        LoginController loginController = new LoginController(loginService, employeeController, managerController);
        ReimbursementRequestController reimbursementRequestController = new ReimbursementRequestController(reimbursementRequestServices, employeeController);


        //Load Components
        data.put("employeeDao", employeeDao);
        data.put("managerDao", managerDao);
        data.put("reimbursementRequestDao", reimbursementRequestDao);

        data.put("employeeServices", employeeServices);
        data.put("managerServices", managerServices);
        data.put("reimbursementRequestServices", reimbursementRequestServices);
        data.put("loginServices", loginService);

        data.put("LoginController", loginController);
        data.put("EmployeeController", employeeController);
        data.put("ManagerController", managerController);
        data.put("ReimbursementRequestController", reimbursementRequestController);
    }

    @Override
    public void run() {
        (new Login(javalin, data)).doPage();
        (new Data(javalin, data)).doPage();
        (new NewRequest(javalin, data)).doPage();
        (new RequestHistory(javalin, data)).doPage();
        (new UpdateProfile(javalin, data)).doPage();
        (new ViewEmployees(javalin, data)).doPage();
        (new ResolveRequest(javalin, data)).doPage();
        (new ResolvedRequests(javalin, data)).doPage();
        (new PendingRequests(javalin, data)).doPage();
        (new RequestsByEmployee(javalin, data)).doPage();
    }

    @Override
    public void exit() {
        javalin.stop();
    }
}
