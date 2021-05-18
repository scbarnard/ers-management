package ers.view;

import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class PendingRequests implements View{
    private Javalin javalin;
    private HashMap<String,Object> data;

    public PendingRequests(Javalin javalin, HashMap<String, Object> database)
    {
        this.javalin = javalin;
        this.data = database;
    }

    @Override
    public void doPage() {
        javalin.get("/PendingRequests", ctx -> {
            ctx.render("allPending.html");
        });

        javalin.post("/PendingRequests", ctx ->{
            ((ReimbursementRequestController) data.get("ReimbursementRequestController")).getAllPendingRequests(ctx);
        });
    }
}
