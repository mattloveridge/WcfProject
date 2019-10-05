import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CellPhoneDao {

    public Map<Integer, CellPhone> loadCellPhoneDataStore(){

        String file = "C:\\WCF\\CellPhone.txt";

        Map<Integer, CellPhone> cellPhoneMap = null;

        try (Stream<String> stream = Files.lines(Paths.get(file))) {

            cellPhoneMap = stream.skip(1).map(line -> {
                return buildCellPhone(line);
                } ) .
                    collect(Collectors.toMap(cellPhone -> cellPhone.getEmployeeId(), cellPhone -> cellPhone));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return cellPhoneMap;
    }

    private CellPhone buildCellPhone(String line){
        String [] strArray = line.split(",");
        return new CellPhone(strArray);
    }
}
