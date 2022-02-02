import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class StepImplementation extends BaseTest {

    @Step("<time> saniye kadar bekle")
    public void waitForSeconds(int time) throws InterruptedException{
        Thread.sleep(time*1000);
        MyLogger.log.info(time + "Saniye Kadar Bekletildi");
    }

    @Step("id <id> li elemente tıkla")
    public void clickByid(String id){
        appiumDriver.findElement(By.id(id)).click();
        MyLogger.log.info("Elemente Tıkladı");
    }

    @Step("xpath <xpath> li elemente tıkla")
    public void clickByXpath(String xpath){
        appiumDriver.findElement(By.xpath(xpath)).click();
        MyLogger.log.info( "Elemente Tıkladı");
    }

    @Step("id <id> li elementi bul ve <text> değerini yaz")
    public void sendKeysById(String id,String text){
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        MyLogger.log.info(text + "Değerini Yazdı");
    }

    @Step("Aşağı Kaydır")
    public void scrollDown(){

        final int PRESS_TIME = 200;
        int edgeBorder = 1;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.height / 2 , edgeBorder);

        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release()
                .perform();

        MyLogger.log.info("Aşağı kaydırdı");
    }

    @Step("Sayfayı sola kaydır")
    public void swipeLeft() {
        final int PRESS_TIME = 200;
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();

        MyLogger.log.info("Sola Kaydırıldı");

    }

    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textAreaControlById(String id, String text) {
        Assert.assertTrue("Element text değerini içermiyor"
                , appiumDriver.findElement(By.id(id)).getText().contains(text));

        MyLogger.log.info(text + "Alanı Doğru");
    }

    @Step("xpath <xpath> li elementi bul ve <text> alanını kontrol et")
    public void textAreaControlByXpath(String xpath, String text){
        Assert.assertTrue("Element text değerini içermiyor"
                , appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));

        MyLogger.log.info(text + "Alanı Doğru");
    }


    @Step("Elementler <xpath> arasından rastgele bir tanesini seç ve tıkla")
    public void clickRandomElement(String xpath) {
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();

        MyLogger.log.info("Rastgele Bir Ürün Seçildi");

    }








}
