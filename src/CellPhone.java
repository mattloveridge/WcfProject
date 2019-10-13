public class CellPhone
                    implements Comparable< CellPhone > {

    private int employeeId;
    private String employeeName;
    private int purchaseDate;
    private String model;

    public CellPhone( String line ) {
        String [] strArray = line.split(",");
        employeeId = Integer.parseInt(strArray[ 0 ]);
        employeeName = strArray[ 1 ];
        purchaseDate = Integer.parseInt(strArray[ 2 ]);
        model = strArray[ 3 ];
    }

    @Override
    public boolean equals( Object incoming ) {
        if ( this == incoming ) return true;
        if ( incoming == null ) return false;
        if ( this.getClass() != incoming.getClass() ) return false;
        CellPhone other = (CellPhone) incoming;

        return this.employeeId == other.employeeId;
    }

    @Override
    public int hashCode() {
        return employeeId * employeeId;
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
