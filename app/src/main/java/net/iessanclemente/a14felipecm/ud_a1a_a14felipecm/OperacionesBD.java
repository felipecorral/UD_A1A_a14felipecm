package net.iessanclemente.a14felipecm.ud_a1a_a14felipecm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by felipe on 23/11/15.
 */
public class OperacionesBD extends SQLiteOpenHelper {
    public SQLiteDatabase sqlLiteDB;
    //Datos BD
    public final static String NOME_BD="basedatos";
    public final static int VERSION_BD=101;

    //Sentencias
    private String CREAR_TABLAS ="CREATE TABLE PERSONA ( " +
            "nombre  VARCHAR(30) PRIMARY KEY ," +
            "descripcion VARCHAR( 200 )  NOT NULL)";
    private String DROP ="DROP TABLE IF EXIST PERSONA";


    //Anhadir persona
    public long anhadirPersona(Persoa p){
        ContentValues valores = new ContentValues();
        valores.put("nombre", p.getNombre());
        valores.put("descripcion",p.getDescripcion());
        long id = sqlLiteDB.insert("PERSONA",null,valores);
        return id;
    }

    //Listar Personas
    public ArrayList<Persoa> listarPersonas(){
        ArrayList<Persoa> arrPers = new ArrayList<Persoa>();
        Cursor c = sqlLiteDB.rawQuery("select * from PERSONA", null);
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                Persoa p = new Persoa(c.getString(0),c.getString(1));
                arrPers.add(p);
                c.moveToNext();
            }
        }
        return arrPers;
    }
    //Listar Personas
    public Cursor listarPersonasC(){
        Cursor c = sqlLiteDB.rawQuery("select * from PERSONA",null);
        return c;
    }


    public OperacionesBD(Context context) {
        super(context, NOME_BD, null, VERSION_BD);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
    }
}
