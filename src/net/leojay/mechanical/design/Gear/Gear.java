package net.leojay.mechanical.design.Gear;

/**
 * Project:PACKAGE_NAME
 * <p>
 * Author:Crazy_LeoJay
 * Time:下午8:12
 */

abstract class Gear {
    String materialName;//材料名称
    int HBW;//调质或正火 硬度
    int HRC;//淬火硬度

    double ra;//齿顶圆半径
    double da;//齿顶圆直径
    double rf;//齿根圆半径
    double df;//齿根圆直径
    double r;//分度圆半径
    double d;//分度圆直径
    double rb;//基圆半径
    double db;//基圆直径
    double h;//全齿高
    double ha;//齿顶高
    double hf;//齿根高
    double sk;//齿厚

    double z;//齿数
    double m;//模数
    double ya;//压力角
    double haX;//齿顶高系数
    double cX;//顶隙系数

    double p;//分度圆齿距
    double s;//分度圆齿厚
    double e;//分度圆曹宽
    double c;//顶隙
    double pb;//基圆齿距
    double qulv;//节圆处齿廓的曲率半径

    double n;//转速
    double T;//转矩
    double b = 0.0000;//齿宽
    double v = 0.0000;//齿轮圆周速度

    double power;

    void dOperation() {
        d = m * z;
    }

    void haOperation() {
        ha = haX * m;
    }

    void hfOperation() {
        hf = (haX + cX) * m;
    }

    void hOperation() {
        h = ha + hf;
//        h = (2 * haX + cX) * m;
    }

    void daOperation() {
        da = d + 2 * ha;
//        da = (z + 2 * haX);
    }

    void dfOperation() {

    }

    void dbOperation() {

    }

    void pOperation() {
    }

    void sOperation() {
    }

    void eOperation() {
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getHBW() {
        return HBW;
    }

    public void setHBW(int HBW) {
        this.HBW = HBW;
    }

    public int getHRC() {
        return HRC;
    }

    public void setHRC(int HRC) {
        this.HRC = HRC;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getRa() {
        return ra;
    }



    public double getV() {
        v = (Math.PI * d * n / (60 * 1000));
        return v;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
