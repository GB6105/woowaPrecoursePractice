package oncall.Utils.validation;

public class NicknameLengthValidator implements Validator<String[]> {
    private final int max;
    private boolean isOverFive = false;
    public NicknameLengthValidator(int max) {
        this.max = max;
    }

    /**
     * 닉네임 길이가 5자가 넘는 요소가 있는 지 체크하는 메서드
     * @param strings
     * @return
     */
    @Override
    public void validate(String[] strings) {
        for(String nickname : strings) {
            if(nickname.length() > 5){
                isOverFive = true;
            }
        }
    }

    public boolean isOverFive() {
        return isOverFive;
    }
}
