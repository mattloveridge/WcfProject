import java.util.LinkedHashSet;
import java.util.Set;

public class ReportDetails {

    private Set< ReportDetail > reportDetailCollection = new LinkedHashSet( );

    public void addReportDetail( ReportDetail reportDetail ) { reportDetailCollection.add( reportDetail ); }
    public Set< ReportDetail > getReportDetailCollection( ) { return reportDetailCollection; }
}
