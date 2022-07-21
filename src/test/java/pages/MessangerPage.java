package pages;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

import com.codeborne.selenide.SelenideElement;
import logic.ServiceClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessangerPage {
  ServiceClass sC = new ServiceClass();
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
  private SelenideElement photoIcon = $(byXpath("//a[contains(@class, '_type_photo')]"));
  private SelenideElement docIcon = $(byXpath("//a[contains(@class, '_type_doc')]"));
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
  private SelenideElement userPhoto = $(byXpath("//a[@href='/album239644651_00?rev=1']"));
  private SelenideElement miniPhoto = $(byXpath("//a[@href='photo239644651_457239020']/parent::div[@id='photos_choose_rows']"));
  private SelenideElement attachDoc = $(byXpath("//span[contains(@class, '_docs_choose_attach')]"));
  private SelenideElement attachDocInput = $(byXpath("//span[contains(text(),'Загрузить')]"));

  public void selectIcon(SelenideElement element){
    actions().pause(2000).perform();
    actions().moveToElement(moreButton).click().perform();
    actions().moveToElement(element).click().perform();
    actions().pause(2000).perform();
  }

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
    selectIcon(audioIcon);
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

  public void addPhoto() {
    log.info("Добавляю фото из библиотеки пользователя");
    selectIcon(photoIcon);
    userPhoto.click();
    miniPhoto.click();
    sendMessageButton.click();
  }

  public void addDoc() {
    log.info("Добавляю документ из библиотеки пользователя");
    selectIcon(docIcon);
    attachDoc.click();
    sendMessageButton.click();
  }
  public void attachFile(String filename) {
    log.info("Добавляю файл с ПК пользователя");
    selectIcon(docIcon);
    sC.uploadFile(attachDocInput, filename);
    attachDoc.click();
    sendMessageButton.click();
  }

}
