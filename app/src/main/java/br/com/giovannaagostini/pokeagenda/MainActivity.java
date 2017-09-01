package br.com.giovannaagostini.pokeagenda;

import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.giovannaagostini.pokeagenda.api.pokeAPI;
import br.com.giovannaagostini.pokeagenda.model.Pokemon;
import br.com.giovannaagostini.pokeagenda.model.Types;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private TextView tvNome, tvTipo;
    private ImageView ivImagem;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = (EditText) findViewById(R.id.etNumero);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        ivImagem = (ImageView) findViewById(R.id.ivImagem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.pesquisar:
                Pesquisar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void limparTela() {
        tvNome.setText("");
        tvTipo.setText("");
        ivImagem.setImageDrawable(null);
    }

    public void Pesquisar() {
        showDialog();

        limparTela();

        pokeAPI service = getRetrofit().create(pokeAPI.class);
        service.buscaPokemon(etNumero.getText().toString()).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                switch (response.code()) {
                    case 200:
                        if (response.body() != null)
                            showPokemon(response.body());
                        break;
                    case 404:
                        Toast.makeText(MainActivity.this,
                                "Pokémon não encontrado",
                                Toast.LENGTH_SHORT).show();
                        break;

                }
                dismissDialog();

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Não foi possível encontrar os dados!",
                        Toast.LENGTH_SHORT).show();
                dismissDialog();
            }
        });
    }

    private void showPokemon(Pokemon pokemon) {
        tvNome.setText(pokemon.getNome());
        if (pokemon.getTipos() != null) {
            for (Types types : pokemon.getTipos()) {
                tvTipo.setText(tvTipo.getText().toString() + "\n" + types.getType().getName());
            }
        }
        Picasso.with(MainActivity.this)
                .load(pokemon.getSprite().getUrlImagem())
                .placeholder(R.drawable.pokebola)
                .error(R.drawable.pokebola_erro)
                .into(ivImagem);
    }

    private void showDialog() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.drawable.pokebola);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Pokémon");
        progressDialog.setMessage("Temos que pegar isso eu sei!!! Pegá-los eu tentarei.");
        progressDialog.show();
    }

    private void dismissDialog() {
        if(progressDialog != null)
            progressDialog.dismiss();
    }
}
