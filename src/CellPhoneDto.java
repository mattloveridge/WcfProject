import java.util.Map;

public class CellPhoneDto {

    private int employeeId;
    private String employeeName;
    private int purchaseDate;
    private String model;

    public CellPhoneDto( CellPhone cellPhone ) {
        this.employeeId = cellPhone.getEmployeeId();
        this.employeeName = cellPhone.getEmployeeName();
        this.purchaseDate = cellPhone.getPurchaseDate();
        this.model = cellPhone.getModel();
    }

    public CellPhoneDto( Map< Integer, CellPhone > map, int employeeId ) {
        CellPhoneDao cellPhoneDao = new CellPhoneDao();
        CellPhone cellPhone = cellPhoneDao.getCellPhone( map, employeeId );
        new CellPhoneDto( cellPhone );
    }

/*
    public CellPhoneDto getCellPhoneDto( ) {
        CellPhone cellPhone = map.get(employeeId);
        if ( cellPhone == null ) {
            throw new RuntimeException( "CellPhone record not found for employeeId = " + employeeId );
        }

        return new CellPhoneDto(cellPhone);
    }
*/

/*
    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public int getPurchaseDate() { return purchaseDate; }

    public void setPurchaseDate(int purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }
*/
}
