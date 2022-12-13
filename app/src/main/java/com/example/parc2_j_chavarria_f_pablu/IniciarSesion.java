package com.example.parc2_j_chavarria_f_pablu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parc2_j_chavarria_f_pablu.Helpers.ManejoDeArchivos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IniciarSesion extends AppCompatActivity {

    ManejoDeArchivos file = new ManejoDeArchivos();
    EditText txtCedula, txtContrasena;
    String name, rol, pwd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);

        Bundle bundle = getIntent().getExtras();
        InicializarControles();
        getData(bundle);

    }

    public void getData(Bundle bundle){
        try {
            if (bundle!=null){
                //Toast.makeText(this, "Data encontrada", Toast.LENGTH_SHORT).show();
                name = bundle.getString("Nombre");
                rol = bundle.getString("Rol");
                pwd = bundle.getString("Pwd");
            }else{
                Toast.makeText(this, "Datos no encontrados", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "ERROR"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void InicializarControles(){
        txtCedula = findViewById(R.id.txtcedula);
        txtContrasena = findViewById(R.id.txtcontraseña);
    }

    public void IngresarOnClick(View v){ //metodo del boton
        try {
            VerifyUser();
        }catch (Exception e){
            Toast.makeText(this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT).show();
        }

    }

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
        bundle.putString("Pwd", users[2]);
        Intent i = new Intent(this, ChooseRoleActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
