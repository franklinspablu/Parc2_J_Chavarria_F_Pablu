package com.example.parc2_j_chavarria_f_pablu;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    ManejoDeArchivos file = new ManejoDeArchivos();
    EditText txtCedula, txtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicializarControles();
        PreCargarDatos();
    }


    private void InicializarControles(){
        txtCedula = findViewById(R.id.txtcedula);
        txtContrasena = findViewById(R.id.txtcontraseña);
    }

    public void IngresarOnClick(View v){ //metodo del boton

        try {
            iniciarSesion();
        }catch (Exception e){
            Toast.makeText(this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT).show();
        }

    }

    private void iniciarSesion() {
        VerifyUser();
    } //metodo que invoca al verifyUser()

    public void PreCargarDatos(){ //precarga los datos ira a SaveInFile
        try {
            SaveInFile(); //Escribe en el archivo
        }catch (Exception e){
            Toast.makeText(this, "Error al cargar datos"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void SaveInFile(){
        file.WriteInFile(getApplicationContext());
    } /*usamos el objeto file de la clase ManejoDeArchivos para escribir en el archivo y le pasamos
    el contexto de la aplicacion para poder utilizar el openFileOutput ya que este necesita estar en un activity para funcionar,
    como no está entonces le pasamos el contexto de la actividad*/

    public void VerifyUser(){
        String[] credenciales = null, credenciales1 = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("Login.txt")));
            credenciales = bf.readLine().split("~");
            bf.close();

            BufferedReader bf1 = new BufferedReader(new InputStreamReader(openFileInput("Login1.txt")));
            credenciales1 = bf1.readLine().split("~");
            bf1.close();

         if (txtCedula.getText().toString().equals(credenciales[1]) && txtContrasena.getText().toString().equals(credenciales[2])){
             Toast.makeText(this, "Sesion Aprobada", Toast.LENGTH_LONG).show();
             TransitionToChooseRole(credenciales);

         }else if (txtCedula.getText().toString().equals(credenciales1[1]) && txtContrasena.getText().toString().equals(credenciales1[2])){
             Toast.makeText(this, "Sesion Aprobada", Toast.LENGTH_LONG).show();
             TransitionToChooseRole(credenciales1);
         } else {
             Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TransitionToChooseRole(String[] users){
        Bundle bundle = new Bundle();
        bundle.putString("Nombre", users[0]);
        bundle.putString("Rol", users[3]);
        Intent i = new Intent(this, ChooseRoleActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

}