import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Controller {

    public void createReportControl( ){

        Optional< Map< Integer, CellPhone > > optionalCellPhones = new CellPhoneDao( ).loadCellPhoneDataStore( );
        Optional< List<CellPhoneUsage> > optionalCellPhoneUsage = new CellPhoneUsageDao( ).
                                                                            loadCellPhoneUsageDataStore( );
        checkCellPhonesToContinue( optionalCellPhones );
        checkCellPhoneUsageToContinue( optionalCellPhoneUsage );
        new ReportService().createReport( optionalCellPhones, optionalCellPhoneUsage );
    }

    private void checkCellPhonesToContinue( Optional< Map< Integer, CellPhone > > optionalCellPhones ) {
        if ( ! optionalCellPhones.isPresent( ) ) {
            throw new RuntimeException( "checkCellPhonesToContinue: optionalCellPhones is not present" );
        }

        if ( optionalCellPhones.get().isEmpty( ) ) {
            throw new RuntimeException( "optionalCellPhones map is empty" );
        }
    }

    private void checkCellPhoneUsageToContinue(
                            Optional< List < CellPhoneUsage > > optionalCellPhoneUsage ) {
        if ( ! optionalCellPhoneUsage.isPresent( ) ) {
            throw new RuntimeException(
                    "checkCellPhoneUsageToContinue: cellPhoneUsage is not present" );
        }

        if ( optionalCellPhoneUsage.get().isEmpty( ) ) {
            throw new RuntimeException(
                    "checkCellPhoneUsageToContinue: cellPhoneUsage is empty" );
        }
    }
}