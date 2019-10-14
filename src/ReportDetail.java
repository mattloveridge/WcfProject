public class ReportDetail {

    private CellPhoneDto cellPhoneDto;
    private int employeeTotalMinutes = 0;
    private double employeeTotalUsage = 0.0;
    private int[ ] monthsMinutes = new int[ 12 ];
    private double[ ] monthsUsage = new double[ 12 ];

    public void setCellPhoneDto( CellPhoneDto cellPhoneDto ) { this.cellPhoneDto = cellPhoneDto; }
    public void addToMonthsMinutes( int month, int minutes ) { this.monthsMinutes[ month - 1 ] += minutes; }
    public void addToMonthsUsage( int month, double usage ) { this.monthsUsage[ month - 1 ] += usage; }
    public int[ ] getMonthsMinutes( ) { return this.monthsMinutes; }
    public double[ ] getMonthsUsage( ) { return this.monthsUsage; }
    public CellPhoneDto getCellPhoneDto( ) { return this.cellPhoneDto; }
    public int getEmployeeTotalMinutes( ) { return employeeTotalMinutes; }
    public double getEmployeeTotalUsage( ) { return employeeTotalUsage; }
    public void addToEmployeeTotalMinutes( int minutes ) { this.employeeTotalMinutes += minutes; }
    public void addToEmployeeTotalUsage( double usage ) { this.employeeTotalUsage += usage; }
}