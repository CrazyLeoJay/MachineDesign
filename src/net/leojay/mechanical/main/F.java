package net.leojay.mechanical.main;

import net.leojay.mechanical.design.Gear.CylinderObliqueGear;
import net.leojay.mechanical.design.calculation.APairCylObliqueGear;

import java.util.Scanner;

import static net.leojay.mechanical.tools.Print.*;

/**
 * Created by leojay on 15-12-18.
 */
/**
 * 开式齿轮，只对抗弯疲劳强度计算，即：先计算 小齿轮直径 d1, 并可以适当加大模数
 * 闭式齿轮，
 *      齿面为软齿面（<= 350HBW）时，先按齿面 接触疲劳   计算， 即先计算模数 m ，然后对齿面 抗弯矩疲劳 进行校核
 *      齿面为硬齿面（>  350HBW）时，先按齿面 抗弯矩疲劳 计算， 即先计算直径 d1，然后对齿面 接触疲劳   进行校核
 * */
public class F {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        println("/*********  一对斜齿轮设计计算   *********/");
        println("齿轮设计类型： 1、开式齿轮   2、闭式齿轮");
        boolean type1 = false;
        do {
            print("选择方式： ");
            type1 = false;
            switch (scanner.nextInt()) {
                case 1:
                    openGear();
                    break;
                case 2:
                    closeGear();
                    break;
                default:
                    println("输入参数有误请重新输入！！！");
                    type1 = true;
                    break;
            }
        } while (type1);
    }



    /**
     * 开式齿轮计算
     */
    static void openGear() {
        Scanner scanner = new Scanner(System.in);
        APairCylObliqueGear aPG = new APairCylObliqueGear();
        CylinderObliqueGear smallGear = aPG.getSmallGear();
        CylinderObliqueGear bigGear = aPG.getBigGear();

        println("/*********  选择的是：开式斜齿轮计算   *********/");
        println("输入已知参数：");
        print("   小齿轮转速 n(r/min):");
        smallGear.setN(scanner.nextDouble());
        print("   传动比 i:");
        aPG.setI(scanner.nextDouble());
        print("   输入功率或转矩：1、转矩  2、功率");
        boolean b = false;
        do {
            b = false;
            switch (scanner.nextInt()) {
                case 1:
                    print("   转矩 T(N/mm):");
                    smallGear.setT(scanner.nextDouble());
                    break;
                case 2:
                    print("   功率 P(KW):");
                    smallGear.setPower(scanner.nextDouble());
                    smallGear.setT(smallGear.calculationT());
                    break;
                default:
                    print("输入错误，重新选择！！");
                    b = true;
                    break;
            }
        } while (b);


        println("1、确定材料和基本参数" +
                "   （1）选材料（P483 表18-4 到 18-6）");

        print("输入小齿轮的材料：");
        smallGear.setMaterialName(scannerString());
        print("输入该材料硬度 HBW ：");
        smallGear.setHBW(scanner.nextInt());

        print("输入大齿轮的材料：");
        bigGear.setMaterialName(scannerString());
        print("输入该材料硬度 HBW ：");
        bigGear.setHBW(scanner.nextInt());

        println("   （1）初选齿数" +
                "       建议：选取 17 ～ 25");//开式选17 ～25 闭式选20 ～ 40
        do {
            print("   小齿轮齿数 Z1 = ");
            smallGear.setZ(scanner.nextInt());
            bigGear.setZ(aPG.getBigZ(smallGear.getZ()));
            double z2 = bigGear.getZ();
            println("   计算：");
            println("   大齿轮齿数为 Z2 = ：" + z2);
            double u = aPG.getU();
            println("   齿数比 u = ：" + u);
            double iError = aPG.getIError();
            println("   传动比误差： " + iError + "%");
            print("   误差是否允许？：（1、允许    非1、不允许，将重新估计）");
        } while (scanner.nextInt() == 1);

        println("   (2) 选择齿宽系数(  根据 表 18-12  P197)");
        print("       选取 ：");
        aPG.setF(scanner.nextDouble());
        print("   (3) 估计小齿轮直径：");
        smallGear.setD(scanner.nextDouble());
        print("       初估：\n" +
                "           速度 v：" + smallGear.getV() +
                "\n           选择精度等级(P180 表18 - 3) ：");
        aPG.setJd(scanner.nextInt());
        print("   (4) 转矩 T ：" + smallGear.getT());

        println("   (5) 重合度计算");//只有斜齿轮
        print("       设 螺旋升角 :");
        smallGear.setHelixAngle(scanner.nextDouble());
        aPG.calculationContactRatio();
        println("       计算得 端面重合度： " + aPG.getFaceContactRatio());
        println("       计算得 轴向重合度： " + aPG.getShaftContactRatio());
        println("       计算得   总重合度： " + aPG.getTotalContactRatio());
        aPG.setZs();
        aPG.setYs();
        println("       重合度数系数 Zs：" + aPG.getZs() +
                "\n                  Ys:" + aPG.getYs());

        println("   (6) 确定载荷系数 Kh 、Kf");
        print("         读表18-7 P489选取 Ka ：");
        aPG.setKa(scanner.nextDouble());
        println("         参数： v = " + smallGear.getV() + "    读图18-6 P490");
        print("         选取 Kv ：");
        aPG.setKv(scanner.nextDouble());
        println("         参数：齿宽系数： " + aPG.getF() + "     读图18-8 P490");
        print("         选取 Kb ：");
        aPG.setKb(scanner.nextDouble());
        double kfb = aPG.argsForKHF();
        int jd = aPG.getJd();
        if (kfb >= 100 && jd <= 8) {
            println("         参数：精度等级 ：" + aPG.getJd() + "    Ka*Ft/b >= 100");

        } else {
            println("         参数：精度等级 ：" + aPG.getJd() + "    Ka*Ft/b <  100");
            println("         若所得值小于 1.2  则取  1.2");
            print("         是否表面硬化？（1、是     2、否）");
            switch (scanner.nextInt()) {
                case 1:
                    println("         计算 Kha = 1/(Zs * Zs) = " + aPG.argsKha());
                    println("         计算 Kfa = 1/y = " + aPG.argsKfa());
                    break;
                case 2:
                    println("         计算 Kha = 1/(Zs * Zs) = " + aPG.argsKha());
                    println("         计算 Kfa =  " + aPG.argsKHF100Kfa());
                    break;
            }
        }
        println("         参照之前所得值查表选取以下值");
        print("         选取 Kha :");
        aPG.setKHa(scanner.nextDouble());
        print("         选取 Kfa :");
        aPG.setKFa(scanner.nextDouble());
        aPG.setKHF();
        println("         计算得 Kh ：" + aPG.getKh());
        println("         计算得 Kf ：" + aPG.getKf());

        
    }

    static void closeGear() {
        println("/*********  选择的是：闭式齿轮计算   *********/");
    }

    static String scannerString() {
        Scanner scanner = new Scanner(System.in);
        String s = null;
        do {
            s = scanner.nextLine();
        } while (s.trim().isEmpty());
        return s;
    }

}
