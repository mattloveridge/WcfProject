import java.util.Map;

public class CellPhoneDto {

    private int employeeId;
    private String employeeName;
    private int purchaseDate;
    private String model;

    public CellPhoneDto(Map< Integer, CellPhone > map, int employeeId ) {
        CellPhoneDao cellPhoneDao = new CellPhoneDao();
        CellPhone cellPhone = cellPhoneDao.getCellPhone(map, employeeId);
        this.employeeId = cellPhone.getEmployeeId();
        this.employeeName = cellPhone.getEmployeeName();
        this.purchaseDate = cellPhone.getPurchaseDate();
        this.model = cellPhone.getModel();
    }

    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public int getPurchaseDate() { return purchaseDate; }
    public String getModel() { return model; }
}
