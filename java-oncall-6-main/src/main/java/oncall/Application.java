package oncall;

import oncall.Duty.DutyAdjustor;
import oncall.Member.Member;
import oncall.Utils.Constant;
import oncall.Utils.Parser;
import oncall.Utils.Validators;

import java.util.Scanner;

public class Application {
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

        DutyAdjustor dutyAdjustor = new DutyAdjustor(member.month);
        dutyAdjustor.adjustDuty(member);
        dutyAdjustor.printDuty();
    }

}
