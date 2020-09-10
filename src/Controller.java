import java.util.*;

public class Controller {

    public void createReportControl( ){

        Map< Integer, CellPhoneDto > cellPhoneDtos = loadCellPhoneDtos();
        checkCellPhonesToContinue( cellPhoneDtos );

        List< CellPhoneUsageDto > cellPhoneUsageDtos =
            new CellPhoneUsageDao( ).loadCellPhoneUsageDataStore();
        checkCellPhoneUsageToContinue( cellPhoneUsageDtos );

        new ReportService().createReport( cellPhoneDtos, cellPhoneUsageDtos );
    }

    private Map< Integer, CellPhoneDto > loadCellPhoneDtos() {
        return new CellPhoneDao( ).loadCellPhoneDataStore( );
    }

    private void checkCellPhonesToContinue( Map< Integer, CellPhoneDto > cellPhoneDtos ) {
        if ( cellPhoneDtos.size() == 0 ) {
            throw new RuntimeException( "checkCellPhonesToContinue: optionalCellPhones is not present" );
        }
    }

    private void checkCellPhoneUsageToContinue( List < CellPhoneUsageDto > cellPhoneUsageDtos ) {
        if ( cellPhoneUsageDtos.size() == 0 ) {
            throw new RuntimeException(
                    "checkCellPhoneUsageToContinue: cellPhoneUsage is not present" );
        }
    }
}