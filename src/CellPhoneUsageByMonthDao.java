import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneUsageByMonthDao {

    public Optional< List< CellPhoneUsageByMonth > > loadCellPhoneUsageByMonthDataStore( ) {
        String file = "C:\\WCF\\CellPhoneUsageByMonth.txt";
//      int currentYear = LocalDate.now().getYear();
        final int currentYear = 2018;

        try ( Stream< String > stream = Files.lines( Paths.get( file ) ) ) {
            return Optional.of(
                    stream.
                    skip( 1 ).
                    map( line -> { return new CellPhoneUsageByMonth( line ); } ) .
                    filter( cellPhoneUsageByMonth -> getYear(cellPhoneUsageByMonth) == currentYear).
                    sorted( ).
                    collect( Collectors.toList( ) ) );
        } catch ( IOException ioe ) { ioe.printStackTrace( ); }

        return Optional.ofNullable(null);
    }

    private int getYear( CellPhoneUsageByMonth cellPhoneUsageByMonth ) {
        String date = cellPhoneUsageByMonth.getDate();
        String year = date.substring(
                    cellPhoneUsageByMonth.getDate().length() - 4,
                    cellPhoneUsageByMonth.getDate().length());
        return Integer.parseInt(year);
    }
}
