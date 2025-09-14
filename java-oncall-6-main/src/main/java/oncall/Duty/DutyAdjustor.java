package oncall.Duty;

import oncall.Member.Member;
import oncall.Utils.Holidays;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DutyAdjustor {
    private int month;
    private int lastDate;
    private DutyInfo[] duties;

    public DutyAdjustor(int month){
        this.month = month;
        this.lastDate = LocalDate.of(LocalDate.now().getYear(),month,1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        this.duties = new DutyInfo[lastDate + 1];
    }

    public void printDuty(){
        for(int i = 1; i < lastDate+1 ; i++){
            System.out.println(month + "월 "
                    + i + "일 "
                    + duties[i].getDayOfWeek()+" , "+ duties[i].getWorkerName());
        }
    }

    public void adjustDuty(Member member){
        String[] days = {"월","화","수","목","금","토","일"};
        int startIndex = 0;

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
        }
    }

    private static String pickAvoidingConsecutive(Deque<String> q, String prev) {
//        if (q.isEmpty()) throw new IllegalStateException("순번이 비어 있습니다.");

        String first = q.pollFirst();                 // 원래 오늘 차례
        if (first != null && first.equals(prev)) {    // 연속 2일 충돌
            String second = q.pollFirst();            // 다음 사람
//            if (second == null) {
//                // 사람 수가 1명이라면 규칙상 해결 불가
//                throw new IllegalStateException("연속 근무를 피할 수 없습니다. 순번 인원을 늘리세요.");
//            }
            // "순서를 바꿔" 오늘은 second가 근무, first는 다음 차례가 되도록 앞에 되돌려둠
            q.addFirst(first);
            return second;
        }
        return first;
    }
}
