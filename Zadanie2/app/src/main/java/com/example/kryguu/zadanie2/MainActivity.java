package com.example.kryguu.zadanie2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextA;
    EditText editTextR;
    EditText editTextG;
    EditText editTextB;
    EditText editTextH;
    EditText editTextS;
    EditText editTextV;

    TextView calculatedHTextView;
    TextView calculatedSTextView;
    TextView calculatedVTextView;
    TextView calculatedATextView;
    TextView calculatedRTextView;
    TextView calculatedGTextView;
    TextView calculatedBTextView;
    TextView calculatedRGBHexTextView;

    View rgbHsvView;
    View hsvRgbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIComponents();
        addListeners();
    }

    private void initUIComponents() {

        editTextA = (EditText)findViewById(R.id.editTextA);
        editTextR = (EditText)findViewById(R.id.editTextR);
        editTextG = (EditText)findViewById(R.id.editTextG);
        editTextB = (EditText)findViewById(R.id.editTextB);
        editTextH = (EditText)findViewById(R.id.editTextH);
        editTextS = (EditText)findViewById(R.id.editTextS);
        editTextV = (EditText)findViewById(R.id.editTextV);

        calculatedHTextView = (TextView)findViewById(R.id.calculatedH);
        calculatedSTextView = (TextView)findViewById(R.id.calculatedS);
        calculatedVTextView = (TextView)findViewById(R.id.calculatedV);
        calculatedATextView = (TextView)findViewById(R.id.calculatedA);
        calculatedRTextView = (TextView)findViewById(R.id.calculatedR);
        calculatedGTextView = (TextView)findViewById(R.id.calculatedG);
        calculatedBTextView = (TextView)findViewById(R.id.calculatedB);
        calculatedRGBHexTextView = (TextView)findViewById(R.id.calculatedRGBHex);

        rgbHsvView = findViewById(R.id.rgbHsvView);
        hsvRgbView = findViewById(R.id.hsvRgbView);

    }

    private void addListeners() {
        addEditTextListeners();
    }

    private void addEditTextListeners() {

        editTextA.addTextChangedListener(getTextWatcherForEditText(255, editTextA));
        editTextR.addTextChangedListener(getTextWatcherForEditText(255, editTextR));
        editTextG.addTextChangedListener(getTextWatcherForEditText(255, editTextG));
        editTextB.addTextChangedListener(getTextWatcherForEditText(255, editTextB));
        editTextH.addTextChangedListener(getTextWatcherForEditText(359, editTextH));
        editTextS.addTextChangedListener(getTextWatcherForEditText(1, editTextS));
        editTextV.addTextChangedListener(getTextWatcherForEditText(1, editTextV));

    }

    private TextWatcher getTextWatcherForEditText(final int maxValue, final EditText editText) {
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    double value;
                    try {
                        value = Double.parseDouble(charSequence.toString());
                    } catch (NumberFormatException e) {
                        value = 0;
                    }
                    if (value > maxValue) {
                        String max = String.valueOf(maxValue);
                        editText.setText(max);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    tryCalculateRGB();
                    tryCalculateHSV();
                }
            };
            return textWatcher;
    }

    private void tryCalculateRGB() {

        String h = editTextH.getText().toString();
        String s = editTextS.getText().toString();
        String v = editTextV.getText().toString();
        float hValue, sValue, vValue = 0;
        float[] hsv = new float[3];

        try {
            hValue = Float.parseFloat(h);
            sValue = Float.parseFloat(s);
            vValue = Float.parseFloat(v);
            hsv[0] = hValue;
            hsv[1] = sValue;
            hsv[2] = vValue;
            int color = Color.HSVToColor(hsv);
            calculatedRGBHexTextView.setText("#"+Integer.toHexString(color));
            calculatedATextView.setText(numberToString(Color.alpha(color)));
            calculatedRTextView.setText(numberToString(Color.red(color)));
            calculatedGTextView.setText(numberToString(Color.green(color)));
            calculatedBTextView.setText(numberToString(Color.blue(color)));
            hsvRgbView.setBackgroundColor(color);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
        }

    }

    private void tryCalculateHSV() {

        String a = editTextA.getText().toString();
        String r = editTextR.getText().toString();
        String g = editTextG.getText().toString();
        String b = editTextB.getText().toString();
        int aValue, rValue, gValue, bValue = 0;
        float[] hsv = new float[3];
        try {
            aValue = Integer.parseInt(a);
            rValue = Integer.parseInt(r);
            gValue = Integer.parseInt(g);
            bValue = Integer.parseInt(b);
            int color = Color.argb(aValue,rValue,gValue,bValue);
            rgbHsvView.setBackgroundColor(color);
            Color.RGBToHSV(rValue, gValue, bValue, hsv);
            calculatedHTextView.setText(numberToString((long)hsv[0]));
            calculatedSTextView.setText(numberToString(hsv[1]));
            calculatedVTextView.setText(numberToString(hsv[2]));
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
        }

    }

    private String numberToString(double number) { // funkcja zamieniająca liczbę na stringa, sprawdzając czy jest całkowita czy nie
        long wholeNumber = (long) number;
        if (number == wholeNumber) {
            return String.valueOf(wholeNumber);
        } else {
            return String.format("%.3f",number); //String.valueOf(number);
        }
    }

    private void rgbToHsv(int r, int g, int b) {
        float[] hsv = new float[3];
        Color.RGBToHSV(r,g,b,hsv);
        Log.d("HSV0", String.valueOf(hsv[0]));
        Log.d("HSV1", String.valueOf(hsv[1]));
        Log.d("HSV2", String.valueOf(hsv[2]));
    }
}