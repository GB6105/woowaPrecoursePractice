package oncall.Utils.validation;

public class NicknameLengthValidator implements Validator<String[]> {
    private final int max;

    public NicknameLengthValidator(int max) {
        this.max = max;
    }

    /**
     * 닉네임 길이가 5자가 넘는 요소가 있는 지 체크하는 메서드
     * @param strings
     * @return
     */
    @Override
    public boolean validate(String[] strings) {
        boolean isOverFive = false;
        for(String nickname : strings) {
            if(nickname.length() > 5){
                isOverFive = true;
                return isOverFive;
            }
        }
        return isOverFive;
    }
}
