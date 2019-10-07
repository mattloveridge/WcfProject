public class CellPhone
                    implements Comparable< CellPhone > {

    private int employeeId;
    private String employeeName;
    private int purchaseDate;
    private String model;

    public CellPhone( String [] strArray ) {
        employeeId = Integer.parseInt(strArray[ 0 ]);
        employeeName = strArray[ 1 ];
        purchaseDate = Integer.parseInt(strArray[ 2 ]);
        model = strArray[ 3 ];
    }

    public int getEmployeeId( ) { return employeeId; }

    public String getEmployeeName( ) { return employeeName; }

    public int getPurchaseDate( ) { return purchaseDate; }

    public String getModel( ) { return model; }

    @Override
    public int compareTo( CellPhone other ) {
        return this.getEmployeeId( ) - other.getEmployeeId( );
    }
}
