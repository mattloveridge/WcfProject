public class CellPhoneUsageByMonthDto {


    private int employeeId;
    private int [] minutesByMonth;
    private double [] usageByMonth;

    public CellPhoneUsageByMonthDto( int employeeId, int [] minutesByMonth, double [] usageByMonth ) {
        this.employeeId = employeeId;
        this.minutesByMonth = minutesByMonth;
        this.usageByMonth = usageByMonth;
    }

}
