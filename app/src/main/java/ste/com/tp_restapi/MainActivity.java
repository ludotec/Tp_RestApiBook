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
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ste.com.tp_restapi.adapters.AuthorAdapter;
import ste.com.tp_restapi.models.AuthorModel;
import ste.com.tp_restapi.repository.RetroAuthorClient;


 public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

     private List<AuthorModel> authorModelList;
     private SwipeRefreshLayout swipeRefreshLayout;
     private AuthorAdapter authorAdapter;
     private TextView txtViewActivityButtonInfo;
     private ListView lvAuthor;
     private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
     //   progressBar.setVisibility(View.VISIBLE);

        authorModelList = new ArrayList<>();

        this.searchItems();

        authorAdapter = new AuthorAdapter(authorModelList);
        lvAuthor.setAdapter(authorAdapter);
        lvAuthor.setOnItemClickListener(this);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // volvemos a llamar metodo que obtiene datos
            searchItems();
            // desactivamos indicador de actualizacion
            swipeRefreshLayout.setRefreshing(false);
        });

        txtViewActivityButtonInfo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
    }

     private void findViews() {
         this.txtViewActivityButtonInfo = findViewById(R.id.txtButton_info);
         this.swipeRefreshLayout        = findViewById(R.id.swiperefreshOne);
         this.lvAuthor                  = findViewById(R.id.lvProviderOne);
         this.progressBar               = (ProgressBar) findViewById(R.id.idLoadingPBOne);
     }


  // listado de test
/*     private void loadAuthors(){
        authorModelList.clear();
        authorModelList.add(new AuthorModel(1, "Jorge Luis", "Borges", "https://www.cervantes.es/imagenes/Image/bibliotecas_documentacion_espanol/biobibliografias/borges_jorge_luis.jpg"));
        authorModelList.add(new AuthorModel(2, "Ernesto", "Sábato", "https://www.biografiasyvidas.com/biografia/s/fotos/sabato_ernesto_1.jpg"));
        authorModelList.add(new AuthorModel(3, "Roberto", "Arlt", "https://www.biografiasyvidas.com/biografia/a/fotos/arlt_roberto_1.jpg"));
        authorModelList.add(new AuthorModel(4, "Adolfo Bioy", "Casares", "https://www.biografiasyvidas.com/biografia/b/fotos/bioy_casares.jpg"));
        authorModelList.add(new AuthorModel(5, "Julio" , "Cortazar", "https://www.biografiasyvidas.com/biografia/c/fotos/cortazar_julio_1967.jpg"));
        authorModelList.add(new AuthorModel(6, "Alejandra" , "Pizarnik", "https://www.biografiasyvidas.com/biografia/p/fotos/pizarnik.jpg"));
        authorModelList.add(new AuthorModel(7, "Silvina" , "Ocampo", "https://www.biografiasyvidas.com/biografia/o/fotos/ocampo_silvina.jpg"));
        authorModelList.add(new AuthorModel(8, "Rodolfo" , "Walsh", "https://www.biografiasyvidas.com/biografia/w/fotos/walsh_rodolfo.jpg"));
        authorModelList.add(new AuthorModel(9, "Alfonsina" , "Storni", "https://www.biografiasyvidas.com/biografia/s/fotos/storni.jpg"));
   //     progressBar.setVisibility(View.GONE);
     }*/

     public void searchItems() {
         authorModelList.clear();

         Call<List<AuthorModel>> call = RetroAuthorClient.getInstance().getMyApiAuthor().searchAuthors();

         call.enqueue(new Callback<List<AuthorModel>>() {
             @Override
             public void onResponse(Call<List<AuthorModel>> call, Response<List<AuthorModel>> response) {


                 if (response.isSuccessful()){
                     authorModelList.addAll((Collection<? extends AuthorModel>) response.body());
                 }
                 authorAdapter.notifyDataSetChanged();
                 progressBar.setVisibility(View.GONE);
             }

             @Override
             public void onFailure(Call<List<AuthorModel>> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });

     }


     @Override
     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         AuthorModel provider = authorAdapter.getItem(i);

         Intent intent = new Intent(MainActivity.this, MainActivity2.class);

         intent.putExtra("AUTHOR_LASTNAME", provider.getLastName());

         startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

     }
 }