package ers.view;

import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class ResolveRequest implements View{

    private Javalin javalin;
    private HashMap<String,Object> data;

    public ResolveRequest(Javalin javalin, HashMap<String, Object> data){
        this.javalin = javalin;
        this.data = data;
    }
    @Override
    public void doPage() {
        javalin.get("/ResolveRequest", ctx -> {
            ctx.render("/allPending.html"); });

        javalin.post("/ResolveRequest", ctx -> {
            ((ReimbursementRequestController) data.get("ReimbursementRequestController")).updateReimbursementRequest(ctx);
        });
    }
}
