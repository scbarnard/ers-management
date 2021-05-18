package ers.view;

import ers.controller.EmployeeController;
import io.javalin.Javalin;
import java.util.HashMap;

public class UpdateProfile implements View{
    private Javalin javalin;
    private HashMap<String,Object> data;

    public UpdateProfile(Javalin javalin, HashMap<String, Object> data){
        this.javalin = javalin;
        this.data = data;
    }
    @Override
    public void doPage() {
        javalin.get("/UpdateProfile", ctx -> {
            ctx.render("profile.html");
        });


        javalin.post("/UpdateProfile", ctx ->{
            ((EmployeeController) data.get("EmployeeController")).updateEmployeeProfile(ctx);
        });
    }
}
