import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneUsageByMonthDao {

    public List< CellPhoneUsageByMonth > loadCellPhoneUsageByMonthDataStore( ) {

        String file = "C:\\WCF\\CellPhoneUsageByMonth.txt";
        List< CellPhoneUsageByMonth > cellPhoneUsageByMonthList = new ArrayList<>();

        try ( Stream< String > stream = Files.lines( Paths.get( file ) ) ) {
                cellPhoneUsageByMonthList =
                    stream.
                    skip( 1 ).
                    map( line -> { return buildCellPhoneUsageByMonth( line ); } ) .
                    sorted( ).
                    collect( Collectors.toList( ) );
        } catch ( IOException ioe ) { ioe.printStackTrace( ); }

        return cellPhoneUsageByMonthList;
    }

    private CellPhoneUsageByMonth buildCellPhoneUsageByMonth( String line ) {
        String [] strArray = line.split(",");
        return new CellPhoneUsageByMonth( strArray );
    }
}
