package br.com.alfaumuarama.aula07_05_10_2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alfaumuarama.aula07_05_10_2023.datasource.TbAluno;
import br.com.alfaumuarama.aula07_05_10_2023.models.Aluno;

public class CadastroActivity extends AppCompatActivity {

    int idAluno = 0;

    EditText edtNome, edtRA;
    Button btnSalvar, btnExcluir, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtRA = findViewById(R.id.edtRA);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnCancelar = findViewById(R.id.btnCancelar);

        //captura o caminho usado para abrir esta tela
        Intent caminhoRecebido = getIntent();

        //se o caminho existe
        if (caminhoRecebido != null) {
            //captura os parametros recebidos vindo da outra tela
            Bundle params = caminhoRecebido.getExtras();

            //se existe os parametros
            if (params != null) {
                idAluno = params.getInt("id");
                edtNome.setText(params.getString("nome"));
                edtRA.setText(params.getString("ra"));
            }
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
    }

    private void salvar() {
        if (edtNome.getText().toString().isEmpty()) {
            ShowMensagem("O campo Nome é obrigatório!");
            return;
        }
        if (edtRA.getText().toString().isEmpty()) {
            ShowMensagem("O campo RA é obrigatório!");
            return;
        }

        Aluno aluno = new Aluno();
        aluno.id = idAluno;
        aluno.nome = edtNome.getText().toString();
        aluno.ra = edtRA.getText().toString();

        TbAluno tbAluno = new TbAluno(CadastroActivity.this);
        tbAluno.gravar(aluno);

        onBackPressed(); //Depois de gravar, fecha a tela
    }

    private void excluir() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
        alerta.setTitle("Atenção"); //Adicionando o titulo da mensagem
        alerta.setMessage("Deseja excluir este aluno?"); //Adicionando o texto da mensagem
        alerta.setNegativeButton("Não", null); //Botao para fechar a mensagem
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmarExclusao();
            }
        });
        alerta.show();
    }

    private void confirmarExclusao() {
        TbAluno tbAluno = new TbAluno(CadastroActivity.this);
        tbAluno.excluir(idAluno);

        onBackPressed(); //Depois de excluir, fecha a tela
    }

    private void cancelar() {
        onBackPressed();
    }

    private void ShowMensagem(String texto) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
        alerta.setTitle("Atenção"); //Adicionando o titulo da mensagem
        alerta.setMessage(texto); //Adicionando o texto da mensagem
        alerta.setNeutralButton("OK", null); //Adicionando botao de OK
        alerta.show(); //Exibindo a mensagem na tela
    }
}