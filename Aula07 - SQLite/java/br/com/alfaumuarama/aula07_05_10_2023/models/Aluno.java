package br.com.alfaumuarama.aula07_05_10_2023.models;

import android.content.ContentValues;

import java.util.HashMap;

public class Aluno {
    public int id;
    public String nome;
    public String ra;

    //Converter os dados da classe em ContentValues (padrao do SQLite)
    public ContentValues toDados() {
        ContentValues dados = new ContentValues();

        if (id > 0)
            dados.put("id", id);
        dados.put("nome", nome);
        dados.put("ra", ra);

        return dados;
    }

    //Converter os dados da classe em HashMap<> (padrao do ListView)
    public HashMap<String,String> toMap() {
        HashMap<String,String> dados = new HashMap<>();

        dados.put("id", String.valueOf(id));
        dados.put("nome", nome);
        dados.put("ra", ra);

        return dados;
    }
}