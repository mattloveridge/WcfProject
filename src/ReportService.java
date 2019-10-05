public class ReportService {

    private static String header = " Report Run Date     Number of Phones     Total Minutes     " +
            "Total Data     Average Minutes     Average Data";

    private static String detail = " Employee Id     Employee Name     Model     Purchase Date     " +
            "Minutes Usage     Data Usage";

    private static String newLine = "\n";

    public static void printHeader(){
        System.out.println(header);
        System.out.println(newLine);
        System.out.println(detail);
    }



}
