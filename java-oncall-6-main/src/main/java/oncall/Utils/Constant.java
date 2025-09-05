package oncall.Utils;

import java.util.regex.Pattern;

public class Constant {
    public static final String REQUIRE_WEEKDAY_LIST = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요>";
    public static final String REQUIRE_WEEKEND_LIST = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>";
    public static final String INPUT_ERROR_MESSAGE = "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.";
    public static final Pattern DAY_KOREAN_REGEX = Pattern.compile("^(월|화|수|목|금|토|일)$");
}

