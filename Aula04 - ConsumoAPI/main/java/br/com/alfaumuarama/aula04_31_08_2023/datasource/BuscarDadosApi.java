package br.com.alfaumuarama.aula04_31_08_2023.datasource;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.alfaumuarama.aula04_31_08_2023.models.Pokemon;

public class BuscarDadosApi extends AsyncTask<String, Void, ArrayList<Pokemon>> {

    @Override
    protected ArrayList<Pokemon> doInBackground(String... strings) {
        ArrayList<Pokemon> listaDados = new ArrayList<>();

        try {
            //capturando a primeira posicao do vetor de strings
            String link = strings[0];

            //criando uma URL valida, apartir do link
            URL url = new URL(link);

            //Criando uma conexao apartir da URL
            URLConnection connection = url.openConnection();

            //Salvando na memoria o retorno da API
            InputStream stream = connection.getInputStream();

            //Pegando os dados de memoria e colocando num reader
            InputStreamReader inputStreamReader = new InputStreamReader(stream);

            //Pegando os dados do reader e carregando no buffer que pode ser lido
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha;

            //enquanto existir dados para ler no reader, salva o valor nos dados
            while ((linha = reader.readLine()) != null) {
                dados += linha; //dados = dados + linha;
            }

            //transforma os dados de texto em um Objeto JSON
            JSONObject json = new JSONObject(dados);

            //capturando o vetor de dentro do item RESULTS do JSON
            JSONArray lista = new JSONArray(json.getString("results"));

            for (int i = 0; i < lista.length(); i++) {
                //pega o item atual da lista e salva em ITEM
                JSONObject item = (JSONObject)lista.get(i);

                //mapeando os campos do JSON para a classe Pokemon
                Pokemon pokemon = new Pokemon();
                pokemon.nome = item.getString("name");
                pokemon.url = item.getString("url");

                listaDados.add(pokemon);
            }
        }
        catch (Exception ex) {
        }

        return listaDados;
    }
}
