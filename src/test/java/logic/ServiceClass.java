package logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ServiceClass {
  String propsPath = "src/main/resources/settings.properties";

  public String getPropsValue(String key) {
    String value;
    Properties appProps = new Properties();
    log.info("Открываю файл настроек {}", propsPath);
    try {
      appProps.load(new FileInputStream(propsPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    value = appProps.getProperty(key);
    log.info("Ключ: {} и его значение: {}", key, value);
    return value;
  }
}
