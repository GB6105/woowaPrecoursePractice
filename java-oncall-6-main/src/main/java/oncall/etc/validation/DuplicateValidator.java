package oncall.etc.validation;

import java.util.HashSet;
import java.util.Set;

public class DuplicateValidator implements Validator<String[]> {
    private final Set<String> weekdaySet = new HashSet<String>();
    static boolean isDuplicate = false;

    public DuplicateValidator() {}

    /**
     * 닉네임 중 중복 닉네임이 있는 지 체크하는 메서드
     * @param strings
     * @return
     */
    @Override
    public void validate(String[] strings){
        for (String nickname : strings) {
            String name = nickname.trim();
            if (!weekdaySet.add(name)) {
                isDuplicate = true;
            }
        }
    }

    public static boolean isDuplicate(){
        return isDuplicate;
    }
}
