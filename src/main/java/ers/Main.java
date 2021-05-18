package ers;

import ers.constants.Constants;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin javalinApp = Javalin.create(config ->{
            config.enableCorsForAllOrigins();
            config.addStaticFiles("/public");

        }).start(7777);

        Application app = new Application(javalinApp);
        app.run();
    }
}
