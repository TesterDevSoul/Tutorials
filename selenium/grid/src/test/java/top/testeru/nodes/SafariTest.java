//package top.testeru.nodes;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInfo;
//import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.slf4j.Logger;
//import top.testeru.basic.BaseTest;
//
//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import static java.lang.invoke.MethodHandles.lookup;
//import static org.slf4j.LoggerFactory.getLogger;
//
///**
// * @author www.testeru.top
// * @version 1.0.0
// * @Project selenium
// * @Description grid 获取title
// * @createTime 2022年12月20日 16:42:38
// */
//public class SafariTest extends BaseTest {
//
//
//    @Test
//    public void safari(TestInfo testInfo) throws MalformedURLException {
//        WebDriver webDriver = initDriver(testInfo, Platform.ANY);
//        webDriver.get("https://www.testeru.top/");
//        String title = webDriver.getTitle();
//        logger.info("{}系统的Safari浏览器打开 {}", Platform.ANY, title);
//    }
//
//}
