package ste.com.tp_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private TextView txtButtonBackFour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        this.txtButtonBackFour = findViewById(R.id.txtButtonBackFour);
        txtButtonBackFour.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity4.this, MainActivity.class);
            startActivity(intent1);
        });
    }
}