package com.yurka.sergeant_matatov.assemblercalculator;

public class AllFormats {

    public int lenNum = 0;

    public int mashlimLeShtaim(String numStr) {
        numStr = numStr.toUpperCase();
        int numChange = 0;
        int cnt = numStr.length();
        char[] buf = new char[15];
        numStr.getChars(0, cnt, buf, 0);
        numStr = new String(buf, 0, cnt);
        int num = fromHexToDex(numStr);
        String strBin = Integer.toBinaryString(num);
        cnt = strBin.length();
        int cntZero = 0;
        while (cnt % 4 != 0) {
            cntZero++;
            cnt++;
        }
        switch (cntZero) {
            case 1:
                String zeroStr = "0";
                strBin = zeroStr + strBin;
                break;
            case 2:
                zeroStr = "00";
                strBin = zeroStr + strBin;
                break;
            case 3:
                zeroStr = "000";
                strBin = zeroStr + strBin;
                break;
        }

        while (lenNum != 0) {
            String temp = strBin;
            strBin = "0000" + temp;
            lenNum--;
        }

        cnt = strBin.length();
        char[] buf1 = new char[50];
        strBin.getChars(0, cnt, buf1, 0);

        //меняем 0 на 1 и 1 на 0
        for (int i = 0; i < cnt; i++) {
            if (buf1[i] == '1')
                buf1[i] = '0';
            else
                buf1[i] = '1';
        }

        //прибавляем 1
        for (int i = cnt - 1; i > -1; i--) {
            if (buf1[i] == '1')
                buf1[i] = '0';
            else if (buf1[i] == '0') {
                buf1[i] = '1';
                break;
            }
        }
        numStr = new String(buf1, 0, cnt);
        numChange = fromBinToDex(numStr);
        return numChange;
    }

    public int fromHexToDex(String numStr) {
        numStr = numStr.toUpperCase();
        int start = 0;
        int cnt = numStr.length();
        char[] buf1 = new char[15];
        numStr.getChars(start, cnt, buf1, 0);
        int[] arr = new int[15];
        int j = 0;
        for (int i = 0; i < cnt; i++) {
            if (buf1[i] == '0' || buf1[i] == '1' || buf1[i] == '2' || buf1[i] == '3' || buf1[i] == '4' || buf1[i] == '5' || buf1[i] == '6' || buf1[i] == '7' || buf1[i] == '8' || buf1[i] == '9') {
                arr[j++] = Integer.valueOf(buf1[i]) - 48;
            } else if (buf1[i] == 'A') {
                arr[j++] = 10;
            } else if (buf1[i] == 'B') {
                arr[j++] = 11;
            } else if (buf1[i] == 'C') {
                arr[j++] = 12;
            } else if (buf1[i] == 'D') {
                arr[j++] = 13;
            } else if (buf1[i] == 'E') {
                arr[j++] = 14;
            } else if (buf1[i] == 'F') {
                arr[j++] = 15;
            }
        }

        int numBit = 1;
        int numDex = 0;

        for (int i = j - 1; i > -1; i--) {
            for (int k = 0; k < 4; k++) {
                if (arr[i] % 2 != 0)
                    numDex += numBit;

                arr[i] = arr[i] >> 1;
                numBit += numBit;
            }
        }
        return numDex;
    }

    public int fromDexToDex(String numStr) {
        int cnt = numStr.length();
        char[] buf = new char[15];
        numStr.getChars(0, cnt, buf, 0);
        numStr = new String(buf, 0, cnt);
        int num = Integer.parseInt(numStr);
        return num;
    }

    public int fromBinToDex(String numStr) {
        int cnt = numStr.length();
        char[] buf = new char[30];
        numStr.getChars(0, cnt, buf, 0);
        int numBit = 1;
        int numDex = 0;
        for (int i = cnt - 1; i > -1; i--) {
            if (buf[i] == '1')
                numDex += numBit;
            numBit += numBit;
        }
        return numDex;
    }

    public int fromOctToDec(String numStr)
    {
        int num = Integer.parseInt(numStr.replaceAll("[\\D]",""));
        String strNum = "";
        int resDec = 0;

        int cnt = 0;
        //quantity numbers on number
        while (num != 0)
        {
            cnt++;
            num /= 10;
        }

        num = Integer.parseInt(numStr.replaceAll("[\\D]",""));
        int pow = 0;
        while (cnt != 0)
        {
            int numMod = num % 10;
            num /= 10;
            resDec += numMod * (Math.pow(8,pow++));
            cnt--;
        }
        return resDec;
    }

    public String addF(String str) {
        if (str.length() % 2 == 0)
            return str;
        else
            return "F" + str;
    }
}
