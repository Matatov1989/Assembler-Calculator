package com.yurka.sergeant_matatov.assemblercalculator;

/**
 * Created by Yurka on 05.01.2017.
 */

public class Result {
    public String strRes = "";
    public int cntNums = 0;
    public int res = 0;

    AllFormats allFormats;
    MathematicalOperations mathematicalOperations;

    public String mathResult(String strNum1, String strNum2, String typeNum1, String typeNum2, String mySing, String minus1, String minus2) {
        allFormats = new AllFormats();
        mathematicalOperations = new MathematicalOperations();

        if (strNum2.equals("0") && mySing.equals("/")) {
            strRes = "Error";
        } else {
            if (strNum2.equals("")) {
                String hex = "";
                //from hex to dex
                if (typeNum1.equals("h"))
                    res = allFormats.fromHexToDex(strNum1);
                //from dex to dex
                if (typeNum1.equals("d"))
                    res = allFormats.fromDexToDex(strNum1);
                if (typeNum1.equals("o"))
                    res = allFormats.fromOctToDec(strNum1);
                //from bin to dex
                if (typeNum1.equals("b"))
                    res = allFormats.fromBinToDex(strNum1);
                if (minus1.length() == 1) {
                    hex = String.format("%X", res);
                    res = allFormats.mashlimLeShtaim(hex);
                    hex = String.format("%X", res);
                    hex = allFormats.addF(hex);
                    res = allFormats.fromHexToDex(hex);
                }
                if (mySing.equals("~"))
                    res = mathematicalOperations.operationNot(res);
                strRes += res;
            }
            if (!strNum1.equals("") && !strNum2.equals("")) {
                int flagSing = 0;
                int num1 = 0;
                int num2 = 0;
                String hex1 = "";
                String hex2 = "";
                //from hex to dex
                if (typeNum1.equals("h"))
                    num1 = allFormats.fromHexToDex(strNum1);
                if (typeNum2.equals("h"))
                    num2 = allFormats.fromHexToDex(strNum2);
                //from dex to dex
                if (typeNum1.equals("d"))
                    num1 = allFormats.fromDexToDex(strNum1);
                if (typeNum2.equals("d"))
                    num2 = allFormats.fromDexToDex(strNum2);
                //from oct to dex
                if (typeNum1.equals("o"))
                    num1 = allFormats.fromOctToDec(strNum1);
                if (typeNum2.equals("o"))
                    num2 = allFormats.fromOctToDec(strNum2);
                //from bin to dex
                if (typeNum1.equals("b"))
                    num1 = allFormats.fromBinToDex(strNum1);
                if (typeNum2.equals("b"))
                    num2 = allFormats.fromBinToDex(strNum2);
                //check math opiration  опредиляем математическое действие
                if (mySing.equals("+"))
                    flagSing = 1;
                if (mySing.equals("-"))
                    flagSing = 2;
                if (mySing.equals("*"))
                    flagSing = 3;
                if (mySing.equals("/"))
                    flagSing = 4;
                if (mySing.equals("^"))
                    flagSing = 5;
                if (mySing.equals("|"))
                    flagSing = 6;
                if (mySing.equals("&"))
                    flagSing = 7;
                if (minus1.length() == 1) {
                    hex1 = String.format("%X", num1);
                    num1 = allFormats.mashlimLeShtaim(hex1);
                    hex1 = String.format("%X", num1);
                    hex1 = allFormats.addF(hex1);
                    num1 = allFormats.fromHexToDex(hex1);
                }
                if (minus2.length() == 1) {
                    hex2 = String.format("%X", num2);
                    num2 = allFormats.mashlimLeShtaim(hex2);
                    hex2 = String.format("%X", num2);
                    hex2 = allFormats.addF(hex2);
                    num2 = allFormats.fromHexToDex(hex2);
                }
                //make math opiration выполняем математическое действие
                switch (flagSing) {
                    case 1:
                        res = mathematicalOperations.operationPlus(num1, num2);
                        break;
                    case 2:
                        res = mathematicalOperations.operationkMinus(num1, num2);
                        break;
                    case 3:
                        res = mathematicalOperations.operationMul(num1, num2);
                        if (cntNums == 1)       //if one number with minus
                        {
                            hex1 = String.format("%X", res);
                            res = allFormats.mashlimLeShtaim(hex1);
                            hex1 = String.format("%X", res);
                            hex1 = allFormats.addF(hex1);
                            res = allFormats.fromHexToDex(hex1);
                        }
                        break;
                    case 4:
                        res = mathematicalOperations.operationDiv(num1, num2);     //div
                        if (cntNums == 1)       //if one number with minus
                        {
                            hex1 = String.format("%X", res);
                            res = allFormats.mashlimLeShtaim(hex1);
                            hex1 = String.format("%X", res);
                            hex1 = allFormats.addF(hex1);
                            res = allFormats.fromHexToDex(hex1);
                        }
                        break;
                    case 5:
                        res = mathematicalOperations.operationXor(num1, num2);
                        break;
                    case 6:
                        res = mathematicalOperations.operationOr(num1, num2);
                        break;
                    case 7:
                        res = mathematicalOperations.operationAnd(num1, num2);
                        break;
                }
                strRes += res;
            }
        }
        return strRes;
    }
}