package com.example.atividade10_09;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {
    public Usuario getInformacao(String end){
        String json = ConexaoApi.getJsonFromApi(end);
        Usuario retorno = parseJson(json);
        return retorno;
    }

    private Usuario parseJson(String json){
        try {
            Usuario usuario = new Usuario();

            JSONObject jsonObj = new JSONObject(json);

            usuario.setNome(jsonObj.getString("name"));
            usuario.setNomeDeUsuario(jsonObj.getString("login"));
            usuario.setUrl(jsonObj.getString("html_url"));
            usuario.setLocalizacao(jsonObj.getString("location"));
            usuario.setFoto(baixarImagem(jsonObj.getString("avatar_url")));

            return usuario;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap baixarImagem(String url) {
        //converte a imagem para o formato Bitmap
        try {
            URL endereco = new URL(url);
            InputStream inputStream = endereco.openStream();
            Bitmap imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
