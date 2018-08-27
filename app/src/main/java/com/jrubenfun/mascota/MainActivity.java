package com.jrubenfun.mascota;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;
    ImageButton starButton;
    int indices[] = new int[5];
    ArrayList<Mascota> topFiveMascotas;
    String[] topNombres = new String[5];;
    String[] topRates = new String[5];
    int topFoto[] = new int[5];

    public Toolbar toolbar;
    public TableLayout tableLayout;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaMascotas = (RecyclerView) findViewById(R.id.rvListaMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarMascotas();
        inicializarAdaptador();

        starButton = (ImageButton) findViewById(R.id.starButton);
        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rates[] = new int[mascotas.size()];
                //Toast.makeText(MainActivity.this,Integer.toString(rates.length),Toast.LENGTH_SHORT).show();

                for (int i=0; i<rates.length;i++){
                    rates[i]=Integer.parseInt(mascotas.get(i).getRate());
                    //Log.v("Contador " + Integer.toString(i),Integer.toString(rates[i]));
                }

                topCinco(rates);
                crearTopFive();

                Intent intent = new Intent(MainActivity.this,ListaMascotas.class);
                intent.putExtra("TopNombres",topNombres);
                intent.putExtra("TopRates",  topRates);
                intent.putExtra("TopFoto",   topFoto);
                startActivity(intent);

            }
        });

        //toolbar = (Toolbar) findViewById(R.id.action_bar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null


        //    setSupportActionBar(ToolbarWidgetWrapper);


        
    }


    public void inicializarMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro1,"john","31"));
        mascotas.add(new Mascota(R.drawable.perro2,"happy","23"));
        mascotas.add(new Mascota(R.drawable.perro3,"ears","20"));
        mascotas.add(new Mascota(R.drawable.perro4,"furry","18"));
        mascotas.add(new Mascota(R.drawable.perro5,"black","15"));
        mascotas.add(new Mascota(R.drawable.perro6,"run","10"));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        listaMascotas.setAdapter(adaptador);
    }

    public void topCinco(int[] rates){

        int i;
        int max = 0;
        int indiceAux;
        for (int j=0; j<5; j++){
            max = rates[0];
            indiceAux = 0;
            for (i =1; i<rates.length; i++){
                if (max< rates[i]){
                    max=rates[i];
                    indiceAux=i;
                }
            }
            indices[j]=indiceAux;
            rates[indiceAux]=Integer.MIN_VALUE;
        }
    }

    public void crearTopFive(){
        topFiveMascotas = new ArrayList<Mascota>();

        for (int i=0;i<indices.length;i++){
            //Log.v("Indices " + Integer.toString(i),Integer.toString(indices[i])+mascotas.get(indices[i]).getNombre());

            topFoto[i] = mascotas.get(indices[i]).getFoto();
            topNombres[i]= mascotas.get(indices[i]).getNombre();
            topRates[i]= mascotas.get(indices[i]).getRate();

        }
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
        // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mContacto:
                Intent intent=new Intent(this,contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcerca:
                Intent intent1=new Intent(this,acerca.class);
                startActivity(intent1);

                break;

        }

        return super.onOptionsItemSelected(item);
    }



}




























