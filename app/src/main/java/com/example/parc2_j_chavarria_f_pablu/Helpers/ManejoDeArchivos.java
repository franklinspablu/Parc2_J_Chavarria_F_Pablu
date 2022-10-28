package com.example.parc2_j_chavarria_f_pablu.Helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class ManejoDeArchivos {
    /*
     creacion de instancias de usuarios, tu sbes eso mano*/
    Usuarios users = new Usuarios("Juan Chavarria", "8-952-301", "123", 1);
    Usuarios users1 = new Usuarios("Franklin Pablu", "8-954-2298", "321", 2);

    public void WriteInFile(Context context){
        //creamos instancias de la clase OSW
        OutputStreamWriter writer = null, writer1 = null;
        try {
         writer = new OutputStreamWriter(context.openFileOutput("Login.txt", Context.MODE_PRIVATE));//creamos el archivo
         writer.write(users.getNombre()+ "~" + users.getCedula()+ "~"+ users.getContrasena()+ "~"+ users.getTipo()+ "\n");//escribimos en el archivo

         writer1 = new OutputStreamWriter(context.openFileOutput("Login1.txt", Context.MODE_PRIVATE));
         writer1.write(users1.getNombre()+ "~" + users1.getCedula()+ "~"+ users1.getContrasena()+ "~"+ users1.getTipo()+ "\n");

         writer.close(); // cerramos los archivos, es super necesario si no sale Erro de NULL POINTER
         writer1.close();

         Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show();
         //Log.d("TAG1","Archivo Guardado en: "+ context.getFilesDir() +"/"+ "Login.txt"); me sirvio para encontrar la ruta de donde se crean los archivos

        }catch (Exception e){
            Toast.makeText(context, "Error al escribir datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void EditFile(){ // para editar los registros, aun sin implementar, logica en proceso

    }

    public String[] FileReader(Context context, String cred) throws Exception {// lector de archivos
        try {
            String[] credenciales; //arreglo que guarda el split de las credenciales.

            if (cred.equals("credenciales")){
                BufferedReader bf = new BufferedReader(new InputStreamReader(context.openFileInput("Login.txt")));
                credenciales = bf.readLine().split("~");
                bf.close();
            }else{
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
