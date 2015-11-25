package net.iessanclemente.a14felipecm.ud_a1a_a14felipecm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

public class Principal extends Activity {
    private OperacionesBD obd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Comprobamos si existe la base de datos y la creamos si procede
/*        String bddestino = "/data/data/" + getPackageName() + "/databases/"
                + "basedatos";
        File file = new File(bddestino);
        if (file.exists()) {
            Log.e("DDBB", "EXISTE");

        }else{

            //copiarBD(bddestino);
            Log.e("DDBB", "NO EXISTE");
        }*/

        obd = new OperacionesBD(this);
        obd.getWritableDatabase();

    }

    public void verAlta_Datos(View v){
        Intent i = new Intent(this,Alta_Datos.class);
        startActivity(i);
    }

    public void verLista(View v){
        Intent i = new Intent(this, Lista.class);
        startActivity(i);
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
