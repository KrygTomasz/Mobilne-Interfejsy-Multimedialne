package com.example.kryguu.laboratoria3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final int REQUEST_CODE = 3131;
    static final String EXTRA_KEY = "extraKey";

    Button firstButton;
    Button secondButton;
    Button thirdButton;
    Button fourthButton;

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        setContentView(R.layout.activity_main);

        fullScreen();

        initUIComponents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    String action = data.getAction();
                    resultTextView.setText(action);
                    break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void fullScreen() { // makes activity full screen

        Window window = getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        windowParams.flags |= bits;
        window.setAttributes(windowParams);

    }

    private void initUIComponents() { // initializes user interface components and listeners

        firstButton = (Button) findViewById(R.id.firstButton);
        secondButton = (Button) findViewById(R.id.secondButton);
        thirdButton = (Button) findViewById(R.id.thirdButton);
        fourthButton = (Button) findViewById(R.id.fourthButton);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(view);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(view);
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(view);
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(view);
            }
        });

        resultTextView = (TextView) findViewById(R.id.resultTextView);

    }

    private void onButtonClick(View view) { // function which puts extra to intent and starts new activity

        Intent intent = new Intent(this, ChildActivity.class);
        String buttonText = ((Button) view).getText().toString();
        intent.putExtra(EXTRA_KEY, buttonText);
        startActivityForResult(intent, REQUEST_CODE);

    }

}
