package com.example.parc2_j_chavarria_f_pablu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parc2_j_chavarria_f_pablu.Helpers.Notas;
import com.example.parc2_j_chavarria_f_pablu.Helpers.NotasAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EstudianteActivity extends AppCompatActivity {

    TextView lblNombre;
    ListView lvNotasEstudiantes;
    List<Notas> notasOpciones = new ArrayList<>();
    String name, rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);
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

            }else{
                Toast.makeText(this, "Datos no encontrados", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "ERROR"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void InicializarControles() {
        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lvNotasEstudiantes = (ListView) findViewById(R.id.lvNotasEstudiante);
        LoadListViewTemplate();
        LoadName();
    }

    private void LoadListViewTemplate(){
        List<Notas> opciones = this.GetElementsToListViewTemplate();
        NotasAdapter adapter = new NotasAdapter(this, opciones);
        lvNotasEstudiantes.setAdapter(adapter);
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
        Intent i = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }


    private void LoadName(){
        try {
            BufferedReader bf1 = new BufferedReader(new InputStreamReader(openFileInput("Login.txt")));
            String[] credenciales = bf1.readLine().split("~");
            bf1.close();
            lblNombre.setText("Bienvenid@ "+credenciales[0]);
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cerrarsesion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){


            case R.id.menuCerrarSesion:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
