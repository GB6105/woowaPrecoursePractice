package oncall.Member;

import oncall.Utils.Constant;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Member {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            String[] inputMonthAndDay = scanner.nextLine().split(",");
            //입력값 검증 부분 -> InputValidator 로 추출 예정
            if (inputMonthAndDay.length != 2) { // 월 , 요일 값
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            int month = 1;
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

            String day = inputMonthAndDay[1];
            if (!Constant.DAY_KOREAN_REGEX.matcher(day).matches()) { // 요일 범주 오류
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            System.out.println("달 = " + month + ", 요일 = " + day); // 입출력 확인 디버깅 코드
            break;
        }
        while(true){
            //평일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKDAY_LIST);
            String[] inputWeekdayMembers = scanner.nextLine().split(",");

            // 1. 닉네임 5글자 이하
            boolean isNicknameLong = false;
            for(int i = 0; i < inputWeekdayMembers.length; i++) {
                if(inputWeekdayMembers[i].length() > 5){
                    isNicknameLong = true;
                    break;
                }
            }
            if(isNicknameLong){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            // 2. 닉네임 중복 판단
            Set<String> weekdaySet = new HashSet<>();
            boolean isDuplicate = false;

            for(int i = 0; i < inputWeekdayMembers.length; i++) {
                String name = inputWeekdayMembers[i].trim();
                if(!weekdaySet.add(name)){
                    System.out.println(Constant.INPUT_ERROR_MESSAGE);
                    isDuplicate = true;
                }
            }

            if(isDuplicate){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            //휴일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKEND_LIST);
            String[] inputWeekendMembers = scanner.nextLine().split(",");

            // 1. 닉네임 5글자 이하
            for(int i = 0; i < inputWeekendMembers.length; i++) {
                if(inputWeekendMembers[i].length() > 5){
                    isNicknameLong = true;
                }
            }
            if(isNicknameLong){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            // 2. 닉네임 중복 판단
            Set<String> weekendSet = new HashSet<>();

            for(int i = 0; i < inputWeekendMembers.length; i++) {
                String name = inputWeekendMembers[i].trim();
                if(!weekendSet.add(name)){
                    System.out.println(Constant.INPUT_ERROR_MESSAGE);
                    isDuplicate = true;
                }
            }

            if(isDuplicate){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
    }
    public boolean checkNicknameLength(String[] nicknames){
        boolean isOverFive = false;
        for(String nickname : nicknames) {
            if(nickname.length() > 5){
                isOverFive = true;
                return isOverFive;
            }
        }
        return isOverFive;
    }
}
