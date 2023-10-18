package com.ait.phonebook.framework;

import com.google.common.io.Files;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class HelperBase {

    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    // предыдущий метод - захардкоженый и работает ТОЛЬКО в данном случае
    // мы же создаем уникальный метод для поиска уникальных элементов
    // на главной странице приложения.
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

    public boolean isElementPresent2(By locator) {
        try {
            driver.findElements(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String takeScreenShot() throws RuntimeException {
        File temporal = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File screenshot = new File(
                "screenshot/screen"
                + (System.currentTimeMillis() / 1000) % 3600
                + ".png");
        try {
            Files.copy(temporal, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //дает возможность распечатать скриншот:
        return screenshot.getAbsolutePath();
    }
    private ScreenRecorder screenRecorder;

    public void startRecording() throws IOException, AWTException {
        File file = new File("record");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0,width,height);

        GraphicsConfiguration graphConfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder =
                new Recorder (
                        graphConfig,
                        captureSize,
                        new Format(
                            MediaTypeKey,
                            FormatKeys.MediaType.FILE,
                            MimeTypeKey,
                            MIME_AVI),
                        new Format(
                            MediaTypeKey,
                            FormatKeys.MediaType.VIDEO,
                            EncodingKey,
                            ENCODING_AVI_MJPG,
                            CompressorNameKey,
                            ENCODING_AVI_MJPG,
                            DepthKey,
                            24,
                            FrameRateKey,
                            Rational.valueOf(15),
                            QualityKey,
                            1.0f,
                            KeyFrameIntervalKey,
                            15 * 60),
                        new Format(
                            MediaTypeKey,
                            FormatKeys.MediaType.VIDEO,
                            EncodingKey,
                            "black",
                            FrameRateKey,
                            Rational.valueOf(30)),
                            null,
                            file,
                            "Video"
                );
        screenRecorder.start();
    }
    public void  stopRecording() throws Exception {
        screenRecorder.stop();
    }
    public void deleteScreenCast(String path) {
        File directory = new File(path);

        File[]files = directory.listFiles();
        for(File f: files){
            f.delete();
        }
    }
}
