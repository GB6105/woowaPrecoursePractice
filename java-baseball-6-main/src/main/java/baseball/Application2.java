package baseball;

import baseball.Judge.Judge;
import baseball.RandomNumberGenerator.RandomNumberGenerator;
import baseball.validator.InputValidator;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class Application2 {

  public static void main(String[] args) {
    // TODO: 프로그램 구현

    //Game 부분
    System.out.println("숫자 야구 게임을 시작합니다.");

    //RandomNumberGenerator 부분
//    List<Integer> computer = RandomNumberGenerator.getComputer();

    while (true) {
      //console 부분
      System.out.print("숫자를 입력해주세요 : ");
      String str = Console.readLine();
      if (!InputValidator.inputValidate(str)) {
        throw new IllegalArgumentException("잘못된 수를 입력하였습니다.");
      }

      //Judge 부분
//      char[] tryNumbers = str.toCharArray();
//      List<Integer> tryIntegerNumbers = new ArrayList<>();
//      for (char tryNumber : tryNumbers) {
//        tryIntegerNumbers.add(Character.getNumericValue(tryNumber));
//      }
//      int strikeCounter = 0;
//      int ballCounter = 0;
      // 대체
//      Judge judge = new Judge(str, computer);

//      for (int i = 0; i < tryIntegerNumbers.size(); i++) {
//        System.out.println("원소 비교" + tryNumbers[i] + " " + computer.get(i));
//        if (tryIntegerNumbers.get(i) == computer.get(i)) {
//          strikeCounter++;
//        } else if (computer.contains(tryIntegerNumbers.get(i))) {
//          ballCounter++;
//        }
//      }
      // 대체

//      judge.judgeNumber();

//      if (strikeCounter == 3) {
//        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
//        break;
//      }
//
//      if (ballCounter > 0 && strikeCounter > 0) {
//        System.out.println(ballCounter + "볼 " + strikeCounter + "스트라이크");
//      } else if (ballCounter == 0 && strikeCounter > 0) {
//        System.out.println(strikeCounter + "스트라이크");
//      } else if (ballCounter > 0 && strikeCounter == 0) {
//        System.out.println(ballCounter + "볼");
//      } else {
//        System.out.println("낫싱");
//      }
      //대체

//      judge.printResult();
    }
//    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
//    if (Console.readLine().equals("1")) {
//
//    } else if (Console.readLine().equals("2")) {
//      return;
//
//    }
  }
}
