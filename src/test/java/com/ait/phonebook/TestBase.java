package com.ait.phonebook;

import com.ait.phonebook.framework.AppManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static AppManager app = new AppManager(System.
            //дефолтный браузер для системы:
            getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite //аннотация, чтобы каждый раз не открывалось новое окно при запуске нового теста
    public void setUp() {
        app.init();
    }

    @AfterSuite //(enabled = false)- чтобы все окна закрывались
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p){

        logger.info("Start test " + m.getName()
                + " with data: " + Arrays.asList(p));
    }
    @AfterMethod
    public void stopTest(ITestResult result){
        if (result.isSuccess()){
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: "
                    + result.getMethod().getMethodName()
                    + "Screenshot: "
                    + app.getUser()
                    .takeScreenShot());
        }
        logger.info("Stop test");
        logger.info("************     ***********    *************");
    }
}
