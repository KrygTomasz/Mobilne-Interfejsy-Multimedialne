package com.example.kryguu.laboratoria3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    TextView textView;
    Button acceptButton;
    Button cancelButton;
    String extraValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Intent intent = getIntent();
        extraValue = intent.getStringExtra(MainActivity.EXTRA_KEY);

        fullScreen();

        initUIComponents();
    }

    private void fullScreen() { // makes activity full screen

        Window window = getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        windowParams.flags |= bits;
        window.setAttributes(windowParams);

    }

    private void initUIComponents() { // initializes user interface components and listeners

        textView = (TextView) findViewById(R.id.childActivityTextView);
        textView.setText(extraValue);
        acceptButton = (Button) findViewById(R.id.acceptButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButtonClick(view);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButtonClick(view);
            }
        });

    }

    private void onBackButtonClick(View view) { // function which sets result and action to intent and closes activity

        String text = ((Button) view).getText().toString();
        setResult(RESULT_OK, (new Intent()).setAction(text));
        super.onBackPressed();

    }

    @Override
    public void onBackPressed() { // function triggered when back button is pressed

        onBackButtonClick(cancelButton);
        
    }
}
