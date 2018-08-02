package com.yurka.sergeant_matatov.assemblercalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import static java.lang.System.exit;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    final String LOG_TAG = "myLogs";

    TextView textNums;
    TextView hexRes;
    TextView decRes;
    TextView binRes;
    TextView hexText;
    TextView decText;
    TextView binText;
    TextView octRes;
    TextView octText;

    Button btnClear;
    Button btnHex;
    Button btnDec;
    Button btnBin;
    Button btnOct;
    Button btnXor;
    Button btnOr;
    Button btnAnd;
    Button btnNot;

    public String strNum1 = "";
    public String strNum2 = "";
    public String typeNum1 = "";
    public String typeNum2 = "";
    public String str = "";
    public String mySing = "";
    public String typeNum = "";
    public String minus1 = "";
    public String minus2 = "";
    public int lenNum = 0;
    public int typeFlag = 0;
    public int singFlag = 0;
    public int textViewFlag = 0;

    private static final String KEY_TEXT_NUMS = "TEXT_NUMS";
    private static final String KEY_RES_DEC = "KEY_RES_DEC";
    private static final String KEY_RES_HEX = "KEY_RES_HEX";
    private static final String KEY_RES_OCT = "KEY_RES_OCT";
    private static final String KEY_RES_BIN = "KEY_RES_BIN";

    private static final String KEY_STR_NUM1 = "STR_NUM1";
    private static final String KEY_STR_NUM2 = "STR_NUM2";
    private static final String KEY_TYPE_NUM1 = "TYPE_NUM1";
    private static final String KEY_TYPE_NUM2 = "TYPE_NUM2";
    private static final String KEY_STR = "STR";

    private static final String KEY_MY_SING = "MY_SING";
    private static final String KEY_TYPE_NUM = "TYPE_NUM";
    private static final String KEY_MINUS1 = "MINUS1";
    private static final String KEY_MINUS2 = "MINUS2";

    private static final String KEY_LEN_NUM = "LEN_NUM";
    private static final String KEY_TYPE_FLAG = "TYPE_FLAG";
    private static final String KEY_SING_FLAG = "SING_FLAG";
    private static final String KEY_TEXT_VIEW_FLAF = "TEXT_VIEW_FLAF";

    SharedPreferences cntCommentPref;    //for dialog add comment
    final String SAVED_CNT_COMMENT = "saved_cnt_comment";

    Dialog dialog;
    Spannable spannable;

    CheckBox navCheckDec;
    CheckBox navCheckHex;
    CheckBox navCheckBin;
    CheckBox navCheckOct;

    MenuItem menuItem;

    int one = -1;
    int two = -1;

    // variable for selection intent
    private final int PICKER = 1;
    // variable to store the currently selected image
    public static int currentPic = 0;

    private Gallery myGallery;
    private ImageView bigimage;

    // adapter for gallery view
    private ImageAdapter adapter;

    private SlidingDrawer slidingDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        ///   Log.d(LOG_TAG, "язык: " +getResources().getConfiguration().locale.getLanguage());

        textNums = (TextView) findViewById(R.id.textNums);

        hexRes = (TextView) findViewById(R.id.hexRes);
        decRes = (TextView) findViewById(R.id.decRes);
        binRes = (TextView) findViewById(R.id.binRes);
        octRes = (TextView) findViewById(R.id.octRes);

        hexText = (TextView) findViewById(R.id.hexText);
        decText = (TextView) findViewById(R.id.decText);
        binText = (TextView) findViewById(R.id.binText);
        octText = (TextView) findViewById(R.id.octText);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnHex = (Button) findViewById(R.id.btnHex);
        btnDec = (Button) findViewById(R.id.btnDec);
        btnBin = (Button) findViewById(R.id.btnBin);
        btnOct = (Button) findViewById(R.id.btnOct);

        slidingDrawer = (SlidingDrawer) findViewById(R.id.drawerSliding);

        if (savedInstanceState != null) {
            String temp = savedInstanceState.getString(KEY_TEXT_NUMS, "");
            textNums.setText(temp.toString());
            setSpannable(textNums.getText().toString());

            temp = savedInstanceState.getString(KEY_RES_DEC, "");
            decRes.setText(temp.toString());

            temp = savedInstanceState.getString(KEY_RES_HEX, "");
            hexRes.setText(temp.toString());

            temp = savedInstanceState.getString(KEY_RES_OCT, "");
            octRes.setText(temp.toString());

            temp = savedInstanceState.getString(KEY_RES_BIN, "");
            binRes.setText(temp.toString());

            temp = savedInstanceState.getString(KEY_STR_NUM1, "");
            strNum1 = temp.toString();

            temp = savedInstanceState.getString(KEY_STR_NUM2, "");
            strNum2 = temp.toString();

            temp = savedInstanceState.getString(KEY_TYPE_NUM1, "");
            typeNum1 = temp.toString();

            temp = savedInstanceState.getString(KEY_TYPE_NUM2, "");
            typeNum2 = temp.toString();

            temp = savedInstanceState.getString(KEY_STR, "");
            str = temp.toString();

            temp = savedInstanceState.getString(KEY_MY_SING, "");
            mySing = temp.toString();

            temp = savedInstanceState.getString(KEY_TYPE_NUM, "");
            typeNum = temp.toString();

            temp = savedInstanceState.getString(KEY_MINUS1, "");
            minus1 = temp.toString();

            temp = savedInstanceState.getString(KEY_MINUS2, "");
            minus2 = temp.toString();

            lenNum = savedInstanceState.getInt(KEY_LEN_NUM, 0);

            typeFlag = savedInstanceState.getInt(KEY_TYPE_FLAG, 0);

            singFlag = savedInstanceState.getInt(KEY_SING_FLAG, 0);

            textViewFlag = savedInstanceState.getInt(KEY_TEXT_VIEW_FLAF, 0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuItem = navigationView.getMenu().findItem(R.id.nav_set_dec);
        navCheckDec = (CheckBox) MenuItemCompat.getActionView(menuItem);
        menuItem = navigationView.getMenu().findItem(R.id.nav_set_hex);
        navCheckHex = (CheckBox) MenuItemCompat.getActionView(menuItem);
        menuItem = navigationView.getMenu().findItem(R.id.nav_set_bin);
        navCheckBin = (CheckBox) MenuItemCompat.getActionView(menuItem);
        menuItem = navigationView.getMenu().findItem(R.id.nav_set_oct);
        navCheckOct = (CheckBox) MenuItemCompat.getActionView(menuItem);

        navCheckDec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    decRes.setVisibility(View.VISIBLE);
                else
                    decRes.setVisibility(View.INVISIBLE);
            }
        });

        navCheckHex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    hexRes.setVisibility(View.VISIBLE);
                else
                    hexRes.setVisibility(View.INVISIBLE);
            }
        });

        navCheckBin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    binRes.setVisibility(View.VISIBLE);
                else
                    binRes.setVisibility(View.INVISIBLE);
            }
        });

        navCheckOct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    octRes.setVisibility(View.VISIBLE);
                else
                    octRes.setVisibility(View.INVISIBLE);
            }
        });

        String strCnt = loadCntForDialogComment();

        if (!str.equals("0") && strCnt.equals("40"))
            dialogComment(strCnt);

        // remove char from 'textNums' one by one revers
        View.OnClickListener listnerClear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strNum1.length() == 0 && minus1.length() == 0) {
                    allClear();
                    decRes.setText("");
                    directAnswer();
                } else {
                    if (str.equals("-") && minus1.length() == 1 && str.length() == 1) {
                        minus1 = "";
                        str = "";
                        textNums.setText(str);
                        one = -1;
                    }
                    if (!str.equals("")) {
                        int start = 0;
                        int end = textNums.length();
                        end--;
                        char[] buf = new char[55];
                        char ch = textNums.getText().toString().charAt(end);    //вытащили удаляемый символ
                        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '|' || ch == '&') {
                            btnNot.setTextColor(getResources().getColor(R.color.colorGold));
                            if (minus2.length() == 0) {
                                singFlag = 0;
                                mySing = "";
                                strNum2 = "";
                                typeNum2 = "";
                            } else if (minus2.length() == 1 && strNum2.equals("") && typeNum2.equals("")) {
                                textNums.getText().toString().getChars(start, end, buf, 0);
                                str = new String(buf, 0, end);
                                minus2 = "";
                                textNums.setText(str);
                            } else
                                singFlag = 1;
                        }
                        if (!strNum2.equals("") || singFlag == 0) {
                            end--;
                            textNums.getText().toString().getChars(start, end, buf, 0);
                            str = new String(buf, 0, end);
                            str = afterClearNums(str);
                            if (singFlag == 1)
                                textNums.setText(str + typeNum2);
                            else {
                                textNums.setText(str + typeNum1);
                                one = -1;
                            }
                        }
                    }
                }
                two = -1;
                directAnswer();
                setSpannable(textNums.getText().toString());
            }
        };

        //button long press to clear all
        btnClear.setOnClickListener(listnerClear);
        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                allClear();
                decRes.setText("");
                return true;
            }
        });

        // logic buttons
        btnXor = (Button) findViewById(R.id.btnXor);
        btnOr = (Button) findViewById(R.id.btnOr);
        btnAnd = (Button) findViewById(R.id.btnAnd);
        btnNot = (Button) findViewById(R.id.btnNot);

        btnXor.setOnClickListener(this);
        btnOr.setOnClickListener(this);
        btnAnd.setOnClickListener(this);
        btnNot.setOnClickListener(this);

    }

    //buttons logic, sliding panel right side
    @Override
    public void onClick(View v) {

        if (slidingDrawer != null)
            slidingDrawer.animateClose();

        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.btnXor:
                onClickXor();
                break;

            case R.id.btnOr:
                onClickOr();
                break;

            case R.id.btnAnd:
                onClickAnd();
                break;

            case R.id.btnNot:
                onClickNot();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_TEXT_NUMS, textNums.getText().toString());
        outState.putString(KEY_RES_DEC, decRes.getText().toString());
        outState.putString(KEY_RES_HEX, hexRes.getText().toString());
        outState.putString(KEY_RES_OCT, octRes.getText().toString());
        outState.putString(KEY_RES_BIN, binRes.getText().toString());
        outState.putString(KEY_STR_NUM1, strNum1.toString());
        outState.putString(KEY_STR_NUM2, strNum2.toString());
        outState.putString(KEY_TYPE_NUM1, typeNum1.toString());
        outState.putString(KEY_TYPE_NUM2, typeNum2.toString());
        outState.putString(KEY_STR, str.toString());
        outState.putString(KEY_MY_SING, mySing.toString());
        outState.putString(KEY_TYPE_NUM, typeNum.toString());
        outState.putString(KEY_MINUS1, minus1.toString());
        outState.putString(KEY_MINUS2, minus2.toString());
        outState.putInt(KEY_LEN_NUM, lenNum);
        outState.putInt(KEY_TYPE_FLAG, typeFlag);
        outState.putInt(KEY_SING_FLAG, singFlag);
        outState.putInt(KEY_TEXT_VIEW_FLAF, textViewFlag);
    }

    //from answear to textview
    public void fromAnswerToText() {
        if (!decRes.getText().toString().isEmpty()) {
            allClear();
            strNum1 += decRes.getText();
            typeNum1 = "d";
            str += decRes.getText();
            textNums.setText(str + "d");
            decRes.setText("");
        }
    }

    //after clear all numbers
    public String afterClearNums(String strClear) {
        int len = strClear.length();
        int index = 0;
        char chLast = 0;
        char chFirst = 0;
        String[] temp = new String[2];
        if (singFlag == 1) {
            index = 1;
            chLast = textNums.getText().toString().charAt(len - 1);    //вытащили удаляемый символ
            chFirst = textNums.getText().toString().charAt(len - 2);

            if (chLast == '+' || chLast == '-' || chLast == '*' || chLast == '/' || chLast == '^' || chLast == '|' || chLast == '&') {
                strClear += "22";
                temp = strClear.split("\\" + mySing);
                temp[1] = "";
            } else
                temp = strClear.split("\\" + mySing);
            len = temp[1].length();
        } else {
            index = 0;
            temp[0] = strClear;
        }

        int type = 0;

        if (temp[index].indexOf("A") != -1 || temp[index].indexOf("B") != -1 || temp[index].indexOf("C") != -1 || temp[index].indexOf("D") != -1 || temp[index].indexOf("E") != -1 || temp[index].indexOf("F") != -1)
            type = 16;

        else if (type != 16)
            if (temp[index].indexOf("8") != -1 || temp[index].indexOf("9") != -1)
                type = 10;

            else if (type != 16 && type != 10)
                if (temp[index].indexOf("2") != -1 || temp[index].indexOf("3") != -1 || temp[index].indexOf("4") != -1 || temp[index].indexOf("5") != -1 || temp[index].indexOf("6") != -1 || temp[index].indexOf("7") != -1)
                    type = 8;

                else if (type != 16 && type != 10 && type != 8)
                    if (temp[index].indexOf("0") != -1 || temp[index].indexOf("1") != -1)
                        type = 2;

        switch (type) {
            case 2:
                typeNum = "b";
                break;
            case 8:
                typeNum = "o";
                break;
            case 10:
                typeNum = "d";
                break;
            case 16:
                typeNum = "h";
                break;
        }

        //if two numbers
        if (singFlag == 1) {
            if (len == 0) {
                if (chLast == '-' && chFirst == '*' || chLast == '-' && chFirst == '/') {
                    strNum2 = "";
                    typeNum2 = "";
                    return temp[0] + mySing + minus2;
                }
                strNum2 = "";
                typeNum2 = "";
                return temp[0] + mySing;
            }

            strNum2 = temp[1];
            typeNum2 = typeNum;
            return temp[0] + mySing + strNum2;
        } else    //если одно число
        {
            if (len == 0) {
                strNum1 = "";
                typeNum1 = "";
                minus1 = "";
                return "";
            }
            if (minus1.length() == 1 && len == 1) {
                strNum1 = "";
                typeNum1 = "";
                return minus1;
            }
            typeNum1 = typeNum;
            strNum1 = temp[0];
            return strNum1;
        }
    }

    //clear all input & output
    public void allClear() {
        binRes.setText("");
        hexRes.setText("");
        octRes.setText("");

        //       dexNum.setText("");
        textNums.setText("");
        str = "";
        strNum1 = "";
        strNum2 = "";
        mySing = "";
        typeNum = "d";
        typeNum1 = "";
        typeNum2 = "";
        typeFlag = 0;
        singFlag = 0;
        minus1 = "";
        minus2 = "";
        lenNum = 0;
        textViewFlag = 1;
        one = -1;
        two = -1;

        //restart colors buttons
        setColorGrayAllButtons();
    }

    /* ALL BUTTONS */
    public void onClickHex(View v) {
        if (typeFlag == 0 || typeFlag == 2 || typeFlag == 8 || typeFlag == 10 || typeFlag == 16) {
            if (singFlag == 0)
                typeNum1 = "h";
            else
                typeNum2 = "h";

            typeNum = "h";
            textNums.setText(str + typeNum);
            setSpannable(textNums.getText().toString());
            setColorBtnHex();
            directAnswer();
        }
    }

    public void onClickDex(View v) {
        if (typeFlag == 0 || typeFlag == 2 || typeFlag == 8 || typeFlag == 10) {
            if (singFlag == 0)
                typeNum1 = "d";
            else
                typeNum2 = "d";

            typeNum = "d";
            textNums.setText(str + typeNum);
            setSpannable(textNums.getText().toString());
            setColorBtnDec();
            directAnswer();
        }
    }

    public void onClickOct(View v) {
        if (typeFlag == 0 || typeFlag == 2 || typeFlag == 8) {
            if (singFlag == 0)
                typeNum1 = "o";
            else
                typeNum2 = "o";

            typeNum = "o";
            textNums.setText(str + typeNum);
            setSpannable(textNums.getText().toString());
            setColorBtnOct();
            directAnswer();
        }
    }

    public void onClickBin(View v) {
        if (typeFlag == 0 || typeFlag == 2) {
            if (singFlag == 0)
                typeNum1 = "b";
            else
                typeNum2 = "b";

            typeNum = "b";
            textNums.setText(str + typeNum);
            setSpannable(textNums.getText().toString());
            setColorBtnBin();
            directAnswer();
        }
    }

    public void onClickA(View v) {
        addHex("A");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClickB(View v) {
        addHex("B");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClickC(View v) {
        addHex("C");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClickD(View v) {
        addHex("D");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClickE(View v) {
        addHex("E");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClickF(View v) {
        addHex("F");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick0(View v) {
        addBin("0");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick1(View v) {
        addBin("1");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick2(View v) {
        addOct("2");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick3(View v) {
        addOct("3");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick4(View v) {
        addOct("4");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick5(View v) {
        addOct("5");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick6(View v) {
        addOct("6");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick7(View v) {
        addOct("7");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick8(View v) {
        addDec("8");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClick9(View v) {
        addDec("9");
        directAnswer();
        setSpannable(textNums.getText().toString());
    }

    public void onClickPlus(View v) {
        if (strNum2.equals("") && !strNum1.equals("")) {
            if ("".equals(mySing)) {
                mySing = "+";
                str += typeNum1;
                str += "+";
                textNums.setText(str);
            } else if (minus2.length() == 0) {
                mySing = "+";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "+";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickMinus(View v) {
        if (strNum1.equals("") && minus1.length() == 0) {
            allClear();
            decRes.setText("");
            minus1 = "-";
            str += minus1;
            textNums.setText(str);
        }
        if (strNum2.equals("") && !strNum1.equals("")) {
            if (mySing.equals("")) {
                mySing = "-";
                str += typeNum1;
                str += "-";
                textNums.setText(str);
            } else if ("*".equals(mySing) || "/".equals(mySing) || "+".equals(mySing) || "-".equals(mySing)) {
                if (strNum2.equals("") && minus2.length() == 0) {
                    minus2 = "-";
                    str += minus2;
                    textNums.setText(str);
                }
            } else {
                mySing = "-";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "-";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickMul(View v) {
        if (strNum2.equals("") && !strNum1.equals("")) {
            if ("".equals(mySing)) {
                mySing = "*";
                str += typeNum1;
                str += "*";
                textNums.setText(str);
            } else if (minus2.length() == 0) {
                mySing = "*";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "*";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickDiv(View v) {
        if (strNum2.equals("") && !strNum1.equals("")) {
            if ("".equals(mySing)) {
                mySing = "/";
                str += typeNum1;
                str += "/";
                textNums.setText(str);
            } else if (minus2.length() == 0) {
                mySing = "/";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "/";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickXor() {
        if (strNum2.equals("") && !strNum1.equals("")) {
            if ("".equals(mySing)) {
                mySing = "^";
                str += typeNum1;
                str += "^";
                textNums.setText(str);
            } else if (minus2.length() == 0) {
                mySing = "^";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "^";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickOr() {
        if (strNum2.equals("") && !strNum1.equals("")) {
            if ("".equals(mySing)) {
                mySing = "|";
                str += typeNum1;
                str += "|";
                textNums.setText(str);
            } else if (minus2.length() == 0) {
                mySing = "|";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "|";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickAnd() {
        if (strNum2.equals("") && !strNum1.equals("")) {
            if ("".equals(mySing)) {
                mySing = "&";
                str += typeNum1;
                str += "&";
                textNums.setText(str);
            } else if (minus2.length() == 0) {
                mySing = "&";
                int start = 0;
                int end = 0;
                end = Integer.valueOf(textNums.length());
                --end;
                char[] buf = new char[15];
                str.getChars(start, end, buf, 0);
                str = "";
                str = new String(buf, 0, end);
                str += "&";
                textNums.setText(str);
            }
            typeFlag = 0;
            singFlag = 1;
        }
        setSpannable(textNums.getText().toString());
        btnNot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void onClickNot() {
        if (mySing.equals("")) {
            if (strNum2.equals("") && !strNum1.equals("")) {
                if ("".equals(mySing)) {
                    mySing = "~";
                    str += typeNum1;
                    str = "~" + str;
                    singFlag = 0;
                    textNums.setText(str);
                }
            }
            directAnswer();
            setSpannable(textNums.getText().toString());
        }
        singFlag = 0;
        str = "";
        mySing = "";
        typeFlag = 0;
        strNum1 = "";
        strNum2 = "";
        typeNum1 = "";
        typeNum2 = "";
        typeNum = "d";
        lenNum = 0;
        minus1 = "";
        minus2 = "";
    }

    public void onClickEqual(View v) {
        fromAnswerToText();
        directAnswer();
        mySing = "";
        setTypeFlag();
        strNum2 = "";
        //   typeNum1 = "d";
        typeNum2 = "";
        typeNum = "d";
        lenNum = 0;
        minus1 = "";
        minus2 = "";
        setColorBtnDec();

        setSpannable(textNums.getText().toString());
    }

    //flag for types input number
    private void setTypeFlag(){
        if (strNum1.contains("A") ||  strNum1.contains("B") ||  strNum1.contains("C") ||  strNum1.contains("D") ||  strNum1.contains("E") ||  strNum1.contains("F"))
            typeFlag = 16;
        else if ( strNum1.contains("8") ||  strNum1.contains("9"))
            typeFlag = 10;
        else if ( strNum1.contains("2") || strNum1.contains("3") ||  strNum1.contains("4") ||  strNum1.contains("5") ||  strNum1.contains("6") ||  strNum1.contains("7"))
            typeFlag = 8;
        else if ( strNum1.contains("0") ||  strNum1.contains("1"))
            typeFlag = 2;
    }

    //output all answers (hex, dec, oct, bin)
    public void directAnswer() {
        Result result = new Result();
        int res = 0;
        if (!strNum1.equals("") || !strNum2.equals("")) {
            String strRes = result.mathResult(strNum1, strNum2, typeNum1, typeNum2, mySing, minus1, minus2);
            if (strRes.equals("Error")) {
                strNum1 = "";
                strNum2 = "";
                mySing = "";
                textNums.setText("Error");
            } else {
                res = Integer.parseInt(strRes.replaceAll("[\\D]", ""));
                char charSing = strRes.charAt(0);
                if (charSing == '-') {
                    res *= -1;
                }
                String strResDex = String.format("%d", res);
                String strResHex = String.format("%X", res);
                String strResBin = Integer.toBinaryString(res);
                String strResOct = Integer.toOctalString(res);
                decRes.setText(strResDex);
                hexRes.setText(strResHex);
                octRes.setText(strResOct);
                int lenBin = strResBin.length();
                int cntPoint = 0;
                while (lenBin % 4 != 0) {
                    strResBin = "0" + strResBin;
                    lenBin = strResBin.length();
                }
                int start = 0;
                int end = lenBin;
                char[] buf = new char[end - start];
                strResBin.getChars(start, end, buf, 0);
                strResBin = "";
                while (lenBin != cntPoint) {
                    strResBin += buf[cntPoint];
                    if ((cntPoint + 1) % 4 == 0 && lenBin != cntPoint + 1)
                        strResBin += " ";
                    cntPoint++;
                }
                strResBin = strResBin.replaceFirst("^0*", "");

                if (strResBin.length() == 0)
                    binRes.setText("0");
                else
                    binRes.setText(strResBin);

            }
        } else if (strNum1.equals("")) {
            decRes.setText("");
            hexRes.setText("");
            octRes.setText("");
            binRes.setText("");
        }
    }

    /* switch status to input number */
    public void addHex(String ch) {
        if (singFlag == 0 && strNum1.length() < 8) {
            if (strNum1.length() == 0 && minus1.length() == 0) {
                allClear();
                decRes.setText("");
            }
            strNum1 += ch;
            str += ch;
            typeNum1 = "h";
            typeNum = "h";
            textNums.setText(str + typeNum);
            typeFlag = 16;
            setColorBtnHex();
        } else if (singFlag == 1 && strNum2.length() < 8) {
            strNum2 += ch;
            str += ch;
            typeNum2 = "h";
            typeNum = "h";
            textNums.setText(str + typeNum);
            typeFlag = 16;
            setColorBtnHex();
        }
    }

    public void addDec(String ch) {
        if (singFlag == 0) {
            if (strNum1.length() == 0 && minus1.length() == 0) {
                allClear();
                decRes.setText("");
            }
            if (typeNum1 == "h" && strNum1.length() < 8) {
                strNum1 += ch;
                typeNum1 = "h";
                typeNum = "h";
                str += ch;
                typeFlag = 16;
                setColorBtnHex();
            }
            if (typeNum1 == "d" && strNum1.length() < 15 || typeNum1 == "b" && strNum1.length() < 15 || typeNum1 == "o" && strNum1.length() < 15 || typeNum1 == "") {
                strNum1 += ch;
                typeNum1 = "d";
                typeNum = "d";
                str += ch;
                typeFlag = 10;
                setColorBtnDec();
            }
        } else {
            if (typeNum2 == "h" && strNum2.length() < 8) {
                strNum2 += ch;
                typeNum2 = "h";
                typeNum = "h";
                str += ch;
                typeFlag = 16;
                setColorBtnHex();
            }
            if (typeNum2 == "d" && strNum2.length() < 15 || typeNum2 == "b" && strNum2.length() < 15 || typeNum2 == "o" && strNum2.length() < 15 || typeNum2 == "") {
                strNum2 += ch;
                typeNum2 = "d";
                typeNum = "d";
                str += ch;
                typeFlag = 10;
                setColorBtnDec();
            }
        }
        textNums.setText(str + typeNum);
    }

    public void addBin(String ch) {
        if (singFlag == 0) {
            if (strNum1.length() == 0 && minus1.length() == 0) {
                allClear();
                decRes.setText("");
            }
            if (typeNum1 == "b" && strNum1.length() < 32 || typeNum1 == "") {
                strNum1 += ch;
                typeNum1 = "b";
                typeNum = "b";
                str += ch;
                typeFlag = 2;
                setColorBtnBin();
            }
            if (typeNum1 == "o" && strNum1.length() < 15) {
                strNum1 += ch;
                typeNum1 = "o";
                typeNum = "o";
                str += ch;
                typeFlag = 8;
                setColorBtnOct();
            }
            if (typeNum1 == "d" && strNum1.length() < 15) {
                strNum1 += ch;
                typeNum1 = "d";
                typeNum = "d";
                str += ch;
                typeFlag = 10;
                setColorBtnDec();
            }
            if (typeNum1 == "h" && strNum1.length() < 8) {
                strNum1 += ch;
                typeNum1 = "h";
                typeNum = "h";
                str += ch;
                typeFlag = 16;
                setColorBtnHex();
            }
        } else {
            if (typeNum2 == "b" && strNum2.length() < 32 || typeNum2 == "") {
                strNum2 += ch;
                typeNum2 = "b";
                typeNum = "b";
                str += ch;
                typeFlag = 2;
                setColorBtnBin();
            }
            if (typeNum2 == "d" && strNum2.length() < 15) {
                strNum2 += ch;
                typeNum2 = "d";
                typeNum = "d";
                str += ch;
                typeFlag = 10;
                setColorBtnDec();
            }
            if (typeNum2 == "h" && strNum2.length() < 8) {
                strNum2 += ch;
                typeNum2 = "h";
                typeNum = "h";
                str += ch;
                typeFlag = 16;
                setColorBtnHex();
            }
        }
        textNums.setText(str + typeNum);
    }

    public void addOct(String ch) {
        if (singFlag == 0) {
            if (strNum1.length() == 0 && minus1.length() == 0) {
                allClear();
                decRes.setText("");
            }
            if (typeNum1 == "h" && strNum1.length() < 8) {
                strNum1 += ch;
                typeNum1 = "h";
                typeNum = "h";
                str += ch;
                typeFlag = 16;
                setColorBtnHex();
            }
            if (typeNum1 == "d" && strNum1.length() < 15) {
                strNum1 += ch;
                typeNum1 = "d";
                typeNum = "d";
                str += ch;
                typeFlag = 10;
                setColorBtnDec();
            }
            if (typeNum1 == "o" && strNum1.length() < 15 || typeNum1 == "b" && strNum1.length() < 15 || typeNum1 == "") {
                strNum1 += ch;
                typeNum1 = "o";
                typeNum = "o";
                str += ch;
                typeFlag = 8;
                setColorBtnOct();
            }
        } else {
            if (typeNum2 == "h" && strNum2.length() < 8) {
                strNum2 += ch;
                typeNum2 = "h";
                typeNum = "h";
                str += ch;
                typeFlag = 16;
                setColorBtnHex();
            }
            if (typeNum2 == "d" && strNum2.length() < 15) {
                strNum2 += ch;
                typeNum2 = "d";
                typeNum = "d";
                str += ch;
                typeFlag = 10;
                setColorBtnDec();
            }
            if (typeNum2 == "o" && strNum2.length() < 15 || typeNum2 == "b" && strNum2.length() < 15 || typeNum2 == "") {
                strNum2 += ch;
                typeNum2 = "o";
                typeNum = "o";
                str += ch;
                typeFlag = 8;
                setColorBtnOct();
            }
        }
        textNums.setText(str + typeNum);
    }

    //colorfull chars цветные буквы
    public void setSpannable(String str) {
        if (str.indexOf("+") != -1 && one != -1 || str.indexOf("-") != -1 && one != -1 || str.indexOf("*") != -1 && one != -1 || str.indexOf("/") != -1 && one != -1 || str.indexOf("^") != -1 && one != -1 || str.indexOf("|") != -1 && one != -1 || str.indexOf("&") != -1 && one != -1) {

            if (str.indexOf("h", one + 1) != -1 && str.indexOf("h", one + 1) > one)
                two = str.indexOf("h", one + 1);

            else if (str.indexOf("d", one + 1) != -1 && str.indexOf("d", one + 1) > one && str.charAt(str.indexOf("d", one)) != 'D')
                two = str.indexOf("d", one + 1);

            else if (str.indexOf("b", one + 1) != -1 && str.indexOf("b", one + 1) > one)
                two = str.indexOf("b", one + 1);

            else if (str.indexOf("o", one + 1) != -1 && str.indexOf("o", one + 1) > one)
                two = str.indexOf("o", one + 1);
        } else if (two == -1) {

            if (str.indexOf("h") != -1)
                one = str.indexOf("h");
            else if (str.indexOf("d") != -1)
                one = str.indexOf("d");
            else if (str.indexOf("b") != -1)
                one = str.indexOf("b");
            else if (str.indexOf("o") != -1)
                one = str.indexOf("o");

            if (str.length() > one) {
                if (str.indexOf("h", one + 1) != -1 && str.indexOf("h", one + 1) > one)
                    two = str.indexOf("h", one + 1);

                else if (str.indexOf("d", one + 1) != -1 && str.indexOf("d", one + 1) > one && str.charAt(str.indexOf("d", one)) != 'D')
                    two = str.indexOf("d", one + 1);

                else if (str.indexOf("b", one + 1) != -1 && str.indexOf("b", one + 1) > one)
                    two = str.indexOf("b", one + 1);

                else if (str.indexOf("o", one + 1) != -1 && str.indexOf("o", one + 1) > one)
                    two = str.indexOf("o", one + 1);
            }
        }
        spannable = new SpannableString(str);
        if (one != -1) {
            spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorOrange)), one, one + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textNums.setText(spannable);
        }
        if (two != -1) {
            spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorOrange)), two, two + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textNums.setText(spannable);
        }
    }

    /* switch color to button (input number) */
    public void setColorGrayAllButtons() {
        btnHex.setTextColor(getResources().getColor(R.color.colorGray));
        btnDec.setTextColor(getResources().getColor(R.color.colorGray));
        btnBin.setTextColor(getResources().getColor(R.color.colorGray));
        btnOct.setTextColor(getResources().getColor(R.color.colorGray));
        btnNot.setTextColor(getResources().getColor(R.color.colorGold));
    }

    public void setColorBtnHex() {
        btnHex.setTextColor(getResources().getColor(R.color.colorGold));
        btnDec.setTextColor(getResources().getColor(R.color.colorGray));
        btnBin.setTextColor(getResources().getColor(R.color.colorGray));
        btnOct.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void setColorBtnDec() {
        btnDec.setTextColor(getResources().getColor(R.color.colorGold));
        btnHex.setTextColor(getResources().getColor(R.color.colorGray));
        btnBin.setTextColor(getResources().getColor(R.color.colorGray));
        btnOct.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void setColorBtnBin() {
        btnBin.setTextColor(getResources().getColor(R.color.colorGold));
        btnDec.setTextColor(getResources().getColor(R.color.colorGray));
        btnHex.setTextColor(getResources().getColor(R.color.colorGray));
        btnOct.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void setColorBtnOct() {
        btnOct.setTextColor(getResources().getColor(R.color.colorGold));
        btnDec.setTextColor(getResources().getColor(R.color.colorGray));
        btnBin.setTextColor(getResources().getColor(R.color.colorGray));
        btnHex.setTextColor(getResources().getColor(R.color.colorGray));
    }

    //dialog for comment on market
    public void dialogComment(String cnt) {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);   //нельзя
        adb.setMessage(getString(R.string.dialogCommentStr));
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                saveCntForDialogComment("0");   //поставили запрет на вывод диалога цифрой 0
                sendComment();
            }
        });
        adb.setNeutralButton(getString(R.string.btnRemind), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saveCntForDialogComment("1");   //обнулили cnt
                dialog.dismiss();
            }
        });
        adb.setNegativeButton(getString(R.string.btnNotRemind), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saveCntForDialogComment("0");   //поставили запрет на вывод диалога цифрой 0
                dialog.dismiss();
            }
        });
        dialog = adb.show();
    }

    //dialodg developers (list)
    public void dialogDevelopers() {
        final String[] developers = {getString(R.string.developer), getString(R.string.painter)};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getString(R.string.developers));
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setItems(developers, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        sendMailForDev("Matatov1989@gmail.com");
                        dialog.dismiss();
                        break;
                    case 1:
                        sendMailForDev("Docmat63@gmail.com");
                        dialog.dismiss();
                        break;
                }
            }
        });
        dialog = adb.show();
    }

    //send mail to select developer
    public void sendMailForDev(String mailAdres) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        // receiver
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mailAdres.toString()});
        // title
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        // send!
        startActivity(Intent.createChooser(emailIntent, getString(R.string.toastSendMail)));
    }

    //send count for dialog on comment to market
    public void saveCntForDialogComment(String cnt) {
        cntCommentPref = getSharedPreferences("cnt comment", MODE_PRIVATE);
        SharedPreferences.Editor ed = cntCommentPref.edit();
        ed.putString(SAVED_CNT_COMMENT, cnt);
        ed.commit();
    }

    //load count for dialog on comment to market
    public String loadCntForDialogComment() {
        cntCommentPref = getSharedPreferences("cnt comment", MODE_PRIVATE);
        String cnt = cntCommentPref.getString(SAVED_CNT_COMMENT, "");
        if (cnt.isEmpty()) {
            saveCntForDialogComment("1");
            return "1";
        } else if (cnt.equals("0"))
            return cnt;
        else {
            int tempCnt = Integer.parseInt(cnt) + 1;
            saveCntForDialogComment("" + tempCnt);
            return cnt;
        }
    }

    //dialog about program
    public void dialogAboutProgram() {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);   //нельзя
        adb.setTitle(R.string.app_name);
        adb.setMessage(R.string.strAboutProgram);
        adb.setIcon(R.mipmap.ic_launcher);
        adb.setPositiveButton(R.string.btnEstimate, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                sendComment();
                dialog.dismiss();
            }
        });
        adb.setNeutralButton(R.string.developers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogDevelopers();
                dialog.dismiss();
            }
        });
        adb.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = adb.show();
    }

    //dialog with logic tables
    public void dialogTablesLogicOperations() {
        final Integer[] mImage = {R.drawable.table_xor, R.drawable.table_or,
                R.drawable.table_and, R.drawable.table_not};

        LayoutInflater adbInflater = LayoutInflater.from(this);
        View view = adbInflater.inflate(R.layout.dialog_tables_logic_operations, null);

        bigimage = (ImageView) view.findViewById(R.id.picture);
        myGallery = (Gallery) view.findViewById(R.id.myGallery);

        // create a new adapter
        adapter = new ImageAdapter(MainActivity.this);
        // set the gallery adapter
        myGallery.setAdapter(adapter);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setView(view);
        dialog = adb.show();
        bigimage.setImageResource(mImage[0]);
        // set long click listener for each gallery thumbnail item
        myGallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // handle long clicks
            public boolean onItemLongClick(AdapterView<?> parent, View v,
                                           int position, long id) {
                // take user to choose an image
                // update the currently selected position so that we assign the
                // imported bitmap to correct item
                currentPic = position;
                // take the user to their chosen image selection app (gallery or
                // file manager)
                Intent pickIntent = new Intent();
                pickIntent.setType("image/*");
                pickIntent.setAction(Intent.ACTION_GET_CONTENT);
                // we will handle the returned data in onActivityResult
                startActivityForResult(
                        Intent.createChooser(pickIntent, "Select Picture"),
                        PICKER);
                return true;
            }
        });

        myGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //       Toast.makeText(MainActivity.this, "Позиция: " + position, Toast.LENGTH_SHORT).show();
                //       bigimage.setImageBitmap(adapter.getPic(position));
                bigimage.setImageResource(mImage[position]);
            }
        });
    }

    //dialog with system table
    public void dialogNumberSystemTable() {
        final Integer[] mImage = {R.drawable.dec_hex_bin_oct};

        LayoutInflater adbInflater = LayoutInflater.from(this);
        View view = adbInflater.inflate(R.layout.dialog_number_system_table, null);

        ImageView picture = (ImageView) view.findViewById(R.id.picture);

        picture.setImageResource(mImage[0]);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setView(view);
        dialog = adb.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // check if we are returning from picture selection
            if (requestCode == PICKER) {
                // the returned picture URI
                Uri pickedUri = data.getData();
                // declare the bitmap
                Bitmap pic = null;
                // declare the path string
                String imgPath = "";
                // retrieve the string using media data
                String[] medData = {MediaStore.Images.Media.DATA};
                // query the data
                Cursor picCursor = managedQuery(pickedUri, medData, null, null,
                        null);
                if (picCursor != null) {
                    // get the path string
                    int index = picCursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    picCursor.moveToFirst();
                    imgPath = picCursor.getString(index);
                } else
                    imgPath = pickedUri.getPath();
                // if and else handle both choosing from gallery and from file
                // manager
                // if we have a new URI attempt to decode the image bitmap
                if (pickedUri != null) {
                    // set the width and height we want to use as maximum
                    // display
                    int targetWidth = 600;
                    int targetHeight = 400;
                    // sample the incoming image to save on memory resources
                    // create bitmap options to calculate and use sample size
                    BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                    // first decode image dimensions only - not the image bitmap
                    // itself
                    bmpOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(imgPath, bmpOptions);

                    // work out what the sample size should be

                    // image width and height before sampling
                    int currHeight = bmpOptions.outHeight;
                    int currWidth = bmpOptions.outWidth;

                    // variable to store new sample size
                    int sampleSize = 1;

                    // calculate the sample size if the existing size is larger
                    // than target size
                    if (currHeight > targetHeight || currWidth > targetWidth) {
                        // use either width or height
                        if (currWidth > currHeight)
                            sampleSize = Math.round((float) currHeight
                                    / (float) targetHeight);
                        else
                            sampleSize = Math.round((float) currWidth
                                    / (float) targetWidth);
                    }
                    // use the new sample size
                    bmpOptions.inSampleSize = sampleSize;
                    // now decode the bitmap using sample options
                    bmpOptions.inJustDecodeBounds = false;
                    // get the file as a bitmap
                    pic = BitmapFactory.decodeFile(imgPath, bmpOptions);
                    // pass bitmap to ImageAdapter to add to array
                    adapter.addPic(pic);
                    // redraw the gallery thumbnails to reflect the new addition
                    myGallery.setAdapter(adapter);
                    // display the newly selected image at larger size
                    bigimage.setImageBitmap(pic);
                    // scale options
                    bigimage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
        }
        // superclass method
        super.onActivityResult(requestCode, resultCode, data);
    }

    //open application in market
    public void sendComment() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.yurka.sergeant_matatov.assemblercalculator&hl"));
        startActivity(intent);
    }

    //send link application on market to friend
    public void adviseFriend() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.yurka.sergeant_matatov.assemblercalculator&hl");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    //open link all program in market
    public void fromDevelopers() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/search?q=Yury%20Matatov&c=apps&hl"));
        startActivity(intent);
    }

    //button exit from program or clouse sliding
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START) && !slidingDrawer.isOpened())
            drawer.closeDrawer(GravityCompat.START);
        else if (slidingDrawer.isOpened() && !drawer.isDrawerOpen(GravityCompat.START))
            slidingDrawer.animateClose();
        else if (drawer.isDrawerOpen(GravityCompat.START) && slidingDrawer.isOpened()) {
            drawer.closeDrawer(GravityCompat.START);
            slidingDrawer.animateClose();
        }
        else {
            super.onBackPressed();
            moveTaskToBack(true);
            exit(0);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_set_dec) {
            if (navCheckDec.isChecked())
                navCheckDec.setChecked(false);
            else
                navCheckDec.setChecked(true);
        } else if (id == R.id.nav_set_hex) {
            if (navCheckHex.isChecked())
                navCheckHex.setChecked(false);
            else
                navCheckHex.setChecked(true);
        } else if (id == R.id.nav_set_bin) {
            if (navCheckBin.isChecked())
                navCheckBin.setChecked(false);
            else
                navCheckBin.setChecked(true);
        } else if (id == R.id.nav_set_oct) {
            if (navCheckOct.isChecked())
                navCheckOct.setChecked(false);
            else
                navCheckOct.setChecked(true);
        } else if (id == R.id.nav_tables_logic_operations) {
            dialogTablesLogicOperations();
        } else if (id == R.id.nav_number_system_table) {
            dialogNumberSystemTable();
        } else if (id == R.id.nav_advise_friend) {
            adviseFriend();
        } else if (id == R.id.nav_about_program) {
            dialogAboutProgram();
        } else if (id == R.id.nav_from_developer) {
            fromDevelopers();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}