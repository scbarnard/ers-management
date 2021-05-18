package ers.view;

import ers.controller.EmployeeController;
import ers.controller.ManagerController;
import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class Data implements View{
    private Javalin javalin;
    private HashMap<String,Object> data;

    public Data(Javalin javalin, HashMap<String, Object> data){
        this.javalin = javalin;
        this.data = data;
    }

    /**
     *
     */
    @Override
    public void doPage() {
        javalin.post("/UserData", ctx -> {
            ((EmployeeController) data.get("EmployeeController")).jsonifyEmployeeInfo(ctx);
        });

        javalin.post("/RequestData", ctx ->{
            ((ReimbursementRequestController) data.get("ReimbursementRequestController")).getReimbursementRequest(ctx);
        });

        javalin.post("/ManagerData", ctx ->{
            ((ManagerController) data.get("ManagerController")).jsonifyManagerInfo(ctx);
        });
    }
}
