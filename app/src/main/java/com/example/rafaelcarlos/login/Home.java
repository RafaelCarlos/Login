package com.example.rafaelcarlos.login;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;




public class Home extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] cursos = new String[]{"Cadastro Usuários", "Lista de Usuarios", "Sobre o prof Neri", "Comprar Videoaulas", "Contato", "Sair"};
        ArrayAdapter<String> aaCursos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cursos);
        setListAdapter(aaCursos);

    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Object objetoSelecionado = this.getListAdapter().getItem(position);
        String menuSelecionado = objetoSelecionado.toString();
        //Toast.makeText(this, "Menu Selecionado: "+menuSelecionado, Toast.LENGTH_SHORT).show();

        switch(position) {
            case 0:
                startActivity(new Intent(this,CadastroUsuario.class)); break;
//		 case 1:
//			 startActivity(new Intent(this,AutoCompletarCursosActivity.class)); break;
            case 5:
                finish(); break;
            default: finish();


        }

    }
}

