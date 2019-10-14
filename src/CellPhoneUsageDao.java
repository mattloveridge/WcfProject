import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneUsageDao {

    public Optional< List< CellPhoneUsage > > loadCellPhoneUsageDataStore( ) {
        String file = "C:\\WCF\\CellPhoneUsage.txt";
        final int currentYear = 2018;

        try ( Stream< String > stream = Files.lines( Paths.get( file ) ) ) {
            return Optional.of(
                    stream.
                    skip( 1 ).
                    map( line -> { return new CellPhoneUsage( line ); } ) .
                    filter(cellPhoneUsage -> getYear(cellPhoneUsage) == currentYear).
                    sorted( ).
                    collect( Collectors.toList( ) ) );
        } catch ( IOException ioe ) { ioe.printStackTrace( ); }

        return Optional.ofNullable( null );
    }

    private int getYear( CellPhoneUsage cellPhoneUsage) {
        String date = cellPhoneUsage.getDate();
        String year = date.substring(
                    cellPhoneUsage.getDate().length() - 4,
                    cellPhoneUsage.getDate().length());
        return Integer.parseInt(year);
    }
}
