public class Application {

    public static void main(String [] array) {
        ReportService.printHeader();
        new Controller().createReportControl();
    }
}
