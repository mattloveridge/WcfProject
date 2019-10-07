import java.util.List;
import java.util.Map;

public class Controller {

    public void createReportControl( ){

        Map< Integer, CellPhone > cellPhones = new CellPhoneDao( ).loadCellPhoneDataStore( );
        List< CellPhoneUsageByMonth > cellPhoneUsageByMonths = new CellPhoneUsageByMonthDao( ).
                                                                        loadCellPhoneUsageByMonthDataStore( );
        createReport( cellPhones, cellPhoneUsageByMonths );
    }

    private void createReport( Map<Integer, CellPhone> cellPhones,
                                    List<CellPhoneUsageByMonth> cellPhoneUsageByMonths ) {
        int saveEmployeeId = -1;
        String saveMonth = "",
                saveYear = "";
        int cumMinutes = 0;
        double cumData = 0.0;
        boolean onOne = true;

        for (CellPhoneUsageByMonth cellPhoneUsageByMonth : cellPhoneUsageByMonths) {

            if ( onOne ) {
                saveEmployeeId = cellPhoneUsageByMonth.getEmployeeId();
                saveMonth = cellPhoneUsageByMonth.getDate().substring(1, 2).equals("/") ?
                        "0" + cellPhoneUsageByMonth.getDate().substring(0, 1) :
                        cellPhoneUsageByMonth.getDate().substring(0, 2);
                saveYear = cellPhoneUsageByMonth.getDate().substring(
                        cellPhoneUsageByMonth.getDate().length() - 4,
                        cellPhoneUsageByMonth.getDate().length());
                onOne = false;
            }

            if ( cellPhoneUsageByMonth.getEmployeeId() == saveEmployeeId &&
                    cellPhoneUsageByMonth.getDate().substring(
                            cellPhoneUsageByMonth.getDate().length() - 4).equals(saveYear) &&
                    cellPhoneUsageByMonth.getDate().substring(0, 2).equals(saveMonth) ) {
                cumMinutes += cellPhoneUsageByMonth.getTotalMinutes();
                cumData += cellPhoneUsageByMonth.getTotalData();
                continue;
            }

            writeDetail( saveEmployeeId, saveMonth, saveYear, cumMinutes, cumData );
            cumMinutes = 0;
            cumData = 0.0;

            saveEmployeeId = cellPhoneUsageByMonth.getEmployeeId();
            saveMonth = cellPhoneUsageByMonth.getDate().substring(1, 2).equals("/") ?
                    "0" + cellPhoneUsageByMonth.getDate().substring(0, 1) :
                    cellPhoneUsageByMonth.getDate().substring(0, 2);
            saveYear = cellPhoneUsageByMonth.getDate().substring(
                    cellPhoneUsageByMonth.getDate().length() - 4,
                    cellPhoneUsageByMonth.getDate().length());
        }

        if ( saveEmployeeId != -1 ) {
            writeDetail( saveEmployeeId, saveMonth, saveYear, cumMinutes, cumData );
        }
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