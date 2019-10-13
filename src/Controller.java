import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Controller {

    public void createReportControl( ){

        Optional< Map< Integer, CellPhone > > optionalCellPhones = new CellPhoneDao( ).loadCellPhoneDataStore( );
        Optional< List< CellPhoneUsageByMonth > > optionalCellPhoneUsageByMonths = new CellPhoneUsageByMonthDao( ).
                                                                loadCellPhoneUsageByMonthDataStore( );
        checkCellPhonesToContinue( optionalCellPhones );
        checkCellPhoneUsageByMonthsToContinue( optionalCellPhoneUsageByMonths );
        new ReportService().createReport( optionalCellPhones, optionalCellPhoneUsageByMonths );
    }

    private void checkCellPhonesToContinue( Optional< Map< Integer, CellPhone > > optionalCellPhones ) {
        if ( ! optionalCellPhones.isPresent( ) ) {
            throw new RuntimeException( "checkCellPhonesToContinue: optionalCellPhones is not present" );
        }

        if ( optionalCellPhones.get().isEmpty ( ) ) {
            throw new RuntimeException( "optionalCellPhones map is empty" );
        }
    }

    private void checkCellPhoneUsageByMonthsToContinue( Optional< List < CellPhoneUsageByMonth > > optionalCellPhoneUsageByMonths ) {
        if ( ! optionalCellPhoneUsageByMonths.isPresent( ) ) {
            throw new RuntimeException(
                    "checkCellPhoneUsageByMonthsToContinue: cellPhoneUsageByMonths is not present" );
        }

        if ( optionalCellPhoneUsageByMonths.get().isEmpty( ) ) {
            throw new RuntimeException(
                    "checkCellPhoneUsageByMonthsToContinue: cellPhoneUsageByMonths is empty" );
        }
    }
}