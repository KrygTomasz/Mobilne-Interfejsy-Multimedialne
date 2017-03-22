package com.example.kryguu.zadanie1;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView textViewA;
    TextView textViewB;
    TextView textViewC;
    EditText editTextA;
    EditText editTextB;
    EditText editTextC;

    Button buttonCalculate;
    Button buttonDraw;

    TextView textViewX1Desc;
    TextView textViewX2Desc;
    TextView textViewX1;
    TextView textViewX2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIComponents();
        buttonCalculate.setOnClickListener(buttonOnClickListener);
        buttonDraw.setOnClickListener(buttonDrawOnClickListener);
    }

    private void initUIComponents() { // inicjalizacja elementów widoku
        textViewA = (TextView) findViewById(R.id.textViewA);
        textViewB = (TextView) findViewById(R.id.textViewB);
        textViewC = (TextView) findViewById(R.id.textViewC);
        editTextA = (EditText) findViewById(R.id.editTextA);
        editTextB = (EditText) findViewById(R.id.editTextB);
        editTextC = (EditText) findViewById(R.id.editTextC);
        textViewX1 = (TextView) findViewById(R.id.textViewX1);
        textViewX2 = (TextView) findViewById(R.id.textViewX2);
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonDraw = (Button) findViewById(R.id.buttonDraw);
        textViewX1Desc = (TextView) findViewById(R.id.textViewX1Desc);
        textViewX2Desc = (TextView) findViewById(R.id.textViewX2Desc);
    }

    private Pair calculate() { // funkcja zwracająca parę rozwiązań
        double x1;
        double x2;
        try {
            double a = Double.parseDouble(editTextA.getText().toString());
            double b = Double.parseDouble(editTextB.getText().toString());
            double c = Double.parseDouble(editTextC.getText().toString());

            double delta = b * b - 4 * a * c;
            if (a == 0) throw new NumberFormatException();
            if (delta >= 0) {
                x1 = -b - Math.sqrt(delta) / (2 * a);
                x2 = -b + Math.sqrt(delta) / (2 * a);
                Pair solutions = Pair.create(x1, x2);
                return solutions;
            } else {
                Toast.makeText(this, R.string.noSolutions, Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.wrongNumber, Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() { // ustawienie listenera dla buttona
        @Override
        public void onClick(View view) {
            Pair solutions = calculate(); // wyliczenie rozwiązań
            initTextViews(solutions);
        }
    };

    private View.OnClickListener buttonDrawOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Pair solutionsX = calculate();
            Pair solutionsY = getYFor(solutionsX);
            initTextViews(solutionsX);
            goToDrawActivity(solutionsX, solutionsY);
        }
    };

    private Pair getYFor(Pair solutionsX) {

        Pair solutionsY;
        float a = Float.parseFloat(editTextA.getText().toString());
        float b = Float.parseFloat(editTextB.getText().toString());
        float c = Float.parseFloat(editTextC.getText().toString());
        try {
            float x1 = Float.parseFloat(solutionsX.first.toString());
            float x2 = Float.parseFloat(solutionsX.second.toString());
            float y1 = a * x1 * x1 + b * x1 + c;
            float y2 = a * x2 * x2 + b * x2 + c;

            solutionsY = Pair.create(y1, y2);
        } catch (Exception e) {
            solutionsY = null;
        }
        return solutionsY;
    }

    private void goToDrawActivity(Pair solutionX, Pair solutionY) { // function which puts extra to intent and starts new activity

        Intent intent = new Intent(this, DrawActivity.class);
        String x1,x2,y1,y2;
        String a = editTextA.getText().toString();
        String b = editTextB.getText().toString();
        String c = editTextC.getText().toString();
        if (solutionX != null && solutionY != null) {
            x1 = solutionX.first.toString();
            x2 = solutionX.second.toString();
            y1 = solutionY.first.toString();
            y2 = solutionY.second.toString();
        } else {
            x1 = null;
            x2 = null;
            y1 = null;
            y2 = null;
        }
        intent.putExtra(IntentExtras.EXTRA_A, a);
        intent.putExtra(IntentExtras.EXTRA_B, b);
        intent.putExtra(IntentExtras.EXTRA_C, c);
        intent.putExtra(IntentExtras.EXTRA_X1, x1);
        intent.putExtra(IntentExtras.EXTRA_X2, x2);
        intent.putExtra(IntentExtras.EXTRA_Y1, y1);
        intent.putExtra(IntentExtras.EXTRA_Y2, y2);
        startActivityForResult(intent, IntentExtras.DRAW_REQUEST_CODE);

    }

    private int numberOfSolutions(Pair solution) { // funkcja licząca ilość rozwiązań
        if (solution.first.equals(solution.second)) return 1;
        else return 2;
    }

    private void initTextViews(Pair solutions) { // funkcja inicjalizująca wszystkie pola teksowe zależnie od rozwiązań
        try {
            int numberOfSolutions = numberOfSolutions(solutions);
            if (numberOfSolutions == 1) {
                initOneSolutionTexts(solutions);
            } else if (numberOfSolutions == 2) {
                initTwoSolutionTexts(solutions);
            }
        } catch (Exception e) {
            initNoSolutionTexts();
        }
    }

    private void initOneSolutionTexts(Pair solutions) { // funkcja inicjalizująca pola tekstowe dla jednego podwójnego rozwiązania
        textViewX1Desc.setText(R.string.textViewX0Desc);
        textViewX2Desc.setText("");
        textViewX1.setText(numberToString((double)solutions.first));
        textViewX2.setText("");
    }

    private void initTwoSolutionTexts(Pair solutions) { // funkcja inicjalizująca pola tekstowe dla dwóch różnych rozwiązań
        textViewX1Desc.setText(R.string.textViewX1Desc);
        textViewX2Desc.setText(R.string.textViewX2Desc);
        textViewX1.setText(numberToString((double)solutions.first));
        textViewX2.setText(numberToString((double)solutions.second));
    }

    private void initNoSolutionTexts() { // funkcja inicjalizująca pola tekstowe dla rozwiązań nierzeczywistych
        textViewX1Desc.setText("");
        textViewX2Desc.setText("");
        textViewX1.setText("");
        textViewX2.setText("");
    }

    private String numberToString(double number) { // funkcja zamieniająca liczbę na stringa, sprawdzając czy jest całkowita czy nie
        long wholeNumber = (long) number;
        if (number == wholeNumber) {
            return String.valueOf(wholeNumber);
        } else {
            return String.valueOf(number);
        }
    }

}
