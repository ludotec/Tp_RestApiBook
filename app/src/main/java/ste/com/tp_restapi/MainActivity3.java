package ste.com.tp_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity3 extends AppCompatActivity {

    private String infoLibro;
    private TextView txtInfoLibro;
    private TextView txtDescriptionBox;
    private ImageView imgDescriptionBox;
    private TextView txtButtonBackThree;
    private String author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        findViews();

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        author = extras.getString("AUTHOR_LASTNAME");
        infoLibro = "El "+extras.getString("TITLE")+ " fué publicado en "+
                extras.getString("PUBLISHED_DATE")+" por la editorial "+extras.getString("PUBLISHER")
                +" , cuenta con " + String.valueOf(extras.getInt("PAGE_COUNT"))+ " páginas, " +
                "tuvo ediciones en " + extras.getString("PRINT_TYPE") + " y fué categorizado como "
                +  "\""+ extras.getString("CATEGORIES")+ "\"" + ".";
        txtInfoLibro.setText(infoLibro);
        txtDescriptionBox.setText(extras.getString("DESCRIPTION"));
        String url = extras.getString("THUMBNAIL").replace("http://", "https://");
        Picasso.get().load(url).into(imgDescriptionBox);
        txtButtonBackThree.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity3.this, MainActivity2.class);
            intent1.putExtra("AUTHOR_LASTNAME",  author);
            startActivity(intent1);
        });

    }

    public void findViews(){

        this.txtInfoLibro       = findViewById(R.id.txtInfoLibro);
        this.txtDescriptionBox  = findViewById(R.id.txtDescriptionBox);
        this.imgDescriptionBox  = findViewById(R.id.imgDescriptionBox);
        this.txtButtonBackThree = findViewById(R.id.txtButtonBackThree);
    }
}