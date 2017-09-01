package br.com.giovannaagostini.pokeagenda.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by logonrm on 01/09/2017.
 */

public class Sprite {

    @SerializedName(value = "front_default")
    private String urlImagem;

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
