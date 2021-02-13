package jstat.base;

public final class CommonConstants {

    public static double getTol(){return CommonConstants.tol;}
    public static void setTol(double tol){CommonConstants.tol = tol;}

    public static String examplesPath(){
        String path = "src/main/java/examples";
        return path;
    }

    public static String mcExamplesPath(){
        String path =  CommonConstants.examplesPath();
        return path + "/mc";
    }

    protected static double tol = 1.0e-8;
    private CommonConstants(){}


}
