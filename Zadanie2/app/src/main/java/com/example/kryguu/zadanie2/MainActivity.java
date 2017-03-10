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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextR;
    EditText editTextG;
    EditText editTextB;
    EditText editTextH;
    EditText editTextS;
    EditText editTextV;
    Button calculateHSVButton;
    Button calculateRGBButton;
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

        editTextR = (EditText)findViewById(R.id.editTextR);
        editTextG = (EditText)findViewById(R.id.editTextG);
        editTextB = (EditText)findViewById(R.id.editTextB);
        editTextH = (EditText)findViewById(R.id.editTextH);
        editTextS = (EditText)findViewById(R.id.editTextS);
        editTextV = (EditText)findViewById(R.id.editTextV);
        calculateHSVButton = (Button) findViewById(R.id.calculateHSVButton);
        calculateRGBButton = (Button)findViewById(R.id.calculateRGBButton);
        rgbHsvView = (View)findViewById(R.id.rgbHsvView);
        hsvRgbView = (View)findViewById(R.id.hsvRgbView);

    }

    private void addListeners() {
        addEditTextListeners();
        addButtonListeners();
    }

    private void addEditTextListeners() {

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
                    if (editText == editTextR || editText == editTextG || editText == editTextB) {
                        tryCalculateHSV();
                    } else if (editText == editTextH || editText == editTextS || editText == editTextV) {
                        tryCalculateRGB();
                    }
                }
            };
            return textWatcher;
    }

    private void addButtonListeners() {

        calculateRGBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryCalculateRGB();
            }
        });

        calculateHSVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryCalculateHSV();
            }
        });

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
            hsvRgbView.setBackgroundColor(color);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
        }

    }

    private void tryCalculateHSV() {

        String r = editTextR.getText().toString();
        String g = editTextG.getText().toString();
        String b = editTextB.getText().toString();
        int rValue, gValue, bValue = 0;
        try {
            rValue = Integer.parseInt(r);
            gValue = Integer.parseInt(g);
            bValue = Integer.parseInt(b);
            int color = Color.argb(255,rValue,gValue,bValue);
            rgbHsvView.setBackgroundColor(color);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
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
