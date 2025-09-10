package oncall.Utils;

public class Parser {
    private Parser(){};

    public static boolean inputParser(String[] strings){
        if(strings.length <5 || strings.length > 36){
            return false;
        }
        for(String str : strings){
            if(!Constant.NICKNAME_REGEX.matcher(str).matches()){
                return false;
            }
        }
        if(Validators.hasDuplicate(strings)){
            return false;
        }
        return true;
    }
}
