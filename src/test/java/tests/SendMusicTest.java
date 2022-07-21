package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import io.qameta.allure.Step;
import logic.RetrySetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MessangerPage;

@Slf4j
public class SendMusicTest {
  AuthorizationPage authorizationPage = new AuthorizationPage();
  MessangerPage mP = new MessangerPage();

  @Step("Шаг1: авторизация в vk.com")
  @BeforeSuite(alwaysRun = true)
  public void setupSuite(ITestContext context) {
    for (ITestNGMethod method : context.getAllTestMethods()) {
      method.setRetryAnalyzer(new RetrySetup());
    }
    authorizationPage.authorization();
  }

  @Step("Шаг2: создание нового чата")
  @Test(priority = 0)
  public void createChat() {
    mP.createChat();
    Assert.assertEquals(1, 0);
  }

  @Step("Шаг3: отправка композиции из библиотеки пользователя в чат")
  @Test(priority = 1)
  public void sendComposition() {
    mP.addComposition();
    Assert.assertEquals(1, 0);
  }

  @Step("Шаг4: удаление чата после выполнения тестов")
  @AfterSuite(alwaysRun = true)
  public void afterActions() {
    mP.removeChat();
    closeWebDriver();
  }
}
