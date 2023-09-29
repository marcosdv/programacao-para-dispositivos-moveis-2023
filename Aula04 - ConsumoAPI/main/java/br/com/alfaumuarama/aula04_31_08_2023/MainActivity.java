//leonardo saraiva
package br.com.alfaumuarama.aula04_31_08_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.com.alfaumuarama.aula04_31_08_2023.datasource.BuscarDadosApi;
import br.com.alfaumuarama.aula04_31_08_2023.models.Pokemon;

public class MainActivity extends ListActivity {

    private ArrayList<Pokemon> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new BuscarDadosApi().execute(Config.link).get();

            ListAdapter adapter = new SimpleAdapter(
                this, //a classe que tem o objeto listView no XML
                dadosToMap(listaDados), //lista com os dados em formato HashMap
                R.layout.listview_modelo, //Layout de modelo para cada item da lista
                new String[] { "nome" }, //campo dos dados que sera carregado na lista
                new int[] { R.id.txtNome } //em que item da lista carregara os dados
            );

            setListAdapter(adapter);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Pokemon pokemon = listaDados.get(0);
        //Toast.makeText(this, pokemon.nome, Toast.LENGTH_LONG).show();
    }

    private List<HashMap<String,String>> dadosToMap(ArrayList<Pokemon> listaDados) {
        List<HashMap<String,String>> lista = new ArrayList<>();

        for (int i = 0; i < listaDados.size(); i++) {
            HashMap<String,String> item = new HashMap<>();
            item.put("id", String.valueOf(listaDados.get(i).id()));
            item.put("nome", listaDados.get(i).nome);
            item.put("imagem", listaDados.get(i).imagem());

            lista.add(item);
        }

        return lista;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //position -> tem o indice do item clicado na ListView

        Pokemon pokemon = listaDados.get(position);

        //criando o caminho para a tela de Detalhes
        Intent tela = new Intent(MainActivity.this, DetalhesActivity.class);

        //Criando objeto Bundle para enviar os dados para a detalhes
        Bundle parametros = new Bundle();
        parametros.putString("nome", pokemon.nome);
        parametros.putString("imagem", pokemon.imagem());

        //adicionando os parametros no caminho da tela
        tela.putExtras(parametros);

        //abrindo a tela de detelhes
        startActivity(tela);
    }
}