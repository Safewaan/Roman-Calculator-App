package com.example.imtiaz.myapplication;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity implements RomanScreen.OnFragmentInteractionListener,IntegerScreen.OnFragmentInteractionListener {

    //Initialzing values that are used through the class and all methods.
    EditText edt1, edt2, edt3;
    int mValueOne, mValueTwo;
    boolean mAddition, mSubtract, mMultiplication, mDivision, mOperator, error;

    /**
     * Set's up the two fragments or tabs (romanScreen and IntegerScreen).
     * @param savedInstanceState saves the possible states in a bundle.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets up the two tabs.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Integer"));
        tabLayout.addTab(tabLayout.newTab().setText("Roman"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * On a button's click, it will find the code associated with that button and execute the code.
     * @param v stores the id of the button pressed.
     */
    public void Click(View v) {

        /*
        Assigns variable names to each of the id's for the input screens.
        edt1 is the integer input screen.
        edt2 is the complete calculation screen.
        edt3 is the roman input screen.
         */
        edt1 = (EditText) findViewById(R.id.InputScreen);
        edt2 = (EditText) findViewById(R.id.CalcScreen);
        edt3 = (EditText) findViewById(R.id.RomanScreen);

        if (error == false) {
            if ((edt1.getText().length()) < 9) { // If the integer value exceeds 9, causes a crash. This conditional prevents that crash.
                if (edt2.length() > 0) { // This conditional ensures that something like XV4 cannot be inputted, as it is an error.
                    if (!(edt2.getText().charAt(edt2.getText().length() - 1) == 'I' || edt2.getText().charAt(edt2.getText().length() - 1) == 'V' || edt2.getText().charAt(edt2.getText().length() - 1) == 'X' || edt2.getText().charAt(edt2.getText().length() - 1) == 'L' || edt2.getText().charAt(edt2.getText().length() - 1) == 'C' || edt2.getText().charAt(edt2.getText().length() - 1) == 'D' || edt2.getText().charAt(edt2.getText().length() - 1) == 'M')) { // This conditional ensures that something like 4XV cannot be inputted, as it is an error.
                        String digit = ((TextView) findViewById(v.getId())).getText().toString();
                        if (Character.isDigit(digit.charAt(0))) {
                            if (edt1.getText().toString().equals("0") && digit.equals('0')) {
                            } else {
                                edt1.setText(edt1.getText() + digit);
                                edt2.setText(edt2.getText() + digit);
                                if (Integer.parseInt(edt1.getText().toString()) < 5000 && Integer.parseInt(edt1.getText().toString()) > 0) {
                                    RomanNumerals romanNumber = new RomanNumerals(Integer.parseInt(edt1.getText().toString()));
                                    edt3.setText(romanNumber.toRoman());
                                } else {
                                    edt3.setText("OUT OF RANGE");
                                }
                            }
                        }
                    }
                } else { //else conditional only for first number as testing for the 1st index of an empty string will cause a crash.
                    String digit = ((TextView) findViewById(v.getId())).getText().toString();
                    if (Character.isDigit(digit.charAt(0))) {
                        if (edt1.getText().toString().equals("0") && digit.equals('0')) {
                        } else {
                            edt1.setText(edt1.getText() + digit);
                            edt2.setText(edt2.getText() + digit);
                            if (Integer.parseInt(edt1.getText().toString()) < 5000 && Integer.parseInt(edt1.getText().toString()) > 0) {
                                RomanNumerals romanNumber = new RomanNumerals(Integer.parseInt(edt1.getText().toString()));
                                edt3.setText(romanNumber.toRoman());
                            } else {
                                edt3.setText("OUT OF RANGE");
                            }
                        }
                    }
                }
            } else {
                error = true;
            }

            switch (v.getId()) {
                case R.id.MRoman:
                    RomanNumerals integerNumberMcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberMcheck.set(edt3.getText() + "M") == true) {
                                RomanNumerals integerNumberM = new RomanNumerals(edt3.getText() + "M");
                                edt1.setText(integerNumberM.toInt() + "");
                                edt2.setText(edt2.getText() + "M");
                                edt3.setText(edt3.getText() + "M");
                            }
                        }
                    } else {
                        if (integerNumberMcheck.set(edt3.getText() + "M") == true) {
                            RomanNumerals integerNumberM = new RomanNumerals(edt3.getText() + "M");
                            edt1.setText(integerNumberM.toInt() + "");
                            edt2.setText(edt2.getText() + "M");
                            edt3.setText(edt3.getText() + "M");
                        }
                    }
                    break;

                case R.id.DRoman:
                    RomanNumerals integerNumberDcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberDcheck.set(edt3.getText() + "D") == true) {
                                RomanNumerals integerNumberD = new RomanNumerals(edt3.getText() + "D");
                                edt1.setText(integerNumberD.toInt() + "");
                                edt2.setText(edt2.getText() + "D");
                                edt3.setText(edt3.getText() + "D");
                            }
                        }
                    } else {
                        if (integerNumberDcheck.set(edt3.getText() + "D") == true) {
                            RomanNumerals integerNumberD = new RomanNumerals(edt3.getText() + "D");
                            edt1.setText(integerNumberD.toInt() + "");
                            edt2.setText(edt2.getText() + "D");
                            edt3.setText(edt3.getText() + "D");
                        }
                    }
                    break;

                case R.id.CRoman:
                    RomanNumerals integerNumberCcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberCcheck.set(edt3.getText() + "C") == true) {
                                RomanNumerals integerNumberC = new RomanNumerals(edt3.getText() + "C");
                                edt1.setText(integerNumberC.toInt() + "");
                                edt2.setText(edt2.getText() + "C");
                                edt3.setText(edt3.getText() + "C");
                            }
                        }
                    } else {
                        if (integerNumberCcheck.set(edt3.getText() + "C") == true) {
                            RomanNumerals integerNumberD = new RomanNumerals(edt3.getText() + "D");
                            edt1.setText(integerNumberD.toInt() + "");
                            edt2.setText(edt2.getText() + "C");
                            edt3.setText(edt3.getText() + "C");
                        }
                    }
                    break;

                case R.id.LRoman:
                    RomanNumerals integerNumberLcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberLcheck.set(edt3.getText() + "L") == true) {
                                RomanNumerals integerNumberX = new RomanNumerals(edt3.getText() + "L");
                                edt1.setText(integerNumberX.toInt() + "");
                                edt2.setText(edt2.getText() + "L");
                                edt3.setText(edt3.getText() + "L");
                            }
                        }
                    } else {
                        if (integerNumberLcheck.set(edt3.getText() + "L") == true) {
                            RomanNumerals integerNumberX = new RomanNumerals(edt3.getText() + "L");
                            edt1.setText(integerNumberX.toInt() + "");
                            edt2.setText(edt2.getText() + "L");
                            edt3.setText(edt3.getText() + "L");
                        }
                    }
                    break;

                case R.id.XRoman:
                    RomanNumerals integerNumberXcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberXcheck.set(edt3.getText() + "X") == true) {
                                RomanNumerals integerNumberX = new RomanNumerals(edt3.getText() + "X");
                                edt1.setText(integerNumberX.toInt() + "");
                                edt2.setText(edt2.getText() + "X");
                                edt3.setText(edt3.getText() + "X");
                            }
                        }
                    } else {
                        if (integerNumberXcheck.set(edt3.getText() + "X") == true) {
                            RomanNumerals integerNumberX = new RomanNumerals(edt3.getText() + "L");
                            edt1.setText(integerNumberX.toInt() + "");
                            edt2.setText(edt2.getText() + "X");
                            edt3.setText(edt3.getText() + "X");
                        }
                    }
                    break;

                case R.id.VRoman:
                    RomanNumerals integerNumberVcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberVcheck.set(edt3.getText() + "V") == true) {
                                RomanNumerals integerNumberV = new RomanNumerals(edt3.getText() + "V");
                                edt1.setText(integerNumberV.toInt() + "");
                                edt2.setText(edt2.getText() + "V");
                                edt3.setText(edt3.getText() + "V");
                            }
                        }
                    } else {
                        if (integerNumberVcheck.set(edt3.getText() + "V") == true) {
                            RomanNumerals integerNumberV = new RomanNumerals(edt3.getText() + "V");
                            edt1.setText(integerNumberV.toInt() + "");
                            edt2.setText(edt2.getText() + "V");
                            edt3.setText(edt3.getText() + "V");
                        }
                    }
                    break;

                case R.id.IRoman:
                    RomanNumerals integerNumberIcheck = new RomanNumerals();
                    if (edt2.length() > 0) {
                        if (!(edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9')) {
                            if (integerNumberIcheck.set(edt3.getText() + "I") == true) {
                                RomanNumerals integerNumberI = new RomanNumerals(edt3.getText() + "I");
                                edt1.setText(integerNumberI.toInt() + "");
                                edt2.setText(edt2.getText() + "I");
                                edt3.setText(edt3.getText() + "I");
                            }
                        }
                    } else {
                        if (integerNumberIcheck.set(edt3.getText() + "I") == true) {
                            RomanNumerals integerNumberI = new RomanNumerals(edt3.getText() + "I");
                            edt1.setText(integerNumberI.toInt() + "");
                            edt2.setText(edt2.getText() + "I");
                            edt3.setText(edt3.getText() + "I");
                        }
                    }
                    break;

                case R.id.AdditionSign:
                    if (mOperator == false) {
                        if (!(edt1.getText().toString().equals(""))) { //prevents operator input if no number is present (stops crashes).
                            if (edt1.getText().toString().length() > String.valueOf(Integer.MAX_VALUE).length()) {
                                error = true;
                            } else {
                                edt2.setText(edt2.getText() + "+");
                                mValueOne = Integer.parseInt(edt1.getText().toString());
                                mAddition = true;
                                mOperator = true;
                                edt1.setText(null);
                                edt3.setText(null);
                            }
                        }
                    }
                    break;

                case R.id.SubtractionSign:
                    if (mOperator == false) {
                        if (!(edt1.getText().toString().equals(""))) { //prevents operator input if no number is present (stops crashes).
                            if (edt1.getText().toString().length() > String.valueOf(Integer.MAX_VALUE).length()) {
                                error = true;
                            } else {
                                edt2.setText(edt2.getText() + "-");
                                mValueOne = Integer.parseInt(edt1.getText().toString());
                                mSubtract = true;
                                mOperator = true;
                                edt1.setText(null);
                                edt3.setText(null);
                            }
                        }
                    }
                    break;

                case R.id.MultiplicationSign:
                    if (mOperator == false) {
                        if (!(edt1.getText().toString().equals(""))) { //prevents operator input if no number is present (stops crashes).
                            if (edt1.getText().toString().length() > String.valueOf(Integer.MAX_VALUE).length()) {
                                error = true;
                            } else {
                                edt2.setText(edt2.getText() + "*");
                                mValueOne = Integer.parseInt(edt1.getText().toString());
                                mMultiplication = true;
                                mOperator = true;
                                edt1.setText(null);
                                edt3.setText(null);
                            }
                        }
                    }
                    break;

                case R.id.DivisionSign:
                    if (mOperator == false) {
                        if (!(edt1.getText().toString().equals(""))) { //prevents operator input if no number is present (stops crashes).
                            if (edt1.getText().toString().length() > String.valueOf(Integer.MAX_VALUE).length()) {
                                error = true;
                            } else {
                                edt2.setText(edt2.getText() + "÷");
                                mValueOne = Integer.parseInt(edt1.getText().toString());
                                mDivision = true;
                                mOperator = true;
                                edt1.setText(null);
                                edt3.setText(null);
                            }
                        }
                    }
                    break;

                case R.id.EqualSign:
                    if (!(edt1.getText().toString().equals(""))) { //prevents operator input if no number is present (stops crashes).
                        if (edt1.getText().toString().length() > String.valueOf(Integer.MAX_VALUE).length()) {
                            error = true;
                        } else {
                            mValueTwo = Integer.parseInt(edt1.getText().toString());
                            edt2.setText(edt2.getText() + "=");
                            if (mAddition == true) {
                                edt1.setText(mValueOne + mValueTwo + "");
                                mAddition = false;
                            }
                            if (mSubtract == true) {
                                edt1.setText(mValueOne - mValueTwo + "");
                                mSubtract = false;
                            }
                            if (mMultiplication == true) {
                                edt1.setText(mValueOne * mValueTwo + "");
                                mMultiplication = false;
                            }
                            if (mDivision == true) {
                                edt1.setText(mValueOne / mValueTwo + "");
                                mDivision = false;
                            }
                            if (Integer.parseInt(edt1.getText().toString()) < 5000 && Integer.parseInt(edt1.getText().toString()) > 0) {
                                RomanNumerals romanNumber = new RomanNumerals(Integer.parseInt(edt1.getText().toString()));
                                edt3.setText(romanNumber.toRoman());
                            } else {
                                edt3.setText("OUT OF RANGE");
                            }
                            mOperator = true;
                        }
                    }
                    break;

                case R.id.CancelSign:
                    edt1.setText("");
                    edt2.setText("");
                    edt3.setText("");
                    error = false;
                    mOperator = false;
                    break;

                case R.id.deletesign:
                if (edt2.getText().length() > 0 ) { //prevents crashes
                    if (edt2.getText().length() - 1 == 0) { //if edt2 - 1 is nothing (last character being removed) set all the edts to nothing. This is placed first to prevent crashes.
                        edt1.setText("");
                        edt2.setText("");
                        edt3.setText("");
                    } else {
                        //conditional for if the last char is a int num.
                        if (edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9') {
                            edt1.setText(edt1.getText().subSequence(0, edt1.getText().length() - 1));
                            edt2.setText(edt2.getText().subSequence(0, edt2.getText().length() - 1));
                            if (edt1.getText().length() == 0) {
                                edt3.setText("");
                            } else {
                                RomanNumerals romanNumberCheck = new RomanNumerals();
                                if (romanNumberCheck.set(edt3.getText().toString()) == true) {
                                    RomanNumerals romanNumber = new RomanNumerals(Integer.parseInt(edt1.getText().toString())); //converts integer input to roman numeral.
                                    edt3.setText(romanNumber.toRoman());
                                } else {
                                    edt3.setText("OUT OF RANGE");
                                }
                            }
                        }
                        //conditional if the last char is a roman num.
                        if (edt2.getText().charAt(edt2.getText().length() - 1) == 'I' || edt2.getText().charAt(edt2.getText().length() - 1) == 'V' || edt2.getText().charAt(edt2.getText().length() - 1) == 'X' || edt2.getText().charAt(edt2.getText().length() - 1) == 'L' || edt2.getText().charAt(edt2.getText().length() - 1) == 'C' || edt2.getText().charAt(edt2.getText().length() - 1) == 'D' || edt2.getText().charAt(edt2.getText().length() - 1) == 'M') {
                            edt3.setText(edt3.getText().subSequence(0, edt3.getText().length() - 1));
                            edt2.setText(edt2.getText().subSequence(0, edt2.getText().length() - 1));
                            if (edt3.getText().length() == 0) {
                                edt1.setText("");
                            } else {
                                RomanNumerals romanNumberCheck = new RomanNumerals();
                                if (romanNumberCheck.set(edt3.getText().toString()) == true) {
                                    RomanNumerals romanNumber = new RomanNumerals(edt3.getText().toString());
                                    edt1.setText(romanNumber.toInt() + "");
                                }
                            }
                        }
                        //conditional if the last char is an operator.
                        if (edt2.getText().charAt(edt2.getText().length() - 1) == '+' || edt2.getText().charAt(edt2.getText().length() - 1) == '-' || edt2.getText().charAt(edt2.getText().length() - 1) == '*' || edt2.getText().charAt(edt2.getText().length() - 1) == '÷') {
                            edt2.setText(edt2.getText().subSequence(0, edt2.getText().length() - 1));
                            mOperator = false;
                            //conditional for if the last char is a int num.
                            if (edt2.getText().charAt(edt2.getText().length() - 1) == '0' || edt2.getText().charAt(edt2.getText().length() - 1) == '1' || edt2.getText().charAt(edt2.getText().length() - 1) == '2' || edt2.getText().charAt(edt2.getText().length() - 1) == '3' || edt2.getText().charAt(edt2.getText().length() - 1) == '4' || edt2.getText().charAt(edt2.getText().length() - 1) == '5' || edt2.getText().charAt(edt2.getText().length() - 1) == '6' || edt2.getText().charAt(edt2.getText().length() - 1) == '7' || edt2.getText().charAt(edt2.getText().length() - 1) == '8' || edt2.getText().charAt(edt2.getText().length() - 1) == '9') {
                                edt1.setText(edt2.getText());
                                RomanNumerals romanNumberCheck = new RomanNumerals();
                                if (romanNumberCheck.set(edt3.getText().toString()) == true) {
                                    RomanNumerals romanNumber = new RomanNumerals(Integer.parseInt(edt1.getText().toString())); //converts integer input to roman numeral.
                                    edt3.setText(romanNumber.toRoman());
                                }
                            }
                            //conditional if the last char is a roman num.
                            if (edt2.getText().charAt(edt2.getText().length() - 1) == 'I' || edt2.getText().charAt(edt2.getText().length() - 1) == 'V' || edt2.getText().charAt(edt2.getText().length() - 1) == 'X' || edt2.getText().charAt(edt2.getText().length() - 1) == 'L' || edt2.getText().charAt(edt2.getText().length() - 1) == 'C' || edt2.getText().charAt(edt2.getText().length() - 1) == 'D' || edt2.getText().charAt(edt2.getText().length() - 1) == 'M') {
                                edt3.setText(edt2.getText());
                                RomanNumerals romanNumber = new RomanNumerals(edt3.getText().toString());
                                edt1.setText(romanNumber.toInt() + "");
                            }
                        }
                        //conditional if the last char is an equal sign.
                        if (edt2.getText().charAt(edt2.getText().length() - 1) == '=') {
                            edt2.setText(edt2.getText().subSequence(0, edt2.getText().length() - 1));
                            for (int i = 0; i < edt2.getText().length() - 1; i++) {
                                if (edt2.getText().charAt(i) == '+' || edt2.getText().charAt(i) == '-' || edt2.getText().charAt(i) == '*' || edt2.getText().charAt(i) == '÷') {
                                    //conditional for if the last char is a int num.
                                    if (edt2.getText().charAt(i + 1) == '0' || edt2.getText().charAt(i + 1) == '1' || edt2.getText().charAt(i + 1) == '2' || edt2.getText().charAt(i + 1) == '3' || edt2.getText().charAt(i + 1) == '4' || edt2.getText().charAt(i + 1) == '5' || edt2.getText().charAt(i + 1) == '6' || edt2.getText().charAt(i + 1) == '7' || edt2.getText().charAt(i + 1) == '8' || edt2.getText().charAt(i + 1) == '9') {
                                        edt1.setText(edt2.getText().subSequence(i + 1, edt2.getText().length()));
                                        RomanNumerals romanNumber = new RomanNumerals(Integer.parseInt(edt1.getText().toString())); //converts integer input to roman numeral.
                                        edt3.setText(romanNumber.toRoman());
                                    }
                                    //conditional if the last char is a roman num.
                                    if (edt2.getText().charAt(edt2.getText().length() - 1) == 'I' || edt2.getText().charAt(edt2.getText().length() - 1) == 'V' || edt2.getText().charAt(edt2.getText().length() - 1) == 'X' || edt2.getText().charAt(edt2.getText().length() - 1) == 'L' || edt2.getText().charAt(edt2.getText().length() - 1) == 'C' || edt2.getText().charAt(edt2.getText().length() - 1) == 'D' || edt2.getText().charAt(edt2.getText().length() - 1) == 'M') {
                                        edt3.setText(edt2.getText().subSequence(i + 1, edt2.getText().length()));
                                        RomanNumerals romanNumber = new RomanNumerals(edt3.getText().toString());
                                        edt1.setText(romanNumber.toInt() + "");
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        if (error == true) {
            edt1.setText("ERROR");
            edt2.setText("EXCEEDS MAXIMUM INTEGER LENGTH");

            switch (v.getId()) {
                case R.id.CancelSign:
                    edt1.setText("");
                    edt2.setText("");
                    edt3.setText("");
                    error = false;
                    mOperator = false;
                    break;
            }
        }
    }
        @Override
        public void onFragmentInteraction (Uri uri){

        }
    }