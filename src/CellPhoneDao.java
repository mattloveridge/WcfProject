import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneDao {

    public Map<Integer, CellPhone> loadCellPhoneDataStore( ){

        String file = "C:\\WCF\\CellPhone.txt";
        Map<Integer, CellPhone> cellPhones = new TreeMap<>();

        try ( Stream<String> stream = Files.lines(Paths.get( file ) ) ) {
            cellPhones = stream.
                    skip( 1 ).
                    map(line -> { return buildCellPhone(line); } ) .
                    collect( Collectors.toMap( cellPhone -> cellPhone.getEmployeeId(),
                                                                        cellPhone -> cellPhone,
                                                                        ( o1, o2 ) -> o1,
                                                                        TreeMap::new ) );
        } catch ( IOException ioe ) { ioe.printStackTrace(); }

        return cellPhones;
    }

    private CellPhone buildCellPhone( String line ) {
        String [] strArray = line.split(",");
        return new CellPhone( strArray );
    }
}
