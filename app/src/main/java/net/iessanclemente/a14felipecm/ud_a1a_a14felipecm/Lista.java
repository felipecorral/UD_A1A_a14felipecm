package net.iessanclemente.a14felipecm.ud_a1a_a14felipecm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Lista extends Activity {
    ListView lv;
    TextView tvPerson;
    ArrayList<Persoa> personas = new ArrayList<Persoa>();
    Persoa ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lv = (ListView) findViewById(R.id.lst_view_datos);
        tvPerson = (TextView) findViewById(R.id.txt_persona_selecc);

        OperacionesBD opsbd = new OperacionesBD(this);
        opsbd.sqlLiteDB = opsbd.getWritableDatabase();

        personas = opsbd.listarPersonas();
        ArrayList<String> nPersonas = new ArrayList<String>();
        for (int i=0;i<personas.size();i++){
            nPersonas.add(personas.get(i).getNombre());
        }

        ArrayAdapter<String> adatador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nPersonas);
        lv.setAdapter(adatador);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvPerson.setText(personas.get(position).getNombre()+"\n"+personas.get(position).getDescripcion());
                ps = personas.get(position);
            }
        });


        //PUEDE HACERSE CON EL CURSOR DIRECTAMENTE, PERO NECESITAMOS QUE LAS FILAS TRENGAN EL CAMPO _id
        //Cursor cur = opsbd.listarPersonasC();
        //SimpleCursorAdapter cAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cur, new String[]{"nombre"},new int[] {android.R.id.text1});
        //lv.setAdapter(cAdapter);

    }//Fin Oncreate

    public void guardarArchivo(View v){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String route = sp.getString("opcion1","/DATOS/");
        if(ps !=null){
            guardar(ps,route,v);
        }else{
            Snackbar.make(v,"Non seleccionaches ningunha persona",Snackbar.LENGTH_SHORT).show();
        }

    }//Fin guardarArchivo


    public void guardar(Persoa p, String r,View v){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File rarchivo = new File(Environment.getExternalStorageDirectory(), r);
            if (!rarchivo.exists()) rarchivo.mkdirs();
            File archivo = new File(rarchivo, p.getNombre() + ".txt");
            try {
                FileOutputStream fos = new FileOutputStream(archivo);
                OutputStreamWriter osr = new OutputStreamWriter(fos);
                osr.write("Nome: " + p.getNombre() + "\n " + "Descrición: " + p.getDescripcion() + "\n");
                osr.close();
                Snackbar.make(v, "Archivo Guardado: "+r + p.getNombre()+".txt", Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(this,"Gardado Correctamente",Toast.LENGTH_SHORT).show();
                Log.i("IO-STATUS", "Archivo guardado:"+r+"/"+p.getNombre());
            } catch (Exception ex) {
                Log.e("IO-ERROR", "Guardando el archivo");
            }
        }else{
            Snackbar.make(v,"Non hay tarxeta SD",Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent (this, Preferencias.class);
            startActivity(i);
            //Toast.makeText(Principal.this, "Hola", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
