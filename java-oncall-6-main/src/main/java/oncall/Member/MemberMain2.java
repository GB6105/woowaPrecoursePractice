package oncall.Member;

import oncall.Utils.Constant;
import oncall.Utils.Parser;
import oncall.Utils.Validators;

import java.util.Arrays;
import java.util.Scanner;

public class MemberMain2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Member member = new Member();

        while (true) {
            System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            String[] monthAndDay = scanner.nextLine().split(",");
            if(!Validators.monthDayValidator(monthAndDay,member)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
        while (true) {
            //평일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKDAY_LIST);
            member.weekdayMembers = scanner.nextLine().split(",");
            if(!Parser.inputParser(member.weekdayMembers)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }

            //휴일 근무자 순번 입력
            System.out.print(Constant.REQUIRE_WEEKEND_LIST);
            member.weekendMembers = scanner.nextLine().split(",");

            if(!Parser.inputParser(member.weekendMembers)){
                System.out.println(Constant.INPUT_ERROR_MESSAGE);
                continue;
            }
            break;
        }
        System.out.println("달 " + member.month + "요일 " + member.day );
        System.out.println(Arrays.toString(member.weekdayMembers));
        System.out.println(Arrays.toString(member.weekendMembers));

        // 날짜 라이브러리 계산해서 1일부터 입력받은 월의 마지막 날까지 정해주기
        // 정해지면 시작 요일에 맞추어서 요일 배분하기
        // 1일부터 순회하면서
            // 평일이면 평일순서에 맞게 배정
            // 주말이면 주말 순서에 맞게 배정
            // 예외 발생하면 옮기기
        // 만들어진 결과 출력하기
    }





}
