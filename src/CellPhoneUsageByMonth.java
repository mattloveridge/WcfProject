import java.time.LocalDate;

public class CellPhoneUsageByMonth
                        implements Comparable<CellPhoneUsageByMonth> {

    private int employeeId;
    private String date;
    private int totalMinutes;
    private double totalData;

    CellPhoneUsageByMonth( String input ){
        String [] strArray = input.split(",");
        employeeId = Integer.parseInt(strArray[ 0 ]);
        date = strArray[ 1 ];
        totalMinutes = Integer.parseInt(strArray[ 2 ]);
        totalData = Double.parseDouble(strArray[ 3 ]);
    }

    public int getEmployeeId() { return employeeId; }

    public String getDate() { return date; }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public double getTotalData() {
        return totalData;
    }

    @Override
    public int compareTo( CellPhoneUsageByMonth other ) {

        int result = this.employeeId - other.employeeId;

        if (result != 0) { return result; }

        return getLocalDate(this).compareTo(getLocalDate(other));
    }

    private LocalDate getLocalDate( CellPhoneUsageByMonth cellPhoneUsageByMonth ) {
        String [] strArray = cellPhoneUsageByMonth.getDate( ).split("/");
        LocalDate localDate = LocalDate.of(
                Integer.parseInt( strArray[ 2 ] ),
                Integer.parseInt( strArray[ 0 ] ),
                Integer.parseInt( strArray[ 1 ] ) );

        return localDate;
    }
}
