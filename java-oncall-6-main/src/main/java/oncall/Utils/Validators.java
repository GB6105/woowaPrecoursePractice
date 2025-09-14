package oncall.Utils;

import oncall.Member.Member;

import java.util.HashSet;
import java.util.Set;

public class Validators {
    private Validators(){}

    public static boolean hasDuplicate(String[] strings){
        Set<String> seen = new HashSet<>();
        for (String nickname : strings) {
            if (!seen.add(nickname.trim())) {
                return true;
            }
        }
        return false;
    }

    public static boolean monthDayValidator(String[] strings, Member member){
        if (strings.length != 2) { // 월 , 요일 값
            return false;
        }
        try{
            member.month = Integer.parseInt(strings[0]);
        }catch(NumberFormatException e){
            return false;
        }
        if (member.month < 1 || member.month > 12) { // 월 범주 오류
            return false;
        }

        member.day = strings[1].trim();
        if (!Regex.DAY_KOREAN_REGEX.matcher(member.day).matches()) { // 요일 범주 오류
            return false;
        }
        return true;
    }
}
