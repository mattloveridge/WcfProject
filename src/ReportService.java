import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReportService {

    private static String reportHeader = " Report Run Date     Number of Phones     Total Minutes     " +
            "Total Data     Average Minutes     Average Data";

    private static String detailHeader = " Employee Id\tEmployee Name\t\t\tModel\t\t\t\tPurchase Date\t" +
            "Minutes Usage\tData Usage";

    private static String monthsHeader =
            "\t\t\tJan\t\t" +
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
            "Dec\t\t" +
            "\n";

    public static void printHeader( ) { System.out.println( reportHeader ); }

    public void createReport( Optional<Map< Integer, CellPhone >> optionalCellPhones,
                               Optional<List<CellPhoneUsage>> optionalCellPhoneUsage ) {

        int currentEmployeeId = optionalCellPhoneUsage.get( ).get(0).getEmployeeId(),
                saveEmployeeId = currentEmployeeId;
        ReportDetail reportDetail = new ReportDetail( );
        ReportDetails reportDetails = new ReportDetails( );

        printHeader( );

        for (CellPhoneUsage cellPhoneUsage : optionalCellPhoneUsage.get( ) ) {
            currentEmployeeId = cellPhoneUsage.getEmployeeId( );
            if (currentEmployeeId != saveEmployeeId) {
                CellPhoneDto cellPhoneDto = new CellPhoneDto( optionalCellPhones.get( ), saveEmployeeId );
                reportDetail.setCellPhoneDto( cellPhoneDto );
                reportDetails.addReportDetail( reportDetail );
                reportDetail = new ReportDetail( );
                reportDetail.addToEmployeeTotalMinutes( cellPhoneUsage.getMinutes( ) );
                reportDetail.addToEmployeeTotalUsage( cellPhoneUsage.getUsage( ) );
                reportDetail.addToMonthsMinutes( getMonth( cellPhoneUsage ), cellPhoneUsage.getMinutes( ) );
                reportDetail.addToMonthsUsage( getMonth( cellPhoneUsage ), cellPhoneUsage.getUsage( ) );
                saveEmployeeId = currentEmployeeId;
                continue;
            }
            reportDetail.addToEmployeeTotalMinutes( cellPhoneUsage.getMinutes( ) );
            reportDetail.addToEmployeeTotalUsage( cellPhoneUsage.getUsage( ) );
            reportDetail.addToMonthsMinutes( getMonth(cellPhoneUsage), cellPhoneUsage.getMinutes() );
            reportDetail.addToMonthsUsage( getMonth(cellPhoneUsage), cellPhoneUsage.getUsage() );
        }

        CellPhoneDto cellPhoneDto = new CellPhoneDto( optionalCellPhones.get(), saveEmployeeId );
        reportDetail.setCellPhoneDto( cellPhoneDto );
        reportDetails.addReportDetail( reportDetail );

        writeReport( reportDetails );
    }

    private int getMonth( CellPhoneUsage cellPhoneUsage) {
        String month = cellPhoneUsage.getDate().substring(1, 2).equals("/") ?
                cellPhoneUsage.getDate().substring(0, 1) :
                cellPhoneUsage.getDate().substring(0, 2);
        return Integer.parseInt( month );
    }

    public void writeReport( ReportDetails reportDetails ) {

        writeHeaderAndDetail( reportDetails );

        System.out.println();
        System.out.println(detailHeader);
        System.out.println(monthsHeader);

        for ( ReportDetail reportDetail : reportDetails.getReportDetailList( ) ) {
            writeDetailLine( reportDetail );
            StringBuilder sb = new StringBuilder();
            sb.append( "minutes:\t");
            String tab2 = "\t\t";
            for ( int counter = 0; counter < 11; counter++ ) {
                sb.append( reportDetail.getMonthsMinutes()[ counter ] + tab2 );
            }
            sb.append( reportDetail.getMonthsMinutes( )[ 11 ]);
            System.out.println( sb );
            sb = new StringBuilder( );
            sb.append( "usage:\t\t");
            DecimalFormat df = new DecimalFormat("###.###");
            for ( int counter = 0; counter < 11; counter++ ) {
                sb.append( df.format( reportDetail.getMonthsUsage()[ counter ] ) + tab2 );
            }
            sb.append( reportDetail.getMonthsUsage( )[ 11 ]);
            System.out.println( sb );
            System.out.println( );
        }
    }

    private void writeDetailLine( ReportDetail reportDetail ) {
        String tab1 = "\t",
                tab2 = "\t\t",
                tab3 = "\t\t\t",
                tab4 = "\t\t\t\t",
                tab5 = "\t\t\t\t\t";

        DecimalFormat df = new DecimalFormat("###.###");

        System.out.println(
                tab1 +
                        reportDetail.getCellPhoneDto().getEmployeeId( ) +
                        tab2 +
                        reportDetail.getCellPhoneDto().getEmployeeName( ) +
                        tab3 +
                        reportDetail.getCellPhoneDto().getModel( ) +
                        tab1 +
                        reportDetail.getCellPhoneDto().getPurchaseDate( ) +
                        tab2 +
                        reportDetail.getEmployeeTotalMinutes( ) +
                        tab4 +
                        df.format( reportDetail.getEmployeeTotalUsage( ) ) );
    }

    private void writeHeaderAndDetail( ReportDetails reportDetails ) {
        int totalMinutes = 0,
                totalPhones = 0;
        double totalUsage = 0.0;

        for ( ReportDetail reportDetail : reportDetails.getReportDetailList() ) {
            totalPhones++;
            for ( int minutes : reportDetail.getMonthsMinutes( ) ) { totalMinutes += minutes; }
            for ( double usage : reportDetail.getMonthsUsage( ) ) { totalUsage += usage; }
        }

        String tab1 = "\t",
                tab2 = "\t\t",
                tab3 = "\t\t\t",
                tab4 = "\t\t\t\t",
                tab5 = "\t\t\t\t\t";

        DecimalFormat df = new DecimalFormat("###.###");

        System.out.println(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now()) +
                        tab3 +
                        totalPhones +
                        tab4 +
                        totalMinutes +
                        tab4 +
                        df.format( totalUsage ) +
                        tab3 +
                        totalMinutes / totalPhones +
                        tab4 +
                        df.format( totalUsage / totalPhones ) );
    }
}
