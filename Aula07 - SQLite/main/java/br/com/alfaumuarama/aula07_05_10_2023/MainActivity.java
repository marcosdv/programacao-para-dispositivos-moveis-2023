package br.com.alfaumuarama.aula07_05_10_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.alfaumuarama.aula07_05_10_2023.datasource.TbAluno;
import br.com.alfaumuarama.aula07_05_10_2023.models.Aluno;

public class MainActivity extends ListActivity {

    Button btnAdd;

    ArrayList<Aluno> listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Carregando os dados do aluno selecionado na listview da tela
        Aluno aluno = listaAlunos.get(position);

        //criando o caminho para abrir a tela de cadastro
        Intent tela = new Intent(MainActivity.this, CadastroActivity.class);

        //Criando os parametros com os dados do aluno
        Bundle params = new Bundle();
        params.putInt("id", aluno.id);
        params.putString("nome", aluno.nome);
        params.putString("ra", aluno.ra);

        //Adicionando os parametros no caminho de tela
        tela.putExtras(params);

        //abrindo a tela de cadastro
        startActivity(tela);
    }

    private void atualizarLista() {
        TbAluno tbAluno = new TbAluno(MainActivity.this);
        listaAlunos = tbAluno.buscarTodos();

        ListAdapter adapter = new SimpleAdapter(
            MainActivity.this, //Local onde esta a ListView (Nesta classe)
            listaToMap(listaAlunos), //Lista com os dados em formato HashMap
            R.layout.listview_modelo, //layout com o modelo de cada item (celula)
            new String[] { "nome", "ra" }, //as colunas com os dados
            new int[] { R.id.txtNome, R.id.txtRA } //cada campo que receberao os dados
        );

        setListAdapter(adapter);
    }

    private ArrayList<HashMap<String,String>> listaToMap(ArrayList<Aluno> lista) {
        ArrayList<HashMap<String,String>> listaRetorno = new ArrayList<>();

        for (Aluno aluno: lista) {
            listaRetorno.add(aluno.toMap());
        }

        return listaRetorno;
    }
}