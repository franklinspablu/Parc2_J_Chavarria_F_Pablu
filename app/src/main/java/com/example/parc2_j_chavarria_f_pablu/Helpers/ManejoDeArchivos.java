package com.example.parc2_j_chavarria_f_pablu.Helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.parc2_j_chavarria_f_pablu.ChooseRoleActivity;
import com.example.parc2_j_chavarria_f_pablu.MainActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class ManejoDeArchivos {
    /*
     creacion de instancias de usuarios, tu sabes eso mano*/
    Usuarios users;

    public void WriteInFileEstudiante(Context context, Usuarios user){
        //creamos instancias de la clase OSW
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(context.openFileOutput("Login.txt", Context.MODE_PRIVATE));//creamos el archivo
            writer.write(user.getNombre()+ "~" + user.getCedula()+ "~"+ user.getContrasena()+ "~"+ user.getTipo()+ "\n");//escribimos en el archivo
            writer.close(); // cerramos los archivos, es super necesario si no sale Erro de NULL POINTER
            Toast.makeText(context, "Datos Actualizados", Toast.LENGTH_SHORT).show();
            //Log.d("TAG1","Archivo Guardado en: "+ context.getFilesDir() +"/"+ "Login.txt"); me sirvio para encontrar la ruta de donde se crean los archivos
        }catch (Exception e){
            Toast.makeText(context, "Error al escribir datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void WriteInFileProfesor(Context context, Usuarios user){
        //creamos instancias de la clase OSW
        OutputStreamWriter writer1 = null;
        try {
         writer1 = new OutputStreamWriter(context.openFileOutput("Login1.txt", Context.MODE_PRIVATE));
            writer1.write(user.getNombre()+ "~" + user.getCedula()+ "~"+ user.getContrasena()+ "~"+ user.getTipo()+ "\n");
           // cerramos los archivos, es super necesario si no sale Erro de NULL POINTER
            writer1.close();
            Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show();
            //Log.d("TAG1","Archivo Guardado en: "+ context.getFilesDir() +"/"+ "Login.txt"); me sirvio para encontrar la ruta de donde se crean los archivos
        }catch (Exception e){
            Toast.makeText(context, "Error al escribir datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void UpdateFile(Context context, String Nombre, String Contrasena){ // para actualizar los registros, del estudiante
        try {
          WriteInFileEstudiante(context, users = new Usuarios(Nombre, "8-952-301", Contrasena, 1));
        }catch (Exception e){
            Toast.makeText(context, "Error al escribir datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void UpdateFile1(Context context, String Nombre, String Contrasena){ // para actualizar los registros, del profesor

        try {
           WriteInFileProfesor(context, users = new Usuarios(Nombre, "8-954-2298", Contrasena, 2));
        }catch (Exception e){
            Toast.makeText(context, "Error al escribir datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public String[] FileReader(Context context, String rol) throws Exception {// lector de archivos
        String[] credenciales = null; //arreglo que guarda el split de las credenciales.
        try {
            if (rol.equals("1")){
                BufferedReader bf = new BufferedReader(new InputStreamReader(context.openFileInput("Login.txt")));
                credenciales = bf.readLine().split("~");
                bf.close();
            }else if(rol.equals("2")){
                BufferedReader bf1 = new BufferedReader(new InputStreamReader(context.openFileInput("Login1.txt")));
                credenciales = bf1.readLine().split("~");
                bf1.close();
            }
            Toast.makeText(context, "Lectura exitosa!", Toast.LENGTH_SHORT).show();
            return credenciales;
        }catch (Exception e) {
            Toast.makeText(context, "Error al leer datos " +e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return null;
    }







}
