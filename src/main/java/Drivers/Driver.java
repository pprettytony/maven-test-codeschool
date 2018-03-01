package Drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;

public class Driver {


    public WebDriver setupDriver(Capabilities capabilities){
        switch (capabilities.getBrowserName()){
            case "chrome":
                ChromeDriverService serviceChrome = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src\\main\\resources\\chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("start-maximized");
                //options.setCapability(ChromeOptions.CAPABILITY, capabilities);
                //options.merge(capabilities);
                return new ChromeDriver(serviceChrome, optionsChrome);

            case "opera":
                //System.setProperty("webdriver.opera.driver", "src\\main\\resources\\operadriver.exe");
                OperaDriverService serviceOpera = new OperaDriverService.Builder()
                        .usingDriverExecutable(new File("src\\main\\resources\\operadriver.exe"))
                        .usingAnyFreePort()
                        .build();
                OperaOptions optionsOpera = new OperaOptions();
                //optionsOpera.setCapability("opera.binary", "src\\main\\resources\\operadriver.exe");
                optionsOpera.setBinary("src\\main\\resources\\operadriver.exe");
                //optionsChrome.addArguments();
                return new OperaDriver(serviceOpera, optionsOpera);
        }
        return null;
    }
}
