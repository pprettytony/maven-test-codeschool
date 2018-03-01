import Managers.AppManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase{
    AppManager app;

    @BeforeClass
    public void init(){
        app = new AppManager();
    }

    @AfterClass
    public void tearDown(){
        app.stop();
    }

}
