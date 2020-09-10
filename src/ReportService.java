import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;

public class ReportService {

    private static String reportHeader = " Report Run Date     Number of Phones     Total Minutes     " +
            "Total Data     Average Minutes     Average Data";

    private static String detailHeader = " Employee Id\tEmployee Name\t\t\tModel\t\t\t\tPurchase Date\t" +
            "Minutes Usage\tData Usage";

    private static void printHeader( ) { System.out.println( reportHeader ); }

    public void createReport( Map< Integer, CellPhoneDto > cellPhoneDtos,
                                                List< CellPhoneUsageDto > cellPhoneUsageDtos ) {
        ReportDetails reportDetails = processUsageRecords( cellPhoneDtos, cellPhoneUsageDtos );

        printHeader( );

        writeReport( reportDetails );
    }

    private ReportDetails processUsageRecords( Map< Integer, CellPhoneDto > cellPhoneDtos,
                                                    List< CellPhoneUsageDto > cellPhoneUsageDtos ) {

        int currentEmployeeId = cellPhoneUsageDtos.get(0).getEmployeeId(),
                saveEmployeeId = currentEmployeeId;
        ReportDetail reportDetail = new ReportDetail( );
        ReportDetails reportDetails = new ReportDetails( );
        CellPhoneDto cellPhoneDto = CellPhoneDto.getCellPhoneDto( cellPhoneDtos, saveEmployeeId );
        reportDetail.setCellPhoneDto( cellPhoneDto );

        for (CellPhoneUsageDto cellPhoneUsageDto : cellPhoneUsageDtos ) {
            currentEmployeeId = cellPhoneUsageDto.getEmployeeId( );
            if ( currentEmployeeId != saveEmployeeId ) {
                reportDetails.addReportDetail( reportDetail );
                reportDetail = new ReportDetail( );
                cellPhoneDto = CellPhoneDto.getCellPhoneDto( cellPhoneDtos, currentEmployeeId );
                reportDetail.setCellPhoneDto( cellPhoneDto );
                accumulateInReportDetail( cellPhoneUsageDto, reportDetail );
                saveEmployeeId = currentEmployeeId;
                continue;
            }
            accumulateInReportDetail( cellPhoneUsageDto, reportDetail );
        }
        reportDetails.addReportDetail( reportDetail );

        return reportDetails;
    }

    private void accumulateInReportDetail( CellPhoneUsageDto cellPhoneUsageDto, ReportDetail reportDetail ) {
        reportDetail.addToEmployeeTotalMinutes( cellPhoneUsageDto.getMinutes( ) );
        reportDetail.addToEmployeeTotalUsage( cellPhoneUsageDto.getUsage( ) );
        reportDetail.addToMonthsMinutes( getMonth(cellPhoneUsageDto), cellPhoneUsageDto.getMinutes() );
        reportDetail.addToMonthsUsage( getMonth(cellPhoneUsageDto), cellPhoneUsageDto.getUsage() );
    }

    private int getMonth( CellPhoneUsageDto cellPhoneUsageDto) {
        String month = cellPhoneUsageDto.getDate().substring(1, 2).equals("/") ?
                cellPhoneUsageDto.getDate().substring(0, 1) :
                cellPhoneUsageDto.getDate().substring(0, 2);
        return Integer.parseInt( month );
    }

    public void writeReport( ReportDetails reportDetails ) {
        String [] months = { "Jan", "Feb", "Mar", "Apr", "May",
                    "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        String minutesUsageHeader = "     Minutes  Usage";

        writeHeaderAndDetail( reportDetails );

        System.out.println();
        System.out.println(detailHeader);

        for ( ReportDetail reportDetail : reportDetails.getReportDetailCollection( ) ) {
            writeDetailLine( reportDetail );
            System.out.println( minutesUsageHeader );
            for ( int counter = 0; counter < 12; counter++ ) {
                System.out.println( months[ counter ] + ":   " + reportDetail.getMonthsMinutes()[ counter ] +
                    "    " + reportDetail.getMonthsUsage()[ counter ] );//aaa
//                sb.append( reportDetail.getMonthsMinutes()[ counter ] + tab2 );
            }
            DecimalFormat df = new DecimalFormat("###.###");
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

        for ( ReportDetail reportDetail : reportDetails.getReportDetailCollection() ) {
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
