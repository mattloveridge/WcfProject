import java.util.List;
import java.util.Map;

public class Controller {

    public void createReportControl( ){

        Map< Integer, CellPhone > cellPhones = new CellPhoneDao( ).loadCellPhoneDataStore( );
        List< CellPhoneUsageByMonth > cellPhoneUsageByMonths = new CellPhoneUsageByMonthDao( ).
                                                                        loadCellPhoneUsageByMonthDataStore( );
        if ( cellPhoneUsageByMonths.isEmpty( ) ) {
            System.out.println( "cellPhoneUsageByMonths is empty" );
            return;
        }

        if ( cellPhones.isEmpty ( ) ) {
            System.out.println( "cellPhones is empty" );
            return;
        }
        createReport( cellPhones, cellPhoneUsageByMonths );
    }

    private void createReport( Map<Integer, CellPhone> cellPhones,
                                    List<CellPhoneUsageByMonth> cellPhoneUsageByMonths ) {

        int saveEmployeeId = -1;
        String currentMonth = "",
                currentYear = "",
                saveMonth = "",
                saveYear = "";
        int cumMinutes = 0;
        double cumData = 0.0;

        saveEmployeeId = cellPhoneUsageByMonths.get(0).getEmployeeId();
        saveMonth = cellPhoneUsageByMonths.get(0).getDate().substring(1, 2).equals("/") ?
                "1".concat(cellPhoneUsageByMonths.get(0).getDate().substring(1, 2)) :
                cellPhoneUsageByMonths.get(0).getDate().substring(0, 2);
        saveYear = cellPhoneUsageByMonths.get(0).getDate().substring(
                    cellPhoneUsageByMonths.get(0).getDate().length() - 4,
                    cellPhoneUsageByMonths.get(0).getDate().length());

        for (CellPhoneUsageByMonth cellPhoneUsageByMonth : cellPhoneUsageByMonths) {

//            System.out.println("cellPhoneUsageByMonth.getDate().substring(1, 2) = " + cellPhoneUsageByMonth.getDate().substring(1, 2));

            currentMonth = cellPhoneUsageByMonth.getDate().substring(1, 2).equals("/") ?
                    "1".concat(cellPhoneUsageByMonth.getDate().substring(1, 2)) :
                    cellPhoneUsageByMonths.get(0).getDate().substring(0, 2);
            currentYear = cellPhoneUsageByMonth.getDate().substring(
                                                    cellPhoneUsageByMonth.getDate().length() - 4);

            if ( cellPhoneUsageByMonth.getEmployeeId() == saveEmployeeId &&
                    currentYear.equals(saveYear) &&
                    currentMonth.equals(saveMonth) ) {
                cumMinutes += cellPhoneUsageByMonth.getTotalMinutes();
                cumData += cellPhoneUsageByMonth.getTotalData();
                continue;
            }

            writeDetail( saveEmployeeId, saveMonth, saveYear, cumMinutes, cumData );
            cumMinutes = 0;
            cumData = 0.0;

            saveEmployeeId = cellPhoneUsageByMonth.getEmployeeId();
            saveMonth = currentMonth;
            saveYear = currentYear;
        }

        writeDetail( saveEmployeeId, saveMonth, saveYear, cumMinutes, cumData );
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

    private void writeDetail(
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
}