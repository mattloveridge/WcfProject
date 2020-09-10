import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneDao {

    public Map< Integer, CellPhoneDto > loadCellPhoneDataStore(){
        final String file = "C:\\WCF\\CellPhone.txt";
        Stream< String > stream = null;

        try {
            stream = Files.lines( Paths.get( file ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        Map< Integer, CellPhoneDto > map =
                stream.
                skip( 1 ).
                map( line -> { return new CellPhoneDto( line ); } ).
                collect( Collectors.toMap( cellPhone -> cellPhone.getEmployeeId(),
                        cellPhone -> cellPhone,
                        (existing, replacement) -> existing) );

        return map;
    }
}
