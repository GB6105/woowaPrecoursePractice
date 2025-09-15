package baseball;

import baseball.validator.InputValidator;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

public class Application {

  public static void main(String[] args) {
    // TODO: 프로그램 구현

    //Game 부분
    System.out.println("숫자 야구 게임을 시작합니다.");

    //RandomNumberGenerator 부분
    List<Integer> computer = new ArrayList<Integer>();
    while (computer.size() < 3) {
      int randomNumber = Randoms.pickNumberInRange(1, 9);
      if (!computer.contains(randomNumber)) {
        computer.add(randomNumber);
      }
    }
    for (Integer number : computer) {
      System.out.print(number);
    }

    while (true) {
      //console 부분
      System.out.print("숫자를 입력해주세요 : ");
      String str = Console.readLine();
      if (!InputValidator.inputValidate(str)) {
        System.out.println("Invalid");
        return;
      }
      //Judge 부분
      char[] tryNumbers = str.toCharArray();
      List<Integer> tryIntegerNumbers = new ArrayList<>();
      for (char tryNumber : tryNumbers) {
        tryIntegerNumbers.add(Character.getNumericValue(tryNumber));
      }
      int strikeCounter = 0;
      int ballCounter = 0;

      for (int i = 0; i < tryNumbers.length; i++) {
        System.out.println("원소 비교" + tryNumbers[i] + " " + computer.get(i));
        if (tryIntegerNumbers.get(i) == computer.get(i)) {
          strikeCounter++;
        } else if (computer.contains(tryIntegerNumbers.get(i))) {
          ballCounter++;
        }
      }
      if (strikeCounter == 3) {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        break;
      }

      if (ballCounter > 0 && strikeCounter > 0) {
        System.out.println(ballCounter + "볼 " + strikeCounter + "스트라이크");
      } else if (ballCounter == 0 && strikeCounter > 0) {
        System.out.println(strikeCounter + "스트라이크");
      } else if (ballCounter > 0 && strikeCounter == 0) {
        System.out.println(ballCounter + "볼");
      } else {
        System.out.println("낫싱");
      }
    }
    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
    if (Console.readLine().equals("1")) {

    } else if (Console.readLine().equals("2")) {
      return;

    }
  }
}
