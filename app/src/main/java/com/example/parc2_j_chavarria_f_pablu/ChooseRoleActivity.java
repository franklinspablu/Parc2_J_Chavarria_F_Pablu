package com.example.parc2_j_chavarria_f_pablu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseRoleActivity extends AppCompatActivity {

    RadioGroup rgOpciones;
    //Intent i = getIntent();

    String name, rol, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
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
            Intent i = new Intent(getApplicationContext(), EstudianteActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Nombre", name);
            bundle.putString("Rol", rol);
            bundle.putString("Pwd", pwd);
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
                bundle.putString("Rol", rol);
                bundle.putString("Pwd", pwd);
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
