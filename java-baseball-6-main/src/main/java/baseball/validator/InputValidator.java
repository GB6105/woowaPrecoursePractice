package baseball.validator;

import baseball.utils.Constants;
import java.util.regex.Pattern;

public class InputValidator {

  private InputValidator() {
  }

  public static boolean inputValidate(String input) {
    if (!Constants.INPUT_NUMBER.matcher(input).matches()) {
      return false;
    }
    return true;
  }
}
