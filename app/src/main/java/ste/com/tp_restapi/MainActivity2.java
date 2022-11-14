package ste.com.tp_restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ste.com.tp_restapi.adapters.BookAdapter;
import ste.com.tp_restapi.models.BookResponse;
import ste.com.tp_restapi.models.Volume;
import ste.com.tp_restapi.repository.RetroClient;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView lvProvider;
    private ProgressBar progressBar;
    private BookAdapter bookAdapter;
    private List<Volume> listItems = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView txtButtonBackTwo;
    private String author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViews();
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        author = extras.getString("AUTHOR_LASTNAME");

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // volvemos a llamar metodo que obtiene datos
            searchItems(author);
            bookAdapter.notifyDataSetChanged();
            // desactivamos indicador de actualizacion
            swipeRefreshLayout.setRefreshing(false);
        });
        searchItems(author);
        bookAdapter = new BookAdapter(listItems);
        lvProvider.setAdapter(bookAdapter);


        lvProvider.setOnItemClickListener(this);

        txtButtonBackTwo.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent1);
        });
    }

    public void findViews(){
        this.progressBar        = (ProgressBar) findViewById(R.id.idLoadingPB);
        this.lvProvider         = findViewById(R.id.lvProvider);
        this.swipeRefreshLayout = findViewById(R.id.swiperefreshTwo);
        this.txtButtonBackTwo   = findViewById(R.id.txtButtonBackTwo);
    }

    public void searchItems(String author) {
        listItems.clear();

        Call<BookResponse> call = RetroClient.getInstance().getMyApi().searchBooks("inauthor:"+ author);

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {


                if (response.isSuccessful()){
                    listItems.addAll(response.body().getItems());
                }
                bookAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "Seleccionó ID: "+listaPost.get(position).getId(), Toast.LENGTH_SHORT).show();
        Volume volume = bookAdapter.getItem(position);

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("TITLE",          listItems.get(position).getVolumeInfo().getTitle());
        intent.putExtra("PUBLISHED_DATE", listItems.get(position).getVolumeInfo().getPublishedDate());
        intent.putExtra("PUBLISHER",      listItems.get(position).getVolumeInfo().getPublisher());
        intent.putExtra("PAGE_COUNT",     listItems.get(position).getVolumeInfo().getPageCount());
        intent.putExtra("PRINT_TYPE",     listItems.get(position).getVolumeInfo().getPrintType());
        intent.putExtra("AUTHOR_LASTNAME", author);

        if (volume.getVolumeInfo().getCategories()!=null) {
            intent.putExtra("CATEGORIES",     volume.getVolumeInfo().getCategories().get(0));
        }else{
            intent.putExtra("CATEGORIES",     "Sin Clasificar");
        }

        intent.putExtra("DESCRIPTION",    listItems.get(position).getVolumeInfo().getDescription());

        if (volume.getVolumeInfo().getImageLinks()!=null) {
            intent.putExtra("THUMBNAIL", volume.getVolumeInfo().getImageLinks().getThumbnail());
        }else{
            intent.putExtra("THUMBNAIL", "https://www.stanser.com/wp-content/uploads/2019/09/portada-libro-flex.jpg");
         }
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }



}
