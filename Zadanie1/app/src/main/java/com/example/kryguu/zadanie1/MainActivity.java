package com.example.kryguu.zadanie1;

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

            double delta = b*b - 4*a*c;
            if(delta>=0) {
                x1 = -b - Math.sqrt(delta) / (2 * a);
                x2 = -b + Math.sqrt(delta) / (2 * a);
                Pair solutions = Pair.create(x1,x2);
                return solutions;
            } else {
                return null;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Wrong number", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() { // ustawienie listenera dla buttona
        @Override
        public void onClick(View view) {
            Pair solutions = calculate(); // wyliczenie rozwiązań
            try {
                int numberOfSolutions = numberOfSolutions(solutions);
                if(numberOfSolutions == 1){
                    initOneSolutionTexts(solutions);
                } else if(numberOfSolutions == 2) {
                    initTwoSolutionTexts(solutions);
                }
            } catch (Exception e) {
                initNoSolutionTexts();
            }
        }
    };

    private int numberOfSolutions(Pair solution) { // funkcja licząca ilość rozwiązań
        if(solution.first.equals(solution.second)) return 1;
        else return 2;
    }

    private void initOneSolutionTexts(Pair solutions) { // funkcja inicjalizująca pola tekstowe dla jednego podwójnego rozwiązania
        textViewX1Desc.setText(R.string.textViewX0Desc);
        textViewX2Desc.setText("");
        textViewX1.setText(solutions.first.toString());
        textViewX2.setText("");
    }

    private void initTwoSolutionTexts(Pair solutions) { // funkcja inicjalizująca pola tekstowe dla dwóch różnych rozwiązań
        textViewX1Desc.setText(R.string.textViewX1Desc);
        textViewX2Desc.setText(R.string.textViewX2Desc);
        textViewX1.setText(solutions.first.toString());
        textViewX2.setText(solutions.second.toString());
    }

    private void initNoSolutionTexts() { // funkcja inicjalizująca pola tekstowe dla rozwiązań nierzeczywistych
        textViewX1Desc.setText("");
        textViewX2Desc.setText("");
        textViewX1.setText(R.string.noSolutions);
        textViewX2.setText("");
    }

}
