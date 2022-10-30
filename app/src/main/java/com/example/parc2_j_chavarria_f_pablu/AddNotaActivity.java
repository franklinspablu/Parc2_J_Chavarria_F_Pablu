package com.example.parc2_j_chavarria_f_pablu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNotaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spnNotas, spnMaterias;
    String materia, nota, descripcion, cedula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnota);

        getData();
        LoadSpinner();

    }
    public String getData(){
        Bundle b = getIntent().getExtras();
        if (b!=null) {
            return  cedula = b.getString("cedula");
        }
            return null;
    }
    private void LoadSpinner() {
        //Spinner de Notas
        spnNotas = (Spinner)findViewById(R.id.spnNotas);
        ArrayAdapter<CharSequence> notasAdapter = ArrayAdapter.createFromResource(this,R.array.Notas, android.R.layout.simple_spinner_item);
        notasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNotas.setAdapter(notasAdapter);
        spnNotas.setOnItemSelectedListener(this);
        //Spinner de materias
        spnMaterias = (Spinner)findViewById(R.id.spnMaterias);
        ArrayAdapter<CharSequence> materiasAdapter = ArrayAdapter.createFromResource(this,R.array.Materias, android.R.layout.simple_spinner_item);
        materiasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaterias.setAdapter(materiasAdapter);
        spnMaterias.setOnItemSelectedListener(this);

    }

    public void AddNota(View view)    {
        materia = spnMaterias.getSelectedItem().toString();
        nota = spnNotas.getSelectedItem().toString();
        int imvMateria = GetImagenMateria(materia);
        int imvStatus = GetImagenNota(nota);

        SharedPreferences prefs =  getSharedPreferences("notas", Context.MODE_PRIVATE);  //Crea el shared prefs
        SharedPreferences.Editor editor = prefs.edit();
        try {
        int indx = (int) spnMaterias.getSelectedItemId();
        editor.putString("materia-"+indx,materia);
        editor.putString("nota-"+indx,nota);
        editor.putInt("imvMateria-"+indx,imvMateria);
        editor.putInt("imvStatus-"+indx,imvStatus);

        editor.commit();

            Toast.makeText(this, "se hizo commit de shared preferences", Toast.LENGTH_SHORT).show();
            Bundle b = new Bundle();
            b.putString("cedula", cedula);

            Intent i = new Intent(getApplicationContext(), ProfesorActivity.class);
            i.putExtras(b);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, "Error en addNota a ProfesorAct "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public int GetImagenMateria(String materia){

        int imvMateria =0;

        switch (materia){
            case "Circuitos Logicos":
            case "Electronica Basica":
                imvMateria = R.drawable.electricalcircuit;
                break;
            case "Sistemas bas. en conoc.":
            case "Animación Digital y Vid." :
            case "Ingenieria de Sist. Din.":
            case "Estructuras de datos 2":
                imvMateria = R.drawable.computerscience;
                break;
            case  "Ingeniería de Software 1":
            case  "Ingeniería de Software 2":
                imvMateria = R.drawable.architechture;
                break;
            case "Sistemas Operativos" :
            case "Organizacion y Arquitectura de computadoras":
                imvMateria = R.drawable.cpu;
                break;
        }

        return imvMateria;

    }


    public int GetImagenNota(String nota){

        int imvStatus = 0;

        switch (nota){
            case "A":
            case "B":
            case "C":
                imvStatus = R.drawable.check;
                break;
            case "D":
            case "F":
                imvStatus = R.drawable.cancel;
                break;
            case "N":
                imvStatus = R.drawable.empty;
        }

    return  imvStatus;

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
