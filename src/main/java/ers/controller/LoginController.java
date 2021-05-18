package ers.controller;


import ers.constants.Constants;
import ers.services.LoginServiceImpl;
import io.javalin.http.Context;
import ers.util.Logger;
import org.json.JSONObject;


public class LoginController{

    LoginServiceImpl service;
    EmployeeController empController;
    ManagerController manController;

    public LoginController(LoginServiceImpl loginService, EmployeeController empController, ManagerController manController) {
        this.service = loginService;
        this.empController = empController;
        this.manController = manController;
    }

    /**
     * Returns a JSON object when a manager logs in - for ReactJS version
     * @param username
     * @return JSONObject
     */
    public JSONObject managerJSON(String username){
        JSONObject managerResponse = new JSONObject();
        managerResponse.put("username", username);
        managerResponse.put("role", Constants.MANAGER);
        return managerResponse;
    }

    /**
     * Returns a JSON object when a employee logs in - for ReactJS version
     * @param username
     * @return
     */
    public JSONObject employeeJSON(String username){
        JSONObject employeeResponse = new JSONObject();
        employeeResponse.put("username", username);
        employeeResponse.put("role", Constants.EMPLOYEE);
        return employeeResponse;
    }

    /**
     * Handles any user logging into the system, be it employee or manager.
     * @param ctx
     */
    public void userLogin(Context ctx){
        //For ReactJS
//        JSONObject req_obj = new JSONObject(ctx.body());
//        JSONObject res_obj;
//        String username = req_obj.getString("username");
//        String password = req_obj.getString("password");


//        //For HTML + JS
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        Logger.logger.info("New login attempt for username: " + username);
        int result = service.userLoginCheck(username, password);
        switch(result){
            case 0:

                manController.setManager(username);

                // for ReactJS
//                res_obj = managerJSON(username);
//                ctx.result(String.valueOf(res_obj));

                //for HTML + JS
                ctx.render("/public/managerHome.html");

                break;
            case 1:

                empController.setCurrentUser(username);

                // For ReactJS
//                res_obj = employeeJSON(username);
//                ctx.result(String.valueOf(res_obj));

                //for HTML + JS
                ctx.render("public/employeeHome.html");
                break;

            case -1:
            default:
                ctx.render("/public/index.html");
                break;
        }
    }
}
