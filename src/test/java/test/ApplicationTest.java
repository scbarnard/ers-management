package test;
import ers.Application;
import io.javalin.Javalin;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    static Javalin javalin = Javalin.create(config -> {}).start(7001);
    static Application app = new Application(javalin);


    @Test
    public void shoudldDoRun(){
        app.run();
    }

    @Test
    public void shouldExit(){
        app.exit();
    }
}
