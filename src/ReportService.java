import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReportService {

    private static String header = " Report Run Date     Number of Phones     Total Minutes     " +
            "Total Data     Average Minutes     Average Data";

    private static String detail = " Employee Id     Employee Name     Model     Purchase Date     " +
            "Minutes Usage     Data Usage";

    private static String newLine = "\n";

    public static void printHeader(){
        System.out.println(header);
        System.out.println(newLine);
        System.out.println(detail);
        System.out.println(
                "\tJan\t\t" +
                        "Feb\t\t" +
                        "Mar\t\t" +
                        "Apr\t\t" +
                        "May\t\t" +
                        "Jun\t\t" +
                        "Jul\t\t" +
                        "Aug\t\t" +
                        "Sep\t\t" +
                        "Oct\t\t" +
                        "Nov\t\t" +
                        "Dec\t\t");
    }

    public void createReport( Optional<Map< Integer, CellPhone >> optionalCellPhones,
                               Optional<List<CellPhoneUsageByMonth >> optionalCellPhoneUsageByMonths ) {

        int saveEmployeeId = -1;
        String currentMonth = "",
                currentYear = "",
                saveMonth = "",
                saveYear = "";
        int cumMinutes = 0;
        double cumData = 0.0;
        int [] minutesByMonth = new int[ 12 ];
        double [] usageByMonth = new double[ 12 ];

        saveEmployeeId = optionalCellPhoneUsageByMonths.get().get(0).getEmployeeId();
        saveMonth = getMonth(optionalCellPhoneUsageByMonths.get().get(0));
        saveYear = getYear(optionalCellPhoneUsageByMonths.get().get(0));

        for (CellPhoneUsageByMonth cellPhoneUsageByMonth : optionalCellPhoneUsageByMonths.get()) {
            currentMonth = getMonth(cellPhoneUsageByMonth);
            currentYear = getYear(cellPhoneUsageByMonth);

            if (cellPhoneUsageByMonth.getEmployeeId() != saveEmployeeId) {
                writeDetail(
                        new CellPhoneUsageByMonthDto(saveEmployeeId, minutesByMonth, usageByMonth),
                            new CellPhoneDto( optionalCellPhones.get(), saveEmployeeId ) );
                saveEmployeeId = cellPhoneUsageByMonth.getEmployeeId();
                saveMonth = currentMonth;
                saveYear = currentYear;
                cumMinutes = 0;
                cumData = 0.0;
                minutesByMonth = new int[12];
                usageByMonth = new double[12];
                continue;
            }

            cumMinutes += cellPhoneUsageByMonth.getTotalMinutes();
            cumData += cellPhoneUsageByMonth.getTotalData();

            saveEmployeeId = cellPhoneUsageByMonth.getEmployeeId();
            saveMonth = currentMonth;
            saveYear = currentYear;
        }

        writeDetail( saveEmployeeId, saveMonth, saveYear, cumMinutes, cumData );
    }

    private void processChangedEmployee( CellPhoneUsageByMonthDto cellPhoneUsageByMonthDto ) {


    }

    private String getMonth( CellPhoneUsageByMonth cellPhoneUsageByMonth ) {
        return cellPhoneUsageByMonth.getDate().substring(1, 2).equals("/") ?
                cellPhoneUsageByMonth.getDate().substring(0, 1) :
                cellPhoneUsageByMonth.getDate().substring(0, 2);
    }

    private String getYear( CellPhoneUsageByMonth cellPhoneUsageByMonth ) {
        return cellPhoneUsageByMonth.getDate().substring(
                cellPhoneUsageByMonth.getDate().length() - 4,
                cellPhoneUsageByMonth.getDate().length());
    }

    public void writeDetail(
            int saveEmployeeId, String saveDate, int counter ) {

        System.out.println(saveEmployeeId +
                "  " +
                saveDate +
                "  " +
                counter) ;
    }

    public void writeDetail(
            int saveEmployeeId, String saveDate, int counter, int detailLinesCounter ) {

        System.out.println(saveEmployeeId +
                "  " +
                saveDate +
                "  " +
                counter +
                "  " +
                detailLinesCounter) ;
    }

    public void writeDetail(
            int saveEmployeeId, String saveMonth, String saveYear, int cumMinutes, double cumData ) {

        System.out.println(saveEmployeeId +
                "  " +
                saveMonth +
                "  " +
                saveYear +
                "  " +
                cumMinutes +
                "  " +
                cumData) ;

    }

    public void writeDetail( CellPhoneUsageByMonthDto cellPhoneUsageByMonthDto,
                                                                CellPhoneDto cellPhoneDto) {

/*
        System.out.println(saveEmployeeId +
                "  " +
                saveMonth +
                "  " +
                saveYear +
                "  " +
                cumMinutes +
                "  " +
                cumData) ;
*/

    }
}
