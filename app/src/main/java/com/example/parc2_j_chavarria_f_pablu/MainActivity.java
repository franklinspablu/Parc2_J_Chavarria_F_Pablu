package com.example.parc2_j_chavarria_f_pablu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parc2_j_chavarria_f_pablu.Helpers.ManejoDeArchivos;
import com.example.parc2_j_chavarria_f_pablu.Helpers.Usuarios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WriteInFile();

        Intent i = new Intent(getApplicationContext(), IniciarSesion.class);
        startActivity(i);

    }

    public void WriteInFile(){
        //creamos instancias de la clase OSW
        OutputStreamWriter writer = null, writer1 = null;
        try {
            writer = new OutputStreamWriter(openFileOutput("Login.txt", Context.MODE_PRIVATE));//creamos el archivo
            writer.write("Juan Chavarria"+ "~" + "8-952-301"+ "~"+ "1"+ "~"+ "1"+ "\n");//escribimos en el archivo
            writer.close(); // cerramos los archivos, es super necesario si no sale Erro de NULL POINTER

            writer1 = new OutputStreamWriter(openFileOutput("Login1.txt", Context.MODE_PRIVATE));
            writer1.write("Franklin Pablu"+ "~" + "8-954-2298"+ "~"+ "2"+ "~"+ "2"+ "\n");
            writer1.close();
        }catch (Exception e){
            Toast.makeText(this, "Error al escribir datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}