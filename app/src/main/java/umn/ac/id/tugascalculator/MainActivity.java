package umn.ac.id.tugascalculator;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView hasil;
    private Boolean userTypingMid = false;
    private Calculator mCalculator;
    private static final String Digits = "0123456789.";
    DecimalFormat df = new DecimalFormat("@###########");




    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculator = new Calculator();
        hasil = (TextView) findViewById(R.id.Hasil);

        df.setMinimumFractionDigits(0);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(8);

        findViewById(R.id.NOL).setOnClickListener(this);
        findViewById(R.id.SATU).setOnClickListener(this);
        findViewById(R.id.DUA).setOnClickListener(this);
        findViewById(R.id.TIGA).setOnClickListener(this);
        findViewById(R.id.EMPAT).setOnClickListener(this);
        findViewById(R.id.LIMA).setOnClickListener(this);
        findViewById(R.id.ENAM).setOnClickListener(this);
        findViewById(R.id.TUJUH).setOnClickListener(this);
        findViewById(R.id.DELAPAN).setOnClickListener(this);
        findViewById(R.id.SEMBILAN).setOnClickListener(this);

        findViewById(R.id.TAMBAH).setOnClickListener(this);
        findViewById(R.id.KURANG).setOnClickListener(this);
        findViewById(R.id.BAGI).setOnClickListener(this);
        findViewById(R.id.KALI).setOnClickListener(this);
        findViewById(R.id.PLUSMINUS).setOnClickListener(this);
        findViewById(R.id.KOMA).setOnClickListener(this);
        findViewById(R.id.SAMADENGAN).setOnClickListener(this);
        findViewById(R.id.CE).setOnClickListener(this);
        findViewById(R.id.CA).setOnClickListener(this);
        findViewById(R.id.BACK).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String btnPress = ((Button) v).getText().toString();
        if (Digits.contains(btnPress)) {
            if(userTypingMid){
                if (btnPress.equals(".") && hasil.getText().toString().contains(".")){
                    //Do Nothing
                }else {
                    hasil.append(btnPress);
                }

            }else{
                if(btnPress.equals(".")){
                    hasil.setText(0 + btnPress);
                }else {
                    hasil.setText(btnPress);
                }
                userTypingMid = true;
            }
        }else{

            if(userTypingMid){
                if (btnPress.equals("BACK")){
                    if (hasil.getText().length() == 1)
                    {
                        mCalculator.setmOperand(Double.parseDouble("0"));
                        userTypingMid = false;
                    }else {
                        hasil.setText(hasil.getText().toString().substring(0,hasil.getText().length()-1));
                        mCalculator.setmOperand(Double.parseDouble(hasil.getText().toString()));
                    }

                }else{
                    mCalculator.setmOperand(Double.parseDouble(hasil.getText().toString()));
                    userTypingMid = false;
                }

            }
            try {
                mCalculator.performOperation(btnPress);
            }catch (NumberFormatException e){
                Log.e("tes","NumberFormatException",e);
            }
            hasil.setText(df.format(mCalculator.getResult()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("OPERAND", mCalculator.getResult());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCalculator.setmOperand(savedInstanceState.getDouble("OPERAND"));
        hasil.setText(df.format(mCalculator.getResult()));
    }
}
