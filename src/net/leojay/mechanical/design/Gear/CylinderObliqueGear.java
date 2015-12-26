package net.leojay.mechanical.design.Gear;

/**
 * Project:net.leojay.mechanical.design
 * <p>
 * Author:Crazy_LeoJay
 * Time:下午12:42
 */

/**
 * 圆柱斜齿轮
 */
public class CylinderObliqueGear extends Gear {

    double bta;//螺旋角系数
    static double helixAngle;//分度圆螺旋角

    public CylinderObliqueGear() {
    }

    public double calculationT() {
        return (9.55e6 * p /super.n);
    }

    public static double getHelixAngle() {
        return helixAngle;
    }

    public static void setHelixAngle(double helixAngle) {
        CylinderObliqueGear.helixAngle = helixAngle;
    }
}
