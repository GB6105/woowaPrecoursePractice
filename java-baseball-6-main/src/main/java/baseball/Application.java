package baseball;

import baseball.Judge.Judge;
import baseball.RandomNumberGenerator.RandomNumberGenerator;
import baseball.validator.InputValidator;
import camp.nextstep.edu.missionutils.Console;
import com.sun.source.tree.IfTree;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

public class Application {

  public static void main(String[] args) {
    // TODO: 프로그램 구현

    //Game 부분
    System.out.println("숫자 야구 게임을 시작합니다.");
    boolean toGameContinue = true;
    while (toGameContinue) {

      //RandomNumberGenerator 부분
      RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
      List<Integer> computer = randomNumberGenerator.getComputer();

      boolean isCorrect = false;
      while(!isCorrect){
        //console 부분
        System.out.print("숫자를 입력해주세요 : ");
        String str = Console.readLine();
        if (!InputValidator.inputValidate(str)) {
          throw new IllegalArgumentException("잘못된 수를 입력하였습니다.");
        }

        //Judge 부분
        Judge judge = new Judge(str, computer);
        judge.judgeNumber();
        judge.printResult();
        if(judge.is3Strike()){
          isCorrect = true;
        }
      }

      System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
      if (Console.readLine().equals("1")) {
        continue;
      } else if (Console.readLine().equals("2")) {
        toGameContinue = false;
      }
    }
  }
}
