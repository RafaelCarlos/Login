package com.example.rafaelcarlos.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class CadastroUsuario extends ActionBarActivity {

    EditText etUsuario, etSenha;
    Button ibCadastar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        etUsuario = (EditText) findViewById(R.id.etUsuarioCadastro);
        etSenha = (EditText) findViewById(R.id.etSenhaCadastro);
        ibCadastar = (Button) findViewById(R.id.btCadastrar);

        ibCadastar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vazios = "";
                if (etUsuario.getText().toString().equals(""))
                    vazios="Campo Usuário não pode estar vazio\n\n";

                if (etSenha.getText().toString().equals(""))
                    vazios+="Campo Senha não pode estar vazio";

                if (etUsuario.getText().toString().equals("") || etSenha.getText().toString().equals(""))
                    mensagemExibir("Erro na Gravação.:", ""+vazios);
                else {


                    String url = "http://177.25.121.168/android/cadastrarUsuario.php";
                    //String urlGet="http://179.100.80.27/android/logar.php?usuario="+etUsuario.getText().toString()+"&senha="+etSenha.getText().toString();
                    ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
                    parametrosPost.add(new BasicNameValuePair("usuario", etUsuario.getText().toString()));
                    parametrosPost.add(new BasicNameValuePair("senha", etSenha.getText().toString()));
                    String respostaRetornada = null;
                    Log.i("logar", "vai entrar no try");
                    try {
                        Log.i("Entrou: ", "Dentro do try");
                        respostaRetornada = ConexaoHttpCliente.executaHttpPost(url, parametrosPost);
                        //respostaRetornada = ConexaoHttpCliente.executaHttpGet(urlGet);

                        Log.i("POST", "Recebeu post: " + respostaRetornada);
                        String resposta = respostaRetornada.toString();
                        Log.i("logar", "resposta = " + resposta);
                        resposta = resposta.replaceAll("\\s+", "");
                        if (resposta.equals("1"))
                            //startActivity(new Intent(CadastroUsuario.this, Home.class));
                            mensagemExibir("Feito", "Usuario Cadastrado com Sucesso!");
                        else
                            mensagemExibir("Erro", "Não foi possível cadastrar o usuário!");

                    } catch (Exception erro) {
                        Log.i("erro", "erro = " + erro);
                        //Toast.makeText(CadastroUsuario.this, "Erro.: " + erro, Toast.LENGTH_LONG).show();
                        mensagemExibir("Erro", "Erro ao cadastrar: " + erro);
                    }
                }
            }
        });


    }
    public void mensagemExibir(String titulo, String texto)
    {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(CadastroUsuario.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK",null);
        mensagem.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
