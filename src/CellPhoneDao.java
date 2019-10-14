import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
`import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneDao {

    public Optional< Map< Integer, CellPhone > > loadCellPhoneDataStore( ){
        String file = "C:\\WCF\\CellPhone.txt";

        try ( Stream< String > stream = Files.lines( Paths.get( file ) ) ) {
            return Optional.of(stream.
                    skip( 1 ).
                    map( line -> { return new CellPhone( line ); } ) .
                    collect( Collectors.toMap( cellPhone -> cellPhone.getEmployeeId(),
                                                                        cellPhone -> cellPhone,
                                                                        (existing, replacement) -> existing) ) );
        } catch ( IOException ioe ) { ioe.printStackTrace(); }

        return null;
    }

    public CellPhone getCellPhone( Map< Integer, CellPhone > map, int employeeId ) {
        CellPhone cellPhone = map.get( employeeId );
        if ( cellPhone == null ) {
            throw new RuntimeException( "CellPhone record not found for employeeId = " + employeeId );
        }
        return cellPhone;
    }
}
