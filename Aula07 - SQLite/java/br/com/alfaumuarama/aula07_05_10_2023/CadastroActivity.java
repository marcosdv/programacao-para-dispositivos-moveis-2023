package br.com.alfaumuarama.aula07_05_10_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alfaumuarama.aula07_05_10_2023.datasource.TbAluno;
import br.com.alfaumuarama.aula07_05_10_2023.models.Aluno;

public class CadastroActivity extends AppCompatActivity {

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
        Aluno aluno = new Aluno();
        aluno.nome = edtNome.getText().toString();
        aluno.ra = edtRA.getText().toString();

        TbAluno tbAluno = new TbAluno(CadastroActivity.this);
        tbAluno.gravar(aluno);

        onBackPressed(); //Depois de gravar, fecha a tela
    }

    private void excluir() {
    }

    private void cancelar() {
        onBackPressed();
    }
}