package baseball.RandomNumberGenerator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RandomNumberGenerator {

  private  List<Integer> computer = new ArrayList<Integer>();

  public  List<Integer> getComputer() {
    while (computer.size() < 3) {
      int randomNumber = Randoms.pickNumberInRange(1, 9);
      if (!computer.contains(randomNumber)) {
        computer.add(randomNumber);
      }
    }
    for (Integer number : computer) {
      System.out.print(number);
    }
    return computer;
  }
}
