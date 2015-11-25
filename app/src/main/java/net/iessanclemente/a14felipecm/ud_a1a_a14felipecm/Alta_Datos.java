package net.iessanclemente.a14felipecm.ud_a1a_a14felipecm;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Alta_Datos extends AppCompatActivity {

    EditText eName;
    EditText eDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__datos);

        eName = (EditText) findViewById(R.id.editName);
        eDesc = (EditText) findViewById(R.id.editDesc);

    }

    public void anhadirPersona(View v){

        if (!eName.getText().toString().equals("") && eName.getText().toString().length()<30){
            if(!eDesc.getText().toString().equals("") && eDesc.getText().toString().length()<200){
                OperacionesBD opbd = new OperacionesBD(this);
                opbd.sqlLiteDB = opbd.getWritableDatabase();
                Persoa person = new Persoa(eName.getText().toString(), eDesc.getText().toString());
                long valordevuelto = opbd.anhadirPersona(person);
                if(valordevuelto != -1){
                    Toast.makeText(Alta_Datos.this, "Persoa engadida: "+person.getNombre(), Toast.LENGTH_SHORT).show();
                    Log.i("BBDD", "Añadido:" + person.getNombre());
                    eName.setText("");
                    eDesc.setText("");
                    eName.requestFocus();
                }else{
                    Toast.makeText(Alta_Datos.this, person.getNombre()+" xa existe", Toast.LENGTH_SHORT).show();
                    Log.i("BBDD", "Error:Persoa existente");
                    eName.requestFocus();
                }
            }else {
                Toast.makeText(this, "Datos da descrición incorrectos", Toast.LENGTH_SHORT).show();
                eDesc.requestFocus();
                Log.i("BBDD", "Error:Falta descripcion");
            }
        }else{
            Toast.makeText(this,"Datos do nome incorrectos", Toast.LENGTH_SHORT).show();
            eName.requestFocus();
            Log.i("BBDD", "Error:Falta nombre");
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
