package com.yurka.sergeant_matatov.assemblercalculator;

/**
 * Created by Yurka on 05.01.2017.
 */

public class MathematicalOperations {

    AllFormats allFormats;

    public int operationPlus(int num1, int num2) {
        return num1 + num2;
    }

    public int operationkMinus(int num1, int num2) {
        return num1 - num2;
    }

    public int operationMul(int num1, int num2) {
        return num1 * num2;
    }

    public int operationDiv(int num1, int num2) {
        int res = 0;
        String resHexInt = "";
        String resHexResidue = "";
        String resAll = "";
        int mul = 0;
        int sub = 0;
        res = num1 / num2;     //целое
        mul = num2 * res;      //целое на делитель
        sub = num1 - mul;       //разница для получение остатка

        //целое
        if (res > 15) {
            if (res == 10)
                resHexInt = "a";
            if (res == 11)
                resHexInt = "b";
            if (res == 12)
                resHexInt = "c";
            if (res == 13)
                resHexInt = "d";
            if (res == 14)
                resHexInt = "e";
            if (res == 15)
                resHexInt = "f";
            if (res >= 16)
                resHexInt = Integer.toHexString(res);
        } else
            resHexInt = Integer.toHexString(res);

        //остаток
        if (sub > 15) {
            if (sub == 10)
                resHexResidue = "a";
            if (sub == 11)
                resHexResidue = "b";
            if (sub == 12)
                resHexResidue = "c";
            if (sub == 13)
                resHexResidue = "d";
            if (sub == 14)
                resHexResidue = "e";
            if (sub == 15)
                resHexResidue = "f";
            if (sub >= 16)
                resHexResidue = Integer.toHexString(sub);
        } else
            resHexResidue = Integer.toHexString(sub);

        if (resHexResidue.length() == 1 && resHexInt.length() == 1 || resHexResidue.length() == 2 && resHexInt.length() == 1) {
            if (num2 >= 256)
                resHexInt = "000" + resHexInt;
            else
                resHexInt = "0" + resHexInt;
        }

        if (resHexResidue.length() == 3 && resHexInt.length() == 1 || resHexResidue.length() == 4 && resHexInt.length() == 1)
            resHexInt = "000" + resHexInt;

        if (resHexResidue.length() == 3 && resHexInt.length() == 2 || resHexResidue.length() == 4 && resHexInt.length() == 2)
            resHexInt = "00" + resHexInt;

        if (resHexResidue.length() == 1 && resHexInt.length() == 3 || resHexResidue.length() == 2 && resHexInt.length() == 3 || resHexResidue.length() == 3 && resHexInt.length() == 3 || resHexResidue.length() == 4 && resHexInt.length() == 3)
            resHexInt = "0" + resHexInt;

        resAll = resHexResidue + resHexInt;

        allFormats = new AllFormats();
        int resDex = allFormats.fromHexToDex(resAll);
        return resDex;
    }

    public int operationXor(int num1, int num2) {
        return num1 ^ num2;
    }

    public int operationOr(int num1, int num2) {
        return num1 | num2;
    }

    public int operationAnd(int num1, int num2) {
        return num1 & num2;
    }

    public int operationNot(int num1) {
        return ~num1;
    }
}
