package com.mk.framework.coordinate;

/**
 * Created by huangjie on 16/6/7.
 */
public class CoordinateUtils {

    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    /**
     * GCJ-02(高德) 坐标转换成 BD-09(百度) 坐标
     * @param coordinate
     * @return
     */
    public static Coordinate encrypt(Coordinate coordinate)
    {
        Coordinate result = new Coordinate();

        //
        double x = coordinate.getLon(), y = coordinate.getLat();
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        result.setLon(z * Math.cos(theta) + 0.0065);
        result.setLat(z * Math.sin(theta) + 0.006);

        return result;
    }

    /**
     * BD-09(百度) 坐标转换成 GCJ-02(高德) 坐标
     * @param coordinate
     * @return
     */
    public static Coordinate decrypt(Coordinate coordinate)
    {
        Coordinate result = new Coordinate();
        //
        double x = coordinate.getLon() - 0.0065, y = coordinate.getLat() - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        result.setLon(z * Math.cos(theta));
        result.setLat(z * Math.sin(theta));

        return result;
    }


    public static void main(String []args){
        //测试坐标 丽江吕家四合院客栈
        Coordinate coordinate = new Coordinate();

        coordinate.setLon(100.245263);
        coordinate.setLat(26.872884);

        Coordinate result =CoordinateUtils.decrypt(coordinate);

        System.out.println(result);
    }
}
