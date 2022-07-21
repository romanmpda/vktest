package pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessangerPage {
  private String chatName = "newChat";
  private String searchAudio = "Metallica";
  private SelenideElement mainLink = $(byXpath("//a[@aria-label='На главную']"));
  private SelenideElement messangerLink = $(byXpath("//li[@id='l_msg']"));
  private SelenideElement newChatButton = $(byXpath("//button[@aria-label='Новый чат']"));
  private SelenideElement chartNameInput = $(byXpath("//input[@id='im_dialogs_creation_name']"));
  private SelenideElement createChatButton =
      $(byXpath("//span[contains(text(),'Создать')]/ancestor::button"));
  private SelenideElement moreButton = $(byXpath("//a[@aria-label='Ещё']"));
  private SelenideElement audioIcon = $(byXpath("//a[contains(@class, '_type_audio')]"));
  private SelenideElement searchAudioInput = $(byXpath("//input[@id='ape_edit_playlist_search']"));
  private SelenideElement attachAudioButton =
      $(byXpath("//div[contains(@class, 'ape_selected')]/div[@role='button']"));
  private SelenideElement moreChatActions =
      $(byXpath("//div[contains(@class, 'header-more')]/div/div[@role='button']/parent::div"));
  private SelenideElement leaveChat = $(byXpath("//a[@data-action='leave']"));
  private SelenideElement leaveChatDialog =
      $(byXpath("//span[contains(text(),'Выйти')]/ancestor::button"));
  private SelenideElement sendMessageButton =
      $("._im_send.im-chat-input--send.im-send-btn.im-send-btn_send");

  public void createChat() {
    log.info("Выполняю создание чата");
    mainLink.click();
    messangerLink.click();
    newChatButton.click();
    chartNameInput.setValue(chatName);
    createChatButton.click();
  }

  public void addComposition() {
    log.info("Добавляю композицию: {} Из библиотеки пользователя", searchAudio);
    actions().pause(2000).perform();
    actions().moveToElement(moreButton).click().perform();
    actions().moveToElement(audioIcon).click().perform();
    actions().pause(2000).perform();
    searchAudioInput.setValue(searchAudio);
    attachAudioButton.click();
    sendMessageButton.click();
  }

  public void removeChat() {
    log.info("Выполняю удаление чата: {}", chatName);
    actions().moveToElement(moreChatActions).click().perform();
    actions().moveToElement(leaveChat).click().perform();
    leaveChatDialog.click();
  }
}
