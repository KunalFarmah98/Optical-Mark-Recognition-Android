package com.apps.kunalfarmah.omrscanner;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AnswersActivity extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20;
    File file, path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        e1 = findViewById(R.id.et1);
        e2 = findViewById(R.id.et2);
        e3 = findViewById(R.id.et3);
        e4 = findViewById(R.id.et4);
        e5 = findViewById(R.id.et5);
        e6 = findViewById(R.id.et6);
        e7 = findViewById(R.id.et7);
        e8 = findViewById(R.id.et8);
        e9 = findViewById(R.id.et9);
        e10 = findViewById(R.id.et10);
        e11 = findViewById(R.id.et11);
        e12 = findViewById(R.id.et12);
        e13 = findViewById(R.id.et13);
        e14 = findViewById(R.id.et14);
        e15 = findViewById(R.id.et15);
        e16 = findViewById(R.id.et16);
        e17 = findViewById(R.id.et17);
        e18 = findViewById(R.id.et18);
        e19 = findViewById(R.id.et19);
        e20 = findViewById(R.id.et20);

        path =
                Environment.getExternalStoragePublicDirectory
                        (
                                Environment.DIRECTORY_DOCUMENTS + "/OMR/"
                        );

        if (!path.exists()) {
            path.mkdirs();
        }

        file = new File(path, "key.txt");
        if (file.exists()) {
            String result = readFile(file);
            String[] answers = result.split("\n");
            try {
                e1.setText(answers[0].substring(answers[0].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e2.setText(answers[1].substring(answers[1].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e3.setText(answers[2].substring(answers[2].indexOf('.') + 1).trim() );

            } catch (Exception e) {
            }
            try {
                e4.setText(answers[3].substring(answers[3].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e5.setText(answers[4].substring(answers[4].indexOf('.') + 1).trim() );

            } catch (Exception e) {
            }
            try {
                e6.setText(answers[5].substring(answers[5].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e7.setText(answers[6].substring(answers[6].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e8.setText(answers[7].substring(answers[7].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e9.setText(answers[8].substring(answers[8].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e10.setText(answers[9].substring(answers[9].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e11.setText(answers[10].substring(answers[10].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e12.setText(answers[11].substring(answers[11].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e13.setText(answers[12].substring(answers[12].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e14.setText(answers[13].substring(answers[13].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e15.setText(answers[14].substring(answers[14].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e16.setText(answers[15].substring(answers[15].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e17.setText(answers[16].substring(answers[16].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e18.setText(answers[17].substring(answers[17].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e19.setText(answers[18].substring(answers[18].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }
            try {
                e20.setText(answers[19].substring(answers[19].indexOf('.') + 1).trim() );
            } catch (Exception e) {
            }

        }
    }


    private String readFile(File file) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            Log.d("ERROR", e.getMessage().toString());
        }
        String result = text.toString();
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String s = "";
            s += "Q1. " + e1.getText().toString().trim() + "\n";
            s += "Q2. " + e2.getText().toString().trim()  + "\n";
            s += "Q3. " + e3.getText().toString().trim()  + "\n";
            s += "Q4. " + e4.getText().toString().trim()  + "\n";
            s += "Q5. " + e5.getText().toString().trim()  + "\n";
            s += "Q6. " + e6.getText().toString().trim()  + "\n";
            s += "Q7. " + e7.getText().toString().trim()  + "\n";
            s += "Q8. " + e8.getText().toString().trim()  + "\n";
            s += "Q9. " + e9.getText().toString().trim()  + "\n";
            s += "Q10. " + e10.getText().toString().trim()  + "\n";
            s += "Q11. " + e11.getText().toString().trim()  + "\n";
            s += "Q12. " + e12.getText().toString().trim()  + "\n";
            s += "Q13. " + e13.getText().toString().trim()  + "\n";
            s += "Q14. " + e14.getText().toString().trim()  + "\n";
            s += "Q15. " + e15.getText().toString().trim()  + "\n";
            s += "Q16. " + e16.getText().toString().trim()  + "\n";
            s += "Q17. " + e17.getText().toString().trim()  + "\n";
            s += "Q18. " + e18.getText().toString().trim()  + "\n";
            s += "Q19. " + e19.getText().toString().trim()  + "\n";
            s += "Q20. " + e20.getText().toString().trim()  + "\n";
            try {
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();
                FileWriter out = new FileWriter(file);
                out.write(s);
                out.close();
                Toast.makeText(this, "Answer Key Upated", Toast.LENGTH_SHORT).show();
                finish();
            } catch (IOException e) {
                Toast.makeText(this, "An Error Occurred", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        return true;
    }
}