package logic;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetrySetup implements IRetryAnalyzer {

  private int retryCount = 0;
  private int maxRetryCount = 2;

  public String getResultStatusName(int status) {
    String resultName = null;
    if (status == 1)
      resultName = "SUCCESS";
    if (status == 2)
      resultName = "FAILURE";
    if (status == 3)
      resultName = "SKIP";
    return resultName;
  }


  @Override
  public boolean retry(@org.jetbrains.annotations.NotNull ITestResult result) {
    if (!result.isSuccess()) {
      if (retryCount < maxRetryCount) {
        log.info("Повторяю тест: " + result.getName() + " со статусом " + "'" + getResultStatusName(result.getStatus()) + "'" + " для попытки " + (retryCount + 1) + " раз.");
        result.setStatus(ITestResult.SKIP);
        retryCount++;
        return true;
      }else {
        result.setStatus(ITestResult.FAILURE);
      }
    } else {
      result.setStatus(ITestResult.SUCCESS);
    }
    return false;
  }

  }
