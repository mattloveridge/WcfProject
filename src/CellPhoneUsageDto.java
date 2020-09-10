import java.time.LocalDate;

public class CellPhoneUsageDto
        implements Comparable< CellPhoneUsageDto > {

    private int employeeId;
    private String date;
    private int minutes;
    private double usage;

    CellPhoneUsageDto(String input ){
        String [] strArray = input.split(",");
        employeeId = Integer.parseInt(strArray[ 0 ]);
        date = strArray[ 1 ];
        minutes = Integer.parseInt(strArray[ 2 ]);
        usage = Double.parseDouble(strArray[ 3 ]);
    }

    public int getEmployeeId() { return employeeId; }
    public String getDate() { return date; }
    public int getMinutes() {
        return minutes;
    }
    public double getUsage() {
        return usage;
    }

    @Override
    public int compareTo( CellPhoneUsageDto other ) {

        int result = this.employeeId - other.employeeId;

        if ( result != 0 ) { return result; }

        return getLocalDate(this).compareTo(getLocalDate(other));
    }

    private LocalDate getLocalDate( CellPhoneUsageDto cellPhoneUsageDto ) {
        String [] strArray = cellPhoneUsageDto.getDate( ).split("/");
        LocalDate localDate = LocalDate.of(
                Integer.parseInt( strArray[ 2 ] ),
                Integer.parseInt( strArray[ 0 ] ),
                Integer.parseInt( strArray[ 1 ] ) );

        return localDate;
    }
}
