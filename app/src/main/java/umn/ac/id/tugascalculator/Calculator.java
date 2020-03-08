package umn.ac.id.tugascalculator;

import android.util.Log;

import java.util.SortedMap;

import javax.xml.transform.dom.DOMLocator;

public class Calculator {
      private double mOperand;
      private double mWaitingOperand;
      private String mWaitingOperator;

      boolean cekStat = false;
      public static final String Tambah = "+";
      public static final String Kurang = "-";
      public static final String Kali = "X";
      public static final String Bagi = "/";

    public static final String ClearEntry = "CE";
    public static final String ClearAll = "C";
    public static final String PlusMinus = "+/-";
    public static final String Back = "BACK";


    public Calculator(){
        mOperand =0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
    }
    public void setmOperand(double operand){
        mOperand = operand;
    }
    public double getResult(){
        return mOperand;
    }
    public String toString(){
        return Double.toString(mOperand);
    }


    protected double performOperation(String operator){
        if (mOperand == 0.0)
        {
            cekStat = false;
        }
        if (operator.equals(ClearAll)) {
            mOperand = 0;
            mWaitingOperand = 0;
            mWaitingOperator = "";
        }else if (operator.equals(ClearEntry)) {
            mOperand = 0;
        }else if (operator.equals(Back)){
            String tmpOperand = Double.toString(mOperand);
            if(tmpOperand.contains("-")&& tmpOperand.length() == 4){
                tmpOperand = tmpOperand.replaceAll("[-1234567890]","0");
                    mOperand = Double.parseDouble(tmpOperand);
                }
            else if (tmpOperand.contains("-")){
                if (mOperand % 1 == 0 )
                {
                    tmpOperand = tmpOperand.substring(0,tmpOperand.length() - 3);
                    mOperand = Double.parseDouble(tmpOperand);

                }else {
                    tmpOperand = tmpOperand.substring(0, tmpOperand.length() - 1);
                    mOperand = Double.parseDouble(tmpOperand);
                }
            }else if (cekStat == true){
                if (tmpOperand.length() == 3)
                {
                    tmpOperand = "0";
                    mOperand = Double.parseDouble(tmpOperand);
                 } else{
                    if (mOperand % 1 == 0 )
                    {
                        tmpOperand = tmpOperand.substring(0,tmpOperand.length() - 3);
                        mOperand = Double.parseDouble(tmpOperand);

                    }else {
                        tmpOperand = tmpOperand.substring(0,tmpOperand.length() - 1);
                        mOperand = Double.parseDouble(tmpOperand);
                    }
                }

            }

        }

        else if (operator.equals(PlusMinus)){
            mOperand = -mOperand;
           cekStat = true;
        }else{
            performWaitingOperation();
            mWaitingOperator = operator;
            mWaitingOperand = mOperand;
            cekStat = false;
        }
        return mOperand;
    }

    protected void performWaitingOperation(){
        if (mWaitingOperator.equals(Tambah)){
            mOperand = mWaitingOperand + mOperand;
        }else if (mWaitingOperator.equals(Kurang)){
            mOperand = mWaitingOperand - mOperand;
        }else if (mWaitingOperator.equals(Kali)) {
            mOperand = mWaitingOperand * mOperand;
        }else if (mWaitingOperator.equals(Bagi)){
            if (mOperand != 0) {
                mOperand = mWaitingOperand / mOperand;
            }
        }
    }



}
