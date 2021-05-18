package ers.view;

import ers.controller.ReimbursementRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class RequestHistory implements View{
    private Javalin javalin;
    private HashMap<String,Object> data;

    public RequestHistory(Javalin javalin, HashMap<String, Object> database)
    {
        this.javalin = javalin;
        this.data = database;
    }


    @Override
    public void doPage() {
        javalin.get("/employee/viewRequestHistory", ctx -> {
            ctx.render("/public/viewRequestHistory.html"); });

        javalin.post("/employee/viewRequestHistory", ctx -> {
            ctx.render("/public/viewRequestHistory.html");
        });
    }
}
