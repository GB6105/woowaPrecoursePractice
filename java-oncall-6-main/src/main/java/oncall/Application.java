package oncall;

import oncall.Duty.DutyInfo;
import oncall.Member.Member;
import oncall.Utils.Constant;
import oncall.Utils.Holidays;
import oncall.Utils.Parser;
import oncall.Utils.Validators;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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
//        System.out.println("달 " + member.month + "요일 " + member.day );
//        System.out.println(Arrays.toString(member.weekdayMembers));
//        System.out.println(Arrays.toString(member.weekendMembers));

        // 날짜 라이브러리 계산해서 1일부터 입력받은 월의 마지막 날까지 정해주기
        // 정해지면 시작 요일에 맞추어서 요일 배분하기
        // 1일부터 순회하면서
        // 평일이면 평일순서에 맞게 배정
        // 주말이면 주말 순서에 맞게 배정
        // 예외 발생하면 옮기기
        // 만들어진 결과 출력하기

        // 입력받은 월의 마지막 날
        int lastDate = LocalDate.of(LocalDate.now().getYear(),member.month,1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
//        System.out.println(lastDate);

        String startDay = member.day; // 시작 요일 정보

        DutyInfo[] duties = new DutyInfo[lastDate+1]; //31

        String[] days = {"월","화","수","목","금","토","일"};
        int startIndex = 0;
        int weekdayIndex = 0;
        int weekendIndex = 0;

        Deque<String> weekdayQ = new ArrayDeque<>(Arrays.asList(member.weekdayMembers));
        Deque<String> weekendQ = new ArrayDeque<>(Arrays.asList(member.weekendMembers));
        String prevWorker = null;

        for(int i = 1 ; i < lastDate+1 ; i++){
            String dayOfWeek = days[(startIndex + (i-1)) % 7];
            LocalDate date = LocalDate.of(LocalDate.now().getYear(),member.month,i);

            boolean isWeekendOrHoliday =
                    dayOfWeek.equals("토") || dayOfWeek.equals("일") || Holidays.isHoliday(date);

            Deque<String> q = isWeekendOrHoliday ? weekendQ : weekdayQ;

            // 오늘 배정: 어제와 같으면 "다음 사람"과 순서 교체
            String assigned = pickAvoidingConsecutive(q, prevWorker);

            // 회전: 오늘 배정된 사람은 큐의 뒤로 보냄
            q.offerLast(assigned);

            duties[i] = new DutyInfo(dayOfWeek, assigned);
            prevWorker = assigned;

//            if(dayOfWeek != "토" && dayOfWeek != "일" && !Holidays.isHoliday(date)){
//                duties[i] = new DutyInfo(dayOfWeek,member.weekdayMembers[weekdayIndex % member.weekdayMembers.length]);
//                weekdayIndex++;
//            }else if(dayOfWeek.equals("토")){
//                if(duties[i-1].getWorkerName().equals(member.weekendMembers[weekendIndex % member.weekendMembers.length])){
//
//                }
//            }
//            else{
//                duties[i] = new DutyInfo(dayOfWeek,member.weekendMembers[weekendIndex % member.weekendMembers.length]);
//                weekendIndex++;
//            }
        }


        for(int i = 1; i < lastDate+1 ; i++){
            System.out.println(member.month + "월 "
                    + i + "일 "
                    + duties[i].getDayOfWeek()+" , "+ duties[i].getWorkerName());
        }

    }
    private static String pickAvoidingConsecutive(Deque<String> q, String prev) {
        if (q.isEmpty()) throw new IllegalStateException("순번이 비어 있습니다.");

        String first = q.pollFirst();                 // 원래 오늘 차례
        if (first != null && first.equals(prev)) {    // 연속 2일 충돌
            String second = q.pollFirst();            // 다음 사람
            if (second == null) {
                // 사람 수가 1명이라면 규칙상 해결 불가
                throw new IllegalStateException("연속 근무를 피할 수 없습니다. 순번 인원을 늘리세요.");
            }
            // "순서를 바꿔" 오늘은 second가 근무, first는 다음 차례가 되도록 앞에 되돌려둠
            q.addFirst(first);
            return second;
        }
        return first;
    }
}
