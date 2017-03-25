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

    Float mExtremumX;
    Float mExtremumY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIComponents();
        buttonCalculate.setOnClickListener(buttonOnClickListener);
        buttonDraw.setOnClickListener(buttonDrawOnClickListener);
    }

    private void initUIComponents() { // initializes user interface components
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

    private Pair calculate() { // function that returns pair of solutions of given quadratic/linear function
        double x1;
        double x2;

        Pair solutions;
        try {
            double a = Double.parseDouble(editTextA.getText().toString());
            double b = Double.parseDouble(editTextB.getText().toString());
            double c = Double.parseDouble(editTextC.getText().toString());

            double delta = b * b - 4 * a * c;
            if (a == 0) {
                if (b != 0) {
                    x1 = -c / b;
                } else {
                    Toast.makeText(this, R.string.noSolutions, Toast.LENGTH_SHORT).show();
                    return null;
                }
                solutions = Pair.create(x1, x1);
                return solutions;
            }
            if (delta >= 0) {
                x1 = (-b - Math.sqrt(delta)) / (2 * a);
                x2 = (-b + Math.sqrt(delta)) / (2 * a);
                mExtremumX = (float)(-b/(2*a));
                mExtremumY = (float)(-delta/(4*a));
                solutions = Pair.create(x1, x2);
                return solutions;
            } else {
                mExtremumX = (float)(-b/(2*a));
                mExtremumY = (float)(-delta/(4*a));
                Toast.makeText(this, R.string.noSolutions, Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.wrongNumber, Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() { // calculate button listener
        @Override
        public void onClick(View view) {
            Pair solutions = calculate(); // calculate solutions
            initTextViews(solutions);
        }
    };

    private View.OnClickListener buttonDrawOnClickListener = new View.OnClickListener() { // draw button listener
        @Override
        public void onClick(View view) {
            Pair solutionsX = calculate();
            Pair solutionsY = getYFor(solutionsX);
            initTextViews(solutionsX);
            goToDrawActivity(solutionsX, solutionsY);
        }
    };

    private Pair getYFor(Pair solutionsX) { // gets pair of ys for given pair of x0 and x1 solutions

        Pair solutionsY;
        try {
            float a = Float.parseFloat(editTextA.getText().toString());
            float b = Float.parseFloat(editTextB.getText().toString());
            float c = Float.parseFloat(editTextC.getText().toString());
            float x1 = Float.parseFloat(solutionsX.first.toString());
            float x2 = Float.parseFloat(solutionsX.second.toString());
            float y1 = a * x1 * x1 + b * x1 + c;
            float y2 = a * x2 * x2 + b * x2 + c;

            solutionsY = Pair.create(y1, y2);
        } catch (NumberFormatException e) {
            solutionsY = null;
        } catch (NullPointerException e) {
            solutionsY = null;
        }
        return solutionsY;
    }

    private boolean solutionsExist(Pair solutionX, Pair solutionY) { // checks if real solution exists
        if (solutionX != null && solutionY != null) {
            return true;
        }
        return false;
    }

    private void goToDrawActivity(Pair solutionX, Pair solutionY) { // function which puts extra to intent and starts new activity

        Intent intent = new Intent(this, DrawActivity.class);
        String x1,x2,y1,y2,extremumX,extremumY;
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
        if (mExtremumX != null && mExtremumY != null) {
            extremumX = mExtremumX.toString();
            extremumY = mExtremumY.toString();
        } else {
            extremumX = null;
            extremumY = null;
        }
        intent.putExtra(IntentExtras.EXTRA_A, a);
        intent.putExtra(IntentExtras.EXTRA_B, b);
        intent.putExtra(IntentExtras.EXTRA_C, c);
        intent.putExtra(IntentExtras.EXTRA_X1, x1);
        intent.putExtra(IntentExtras.EXTRA_X2, x2);
        intent.putExtra(IntentExtras.EXTRA_Y1, y1);
        intent.putExtra(IntentExtras.EXTRA_Y2, y2);
        intent.putExtra(IntentExtras.EXTRA_EXTREMUM_X, extremumX);
        intent.putExtra(IntentExtras.EXTRA_EXTREMUM_Y, extremumY);
        startActivity(intent);

    }

    private int numberOfSolutions(Pair solution) { // calculates number of solutions
        if (solution.first.equals(solution.second)) return 1;
        else return 2;
    }

    private void initTextViews(Pair solutions) { // initializes all text views
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

    private void initOneSolutionTexts(Pair solutions) { // initializes text views for solution when x0 == x1
        textViewX1Desc.setText(R.string.textViewX0Desc);
        textViewX2Desc.setText("");
        textViewX1.setText(StringTools.numberToString((double)solutions.first));
        textViewX2.setText("");
    }

    private void initTwoSolutionTexts(Pair solutions) { // initializes text views for solution when x0 != x1
        textViewX1Desc.setText(R.string.textViewX1Desc);
        textViewX2Desc.setText(R.string.textViewX2Desc);
        textViewX1.setText(StringTools.numberToString((double)solutions.first));
        textViewX2.setText(StringTools.numberToString((double)solutions.second));
    }

    private void initNoSolutionTexts() { // initializes text views for solution when x0 and x1 are not real
        textViewX1Desc.setText("");
        textViewX2Desc.setText("");
        textViewX1.setText(R.string.noSolutions);
        textViewX2.setText("");
    }

}
