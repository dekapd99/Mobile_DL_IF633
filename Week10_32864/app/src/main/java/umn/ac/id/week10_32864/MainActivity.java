package umn.ac.id.week10_32864;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button tutorial1, tutorial2, tutorial3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tutorial1 = findViewById(R.id.tutorial1);
        tutorial2 = findViewById(R.id.tutorial2);
        tutorial3 = findViewById(R.id.tutorial3);

        tutorial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial1.class));
            }
        });
        tutorial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial2.class));
            }
        });
        tutorial3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial3.class));
            }
        });
    }
}