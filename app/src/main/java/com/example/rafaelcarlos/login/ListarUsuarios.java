package com.example.rafaelcarlos.login;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class ListarUsuarios extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String[] usuarios = new String[10];
        int posicao=0;

        List<String> usu = new ArrayList<>();

        String url = "http://localhost/android/listarUsuario.php";
        //String urlGet="http://179.100.80.27/android/logar.php?usuario="+etUsuario.getText().toString()+"&senha="+etSenha.getText().toString();

        String respostaRetornada = null;
        Log.i("logar", "vai entrar no try");
        try {
            respostaRetornada = ConexaoHttpCliente.executaHttpGet(url);
            String resposta = respostaRetornada.toString();
            Log.i("Usuarios", "Usuários são = " + resposta);

//            char caracter_lido ='n';
//
//            for (int i = 0; caracter_lido !='^'; i++)
//            {
//                caracter_lido = resposta.charAt(i);
//                Log.i("Char Usuários!"," "  + caracter_lido);
//            }
            char caracter_lido =resposta.charAt(0);

            String nome ="";
            for (int i = 0; caracter_lido !='^'; i++)
            {
                caracter_lido = resposta.charAt(i);
                if(caracter_lido != '#')
                nome +=(char) caracter_lido;

                else {

                    usuarios[posicao] =""+nome;

                    Log.i("Char Usuários!"," "  + caracter_lido);

                }
            }



        } catch (Exception erro) {
            Log.i("erro", "erro = " + erro);
            Toast.makeText(ListarUsuarios.this, "Erro.: " + erro, Toast.LENGTH_LONG).show();
        }


        ArrayAdapter<String> aaCursos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usuarios);
        setListAdapter(aaCursos);

    }

    public void mensagemExibir(String titulo, String texto) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(ListarUsuarios.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK", null);
        mensagem.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_usuarios, menu);
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
