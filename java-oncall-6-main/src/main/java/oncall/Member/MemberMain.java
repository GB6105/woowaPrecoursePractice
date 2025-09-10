package oncall.Member;

import oncall.Utils.Constant;
import oncall.Utils.validation.DuplicateValidator;
import oncall.Utils.validation.NicknameLengthValidator;

import java.util.Arrays;
import java.util.Scanner;

public class MemberMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Member member = new Member();

        NicknameLengthValidator nicknameLengthValidator = new NicknameLengthValidator(5);
        DuplicateValidator duplicateValidator = new DuplicateValidator();
        while (true) {
            System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            String[] inputMonthAndDay = scanner.nextLine().split(",");
            //입력값 검증 부분 -> InputValidator 로 추출 예정
            if (inputMonthAndDay.length != 2) { // 월 , 요일 값
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            try{
                member.month = Integer.parseInt(inputMonthAndDay[0]);
            }catch(NumberFormatException e){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            if (member.month < 1 || member.month > 12) { // 월 범주 오류
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            member.day = inputMonthAndDay[1].trim();
            if (!Constant.DAY_KOREAN_REGEX.matcher(member.day).matches()) { // 요일 범주 오류
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
        while (true) {
            //평일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKDAY_LIST);
            member.weekdayMembers = scanner.nextLine().split(",");
            nicknameLengthValidator.validate(member.weekdayMembers);
            duplicateValidator.validate(member.weekdayMembers);
            // 1. 닉네임 5글자 이하
            if(nicknameLengthValidator.isOverFive()){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            // 2. 닉네임 중복 판단
            if(duplicateValidator.isDuplicate()){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            //휴일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKEND_LIST);
            member.weekendMembers = scanner.nextLine().split(",");
            nicknameLengthValidator.validate(member.weekendMembers);
            duplicateValidator.validate(member.weekendMembers);

            // 1. 닉네임 5글자 이하
            if(nicknameLengthValidator.isOverFive()){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            // 2. 닉네임 중복 판단
            if(duplicateValidator.isDuplicate()){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
        System.out.println("달 " + member.month + "요일 " + member.day );
        System.out.println(Arrays.toString(member.weekdayMembers));
        System.out.println(Arrays.toString(member.weekendMembers));
    }





}
