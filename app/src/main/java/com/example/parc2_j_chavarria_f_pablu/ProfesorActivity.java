package com.example.parc2_j_chavarria_f_pablu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parc2_j_chavarria_f_pablu.Helpers.Notas;
import com.example.parc2_j_chavarria_f_pablu.Helpers.NotasAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfesorActivity extends AppCompatActivity {
    TextView lblTitulo;
    ListView lvNotas;
    String cedula;
    List<Notas> notasOpciones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);
        InicializarControles();
    }

    private void InicializarControles() {
        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lvNotas = (ListView) findViewById(R.id.lvNotasProfesor);

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

                    materia =  pref.getString("materia",null);
                    nota = pref.getString("nota", null);
                    imvMateria = pref.getInt("imvMateria", 0);
                    imvStatus = pref.getInt("imvStatus", 0);

                    notasOpciones.add(new Notas(materia, nota,"Semestre",imvMateria,imvStatus));
                }

            }
        }catch (Exception e){
            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    return notasOpciones;
    }





}
