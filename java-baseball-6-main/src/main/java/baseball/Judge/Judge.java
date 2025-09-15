package baseball.Judge;

import java.util.ArrayList;
import java.util.List;

public class Judge {
  private int strikeCounter = 0;
  private int ballCounter = 0;
  private List<Integer> tryIntegerNumbers = new ArrayList<>();
  private List<Integer> computer;

  public Judge(String input, List<Integer> computer) {
    char[] tryNumbers = input.toCharArray();
    for (char tryNumber : tryNumbers) {
      tryIntegerNumbers.add(Character.getNumericValue(tryNumber));
    }
    this.computer = computer;
  }
  public int getStrikeCounter() {
    return strikeCounter;
  }
  public void judgeNumber(){
      for (int i = 0; i < tryIntegerNumbers.size(); i++) {
        if (tryIntegerNumbers.get(i) == computer.get(i)) {
          strikeCounter++;
        } else if (computer.contains(tryIntegerNumbers.get(i))) {
          ballCounter++;
        }
      }
  }

  public void printResult(){
    if (strikeCounter == 3) {
      System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
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

  public boolean is3Strike(){
    return strikeCounter == 3;
  }
}
