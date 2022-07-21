package pages;


import logic.ServiceClass;
import lombok.extern.slf4j.Slf4j;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class AuthorizationPage {

  private SelenideElement enterButton = $(byXpath("//span[contains(text(),'Войти')]/ancestor::button"));
  private SelenideElement loginInput = $(byXpath("//input[@name='login']"));
  private SelenideElement submitButton = $(byXpath("//button[@type='submit']"));
  private SelenideElement passwordInput = $(byXpath("//input[@name='password']"));

  public void authorization(){
    log.info("Выполняю авторизацию");
    ServiceClass serviceClass = new ServiceClass();
    open(serviceClass.getPropsValue("vkUrl"));
    enterButton.exists();
    enterButton.click();
    loginInput.setValue(serviceClass.getPropsValue("login"));
    submitButton.click();
    passwordInput.setValue(serviceClass.getPropsValue("password"));
    submitButton.click();
  }

}
