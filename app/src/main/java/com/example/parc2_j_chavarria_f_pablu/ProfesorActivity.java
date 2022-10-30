package com.example.parc2_j_chavarria_f_pablu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parc2_j_chavarria_f_pablu.Helpers.ManejoDeArchivos;
import com.example.parc2_j_chavarria_f_pablu.Helpers.Notas;
import com.example.parc2_j_chavarria_f_pablu.Helpers.NotasAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProfesorActivity extends AppCompatActivity {

    TextView lblTitulo, lblNombre;
    ListView lvNotas;
    String name, rol, pwd;
    List<Notas> notasOpciones = new ArrayList<>();
    ManejoDeArchivos file = new ManejoDeArchivos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);
        Bundle bundle = getIntent().getExtras();
        getData(bundle);
        InicializarControles();

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

    private void InicializarControles() {
        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lvNotas = (ListView) findViewById(R.id.lvNotasProfesor);
        lblNombre = findViewById(R.id.lblNombre);
        LoadListViewTemplate();
        LoadName();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addnota,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuAgregar:
                Intent i = new Intent(getApplicationContext(), AddNotaActivity.class);
                startActivity(i);
                break;
            case R.id.menuCloseSession:

                Intent intent = new Intent(getApplicationContext(), IniciarSesion.class);

                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void LoadListViewTemplate(){
        List<Notas> opciones = this.GetElementsToListViewTemplate();
        NotasAdapter adapter = new NotasAdapter(this, opciones);
        lvNotas.setAdapter(adapter);
    }

    private List<Notas> GetElementsToListViewTemplate(){
        try {
            SharedPreferences pref = getSharedPreferences("notas", Context.MODE_PRIVATE);
            String materia, nota, descripcion;
            int imvMateria, imvStatus;

            for (int i=0; i<10; i++){
                if(pref.getString("materia-"+i,null)!=null){
                    /*para circuitos
                    * materia = materia-0 = Circuitos Logicos
                    * nota = nota-1 = B
                    * imvMateria = imvMateria-0 = 102554220
                    * imvNota = imvNota-1 = 15168465*/
                    materia =  pref.getString("materia-"+i,null);
                    nota = pref.getString("nota-"+i, null);
                    imvMateria = pref.getInt("imvMateria-"+i, 0);
                    imvStatus = pref.getInt("imvStatus-"+i, 0);

                    notasOpciones.add(new Notas(materia, nota,"Semestre",imvMateria,imvStatus));
                }
            }
        }catch (Exception e){
            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    return notasOpciones;
    }


    public void UpdateUser(View view){
        Bundle bundle = new Bundle();
        bundle.putString("Nombre", name);
        bundle.putString("Rol", rol);
        bundle.putString("Pwd", pwd);
        Intent i = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }


    private void LoadName(){
        try {
            BufferedReader bf1 = new BufferedReader(new InputStreamReader(openFileInput("Login1.txt")));
            String[] credenciales1 = bf1.readLine().split("~");
            bf1.close();
                lblNombre.setText("Bienvenid@ Profesor@ "+credenciales1[0]);
        }catch (Exception e){
            System.out.println("Error");
        }
    }


}