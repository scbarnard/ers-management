package ers.view;

import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class NewRequest implements View{

    private Javalin javalin;
    private HashMap<String,Object> data;

    public NewRequest(Javalin javalinApp, HashMap<String, Object> database)
    {
        javalin = javalinApp;
        data = database;
    }

    @Override
    public void doPage() {
        javalin.get("/employee/newRequest", ctx -> {
           ctx.render("/public/newRequest.html"); });

        javalin.post("/employee/newRequest", ctx -> {
            ((ReimbursementRequestController) data.get("ReimbursementRequestController")).createReimbursementRequest(ctx);
        });
    }
}
