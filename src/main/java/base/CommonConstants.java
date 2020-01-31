package base;

public final class CommonConstants {

    public static double getTol(){return CommonConstants.tol;}
    public static void setTol(double tol){CommonConstants.tol = tol;}

    protected static double tol = 1.0e-8;
    private CommonConstants(){}


}
