import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneUsageDao {

    public static List< CellPhoneUsageDto > loadCellPhoneUsageDataStore() {
        final String file = "C:\\WCF\\CellPhoneUsage.txt";
        final int currentYear = 2018;
        List< CellPhoneUsageDto > cellPhoneUsageDtos = new ArrayList<>();
        Stream< String > stream = null;

        try {
            stream = Files.lines(Paths.get(file) );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        List< CellPhoneUsageDto > list =
            stream.
            skip( 1 ).
            map( line -> { return new CellPhoneUsageDto( line ); } ) .
            filter(cellPhoneUsageDto -> getYear(cellPhoneUsageDto) == currentYear).
            sorted( ).
            collect( Collectors.toList( ) );

        return list;
    }

    private static int getYear( CellPhoneUsageDto cellPhoneUsageDto) {
        final String date = cellPhoneUsageDto.getDate();
        final String year = date.substring(
                    cellPhoneUsageDto.getDate().length() - 4,
                    cellPhoneUsageDto.getDate().length());
        return Integer.parseInt(year);
    }
}
