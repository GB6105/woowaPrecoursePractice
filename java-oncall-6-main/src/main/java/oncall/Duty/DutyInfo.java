package oncall.Duty;

public class DutyInfo {
    private String dayOfWeek;
    private String workerName;

    public DutyInfo(String dayOfWeek, String workerName){
        this.dayOfWeek = dayOfWeek;
        this.workerName = workerName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getWorkerName() {
        return workerName;
    }
}
