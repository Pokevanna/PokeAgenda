package br.com.giovannaagostini.pokeagenda.model;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    @SerializedName(value = "name")
    private String nome;
    private Integer numero;
    @SerializedName(value = "sprites")
    private Sprite sprite;
    @SerializedName(value = "types")
    private List<Types> tipos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public List<Types> getTipos() {
        return tipos;
    }

    public void setTipos(List<Types> tipos) {
        this.tipos = tipos;
    }
}
