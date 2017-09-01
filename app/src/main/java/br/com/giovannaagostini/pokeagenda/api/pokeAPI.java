package br.com.giovannaagostini.pokeagenda.api;

import java.util.List;

import br.com.giovannaagostini.pokeagenda.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface pokeAPI {

    @GET(value = "pokemon/{numeropokemon}")
    Call<Pokemon> buscaPokemon(@Path(value = "numeropokemon") String pokemon);

}
