import java.util.LinkedHashSet;
import java.util.Set;

public class ReportDetails {

    private Set< ReportDetail > reportDetailList = new LinkedHashSet( );

    public void addReportDetail( ReportDetail reportDetail ) { reportDetailList.add( reportDetail ); }
    public Set< ReportDetail > getReportDetailList( ) { return reportDetailList; }
}
