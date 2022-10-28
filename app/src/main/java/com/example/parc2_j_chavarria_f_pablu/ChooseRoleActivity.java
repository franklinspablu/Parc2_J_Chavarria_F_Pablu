package com.example.parc2_j_chavarria_f_pablu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.RadioAccessSpecifier;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseRoleActivity extends AppCompatActivity {

    RadioGroup rgOpciones;
    Intent i = getIntent();
    Bundle bundle = getIntent().getExtras();
    String name, rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        InicializarControles();
        getData();

    }

    public void getData(){

        if (!bundle.isEmpty()){

            name = bundle.getString("Nombre");
            rol = bundle.getString("Rol");

        }else{
            Toast.makeText(this, "Datos no encontrados", Toast.LENGTH_SHORT).show();
        }
    }

    public void InicializarControles(){
        rgOpciones = (RadioGroup)findViewById(R.id.rgOpciones);
    }



    public void Entrar(View v){

        try {
               switch (rgOpciones.getCheckedRadioButtonId()){
                   case (R.id.rbEstudiante):
                        EstudianteScreen();
                       break;
                   case (R.id.rbSIU):
                        SiuScreen();
                       break;
                   case (R.id.rbProfesor):
                        ProfesorScreen();
                       break;
               }
        }catch (Exception e){
            Toast.makeText(this, "Hay un error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void EstudianteScreen(){
        try {
            Intent i = new Intent(getApplicationContext(),GradesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Nombre", name);
            i.putExtras(bundle);
            if (rol.equals("1")){
                startActivity(i);
            }else{
                Toast.makeText(this, "Usted no es un estudiante activo!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Hay un error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void ProfesorScreen(){
            try {
                Intent i = new Intent(getApplicationContext(),ProfesorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Nombre", name);
                i.putExtras(bundle);
                if (rol.equals("2")){
                    startActivity(i);
                }else {
                    Toast.makeText(this, "Usted no es un profesor!", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(this, "Hay un error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }

    public void SiuScreen(){

        try {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://utp.ac.pa"));
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, "Hay un error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



}
