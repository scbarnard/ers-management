package ers.view;

import ers.controller.LoginController;
import ers.util.Logger;
import io.javalin.Javalin;

import java.util.HashMap;

public class Login implements View{
    private Javalin javalin;
    private HashMap<String, Object> data;

    public Login(Javalin javalinApp, HashMap<String,Object> data) {
        javalin = javalinApp;
        this.data = data;
    }

    @Override
    public void doPage() {
        javalin.get("/index", ctx -> {
            ctx.render("/public/index.html");
        });

        javalin.post("/LoginCheck", ctx -> {
            Logger.logger.info(ctx.body());
            ((LoginController) data.get("LoginController")).userLogin(ctx);
        });
    }
}
