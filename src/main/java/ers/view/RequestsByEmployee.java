package ers.view;

import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class RequestsByEmployee implements View{
    private Javalin javalin;
    private HashMap<String,Object> data;

    public RequestsByEmployee(Javalin javalin, HashMap<String, Object> database)
    {
        this.javalin = javalin;
        this.data = database;
    }

    @Override
    public void doPage() {
        javalin.get("/RequestsByEmployee", ctx -> {
            ctx.render("/requestsByEmployee.html"); });

        javalin.post("/RequestsByEmployee", ctx -> {
            ((ReimbursementRequestController) data.get("ReimbursementRequestController")).getRequestsByEmployee(ctx);
        });
    }
}
