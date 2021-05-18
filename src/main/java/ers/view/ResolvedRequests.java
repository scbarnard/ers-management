package ers.view;

import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class ResolvedRequests implements View {
    private Javalin javalin;
    private HashMap<String,Object> data;

    public ResolvedRequests(Javalin javalin, HashMap<String, Object> database)
    {
        this.javalin = javalin;
        this.data = database;
    }

    @Override
    public void doPage() {
        javalin.get("/ResolvedRequests", ctx -> {
            ctx.render("/resolvedRequests.html"); });

        javalin.post("/ResolvedRequests", ctx -> {
            ((ReimbursementRequestController) data.get("ReimbursementRequestController")).getAllResolvedRequests(ctx);
        });
    }
}
