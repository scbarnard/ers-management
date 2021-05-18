package ers.view;
import ers.controller.EmployeeController;
import io.javalin.Javalin;
import java.util.HashMap;

public class ViewEmployees implements View {
    private Javalin javalin;
    private HashMap<String,Object> data;

    public ViewEmployees(Javalin javalin, HashMap<String, Object> data) {
        this.javalin = javalin;
        this.data = data;
    }

    @Override
    public void doPage() {
        javalin.get("/AllEmployeesData", ctx -> {
            ctx.render("allEmployees.html");
        });

        javalin.post("/AllEmployeesData", ctx->{
            ((EmployeeController) data.get("EmployeeController")).jsonifyAllEmployeeInfo(ctx);
        });
    }
}
