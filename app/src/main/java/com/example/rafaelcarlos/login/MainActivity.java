package com.example.rafaelcarlos.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class MainActivity extends Activity {

    EditText etUsuario, etSenha;
    Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logar);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btLogar = (Button) findViewById(R.id.btLogar);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://localhost/android/index.php";
                //String urlGet="http://179.100.80.27/android/logar.php?usuario="+etUsuario.getText().toString()+"&senha="+etSenha.getText().toString();
                ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
                parametrosPost.add(new BasicNameValuePair("usuario", etUsuario.getText().toString()));
                parametrosPost.add(new BasicNameValuePair("senha", etSenha.getText().toString()));
                String respostaRetornada = null;
                Log.i("logar", "vai entrar no try");
                try {
                    respostaRetornada = ConexaoHttpCliente.executaHttpPost(url, parametrosPost);
                    //respostaRetornada = ConexaoHttpCliente.executaHttpGet(urlGet);
                    String resposta = respostaRetornada.toString();
                    Log.i("logar", "resposta = " + resposta);
                    resposta = resposta.replaceAll("\\s+", "");
                    if (resposta.equals("1")) {
                        startActivity(new Intent(MainActivity.this, Home.class));
                        mensagemExibir("Login", "Usuario Válido PARABÉNS ");
                    } else {
                        mensagemExibir("Login", "Usuario Inválido ????");
                    }
                }catch (Exception erro) {
                    Log.i("erro", "erro = " + erro);
                    Toast.makeText(MainActivity.this, "Erro.: " + erro, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void mensagemExibir(String titulo, String texto)
    {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(MainActivity.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK",null);
        mensagem.show();
    }


}
