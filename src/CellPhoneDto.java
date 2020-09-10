import java.util.Map;

public class CellPhoneDto {

    private int employeeId;
    private String employeeName;
    private int purchaseDate;
    private String model;

    public CellPhoneDto( String line ) {
        String [] strArray = line.split(",");
        employeeId = Integer.parseInt(strArray[ 0 ]);
        employeeName = strArray[ 1 ];
        purchaseDate = Integer.parseInt(strArray[ 2 ]);
        model = strArray[ 3 ];
    }

    public static CellPhoneDto getCellPhoneDto(Map< Integer, CellPhoneDto > map, int employeeId ) {
        return map.get( employeeId );
    }

    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public int getPurchaseDate() { return purchaseDate; }
    public String getModel() { return model; }
}
