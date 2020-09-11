package com.example.atividade10_09;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private TextView nomeDeUsuario;
    private TextView url;
    private TextView localizacao;
    private ImageView foto;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetUsuario getusuario = new GetUsuario();

        nome = (TextView)findViewById(R.id.nomeDeUsuario);
        nomeDeUsuario = (TextView)findViewById(R.id.nome);
        url = (TextView)findViewById(R.id.url);
        localizacao = (TextView)findViewById(R.id.localizacao);
        foto = (ImageView)findViewById(R.id.imageView);



        //Chama Async Task
        getusuario.execute();


    }

    private class GetUsuario extends AsyncTask<Void, Void, Usuario> {

        @Override
        protected void onPreExecute(){
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Usuario doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("https://api.github.com/users/brunopessi");
        }

        @Override
        protected void onPostExecute(Usuario usuario){
            nome.setText(usuario.getNome());
            nomeDeUsuario.setText(usuario.getNomeDeUsuario());
            url.setText(usuario.getUrl());
            localizacao.setText(usuario.getLocalizacao());
            foto.setImageBitmap(usuario.getFoto());
            load.dismiss(); //deleta o dialog
        }
    }
}