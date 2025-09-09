package oncall.Member;

import oncall.Utils.Constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Member {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = -1;
        String day;
        String[] weekdayMembers;
        String[] weekendMembers;
        while(true) {
            System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            String[] inputMonthAndDay = scanner.nextLine().split(",");
            //입력값 검증 부분 -> InputValidator 로 추출 예정
            if (inputMonthAndDay.length != 2) { // 월 , 요일 값
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            try{
                month = Integer.parseInt(inputMonthAndDay[0]);
            }catch(NumberFormatException e){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            if (month < 1 || month > 12) { // 월 범주 오류
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            day = inputMonthAndDay[1].trim();
            if (!Constant.DAY_KOREAN_REGEX.matcher(day).matches()) { // 요일 범주 오류
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
        while(true){
            //평일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKDAY_LIST);
            weekdayMembers = scanner.nextLine().split(",");

            // 1. 닉네임 5글자 이하
            if(isNicknameToolong(weekdayMembers)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            // 2. 닉네임 중복 판단
            if(hasDuplicateNickname(weekdayMembers)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            //휴일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKEND_LIST);
            weekendMembers = scanner.nextLine().split(",");

            // 1. 닉네임 5글자 이하
            if(isNicknameToolong(weekendMembers)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            // 2. 닉네임 중복 판단
            if(hasDuplicateNickname(weekendMembers)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
        System.out.println("달 " + month + "요일 " + day );
        System.out.println(Arrays.toString(weekdayMembers));
        System.out.println(Arrays.toString(weekendMembers));
    }
    public static boolean isNicknameToolong(String[] nicknames){
        boolean isOverFive = false;
        for(String nickname : nicknames) {
            if(nickname.length() > 5){
                isOverFive = true;
                return isOverFive;
            }
        }
        return isOverFive;
    }

    public static boolean hasDuplicateNickname(String[] nicknames){
        Set<String> weekdaySet = new HashSet<>();
        boolean isDuplicate = false;

        for (String nickname : nicknames) {
            String name = nickname.trim();
            if (!weekdaySet.add(name)) {
                isDuplicate = true;
                return isDuplicate;
            }
        }
        return isDuplicate;
    }
}
