package oncall.Utils;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern DAY_KOREAN_REGEX = Pattern.compile("^(월|화|수|목|금|토|일)$");
    public static final Pattern NICKNAME_REGEX = Pattern.compile("^[가-힣]{1,4}$");
}
