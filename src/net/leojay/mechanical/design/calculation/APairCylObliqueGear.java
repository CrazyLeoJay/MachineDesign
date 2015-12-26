package net.leojay.mechanical.design.calculation;

import net.leojay.mechanical.design.Gear.CylinderObliqueGear;

/**
 * Project:net.leojay.mechanical.design
 * <p>
 * Author:Crazy_LeoJay
 * Time:下午1:40
 */
public class APairCylObliqueGear {

    CylinderObliqueGear smallGear = new CylinderObliqueGear();
    CylinderObliqueGear bigGear = new CylinderObliqueGear();

    double i = 0.0000;//传动比
    double u = 0.0000;//齿数比
    double f = 0.00;//齿宽系数

    double zs = 0.000;//重合度系数
    double ys = 0.000;//重合度系数

    double Kh;//齿面接触应力
    double Kf;//齿根弯曲应力
    double Ka;//使用系数
    double Kv;//动载系数
    double Kb;//齿向载荷分布系数
    double KHa;//齿间载荷分配系数
    double KFa;//齿间载荷分配系数
    int jd = 0;//精度等级

    double faceContactRatio = 0.0000;//端面重合度
    double shaftContactRatio = 0.0000;//轴向重合度
    double totalContactRatio = 0.0000;//总重合度

    public double argsKHF100Kfa(){
        double d = shaftContactRatio / (faceContactRatio * ys);
        if (KFa > d) {
            KFa = d;
        }
        return KFa;
    }

    public void calculationContactRatio(){
        faceContactRatio = (1.88 - 3.2 * (1/smallGear.getZ() + 1/bigGear.getZ()));
        shaftContactRatio = (f * smallGear.getZ() / Math.PI) * Math.tan(smallGear.getHelixAngle());
        totalContactRatio = faceContactRatio + shaftContactRatio;
    }

    public double getTotalContactRatio() {
        return totalContactRatio;
    }

    public double getFaceContactRatio() {
        return faceContactRatio;
    }

    public double getShaftContactRatio() {
        return shaftContactRatio;
    }

    public double argsKha(){
        KHa = 1/Math.pow(zs, 2);
        return KHa;
    }

    public double argsKfa(){
        KFa = 1/ys;
        return KFa;
    }

    public double argsForKHF(){
        double Ft = 2 * smallGear.getT() / smallGear.getD();
        return Ka * Ft / smallGear.getB();
    }

    public void setKHF() {
        Kh = Ka * Kv * Ka * Kb * KHa;
        Kf = Ka * Kv * Ka * Kb * KFa;
    }

    public void setKFa(double KFa) {
        this.KFa = KFa;
    }

    public void setKHa(double KHa) {
        this.KHa = KHa;
    }

    public void setKb(double kb) {
        Kb = kb;
    }

    public void setKv(double kv) {
        Kv = kv;
    }

    public void setKa(double ka) {
        Ka = ka;
    }

    public double getKh() {
        return Kh;
    }

    public double getKf() {
        return Kf;
    }

    public double getKa() {
        return Ka;
    }

    public double getKv() {
        return Kv;
    }

    public double getKb() {
        return Kb;
    }

    public double getKHa() {
        return KHa;
    }

    public double getKFa() {
        return KFa;
    }

    public void setZs() {
        zs = Math.sqrt((4 - faceContactRatio)/3);
    }

    public void setYs() {
        ys = 0.25 + 0.75/faceContactRatio;
    }

    public double getYs() {
        return ys;
    }

    public double getZs() {
        return zs;
    }

    public int getBigZ(double z){
        double z1 = i * z;
        int z2 = (int)z1;
        if (z1 % z2 != 0){
            z2 ++;
        }
        return z2;
    }

    public double getU(){
        u = (bigGear.getZ() / smallGear.getZ());
        return u;
    }

    public double getIError(){
        return ((u - i)/i);
    }

    public CylinderObliqueGear getSmallGear() {
        return smallGear;
    }

    public CylinderObliqueGear getBigGear() {
        return bigGear;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
        smallGear.setB(i * smallGear.getD());
        bigGear.setB(i * bigGear.getD());
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public int getJd() {
        return jd;
    }

    public void setJd(int jd) {
        this.jd = jd;
    }
}
