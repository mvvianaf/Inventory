package net.viniciusviana.inventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import net.viniciusviana.inventory.model.Count;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vinicius Viana on 13/06/2016.
 */
public class CountDao extends SQLiteOpenHelper{

    private Context context;

    private static final String TAG = "sql";
    public static final  String BD = "inventory.sqlite";
    private static final int VERSION = 1;

    public CountDao(Context context) {
        super(context, BD, null, VERSION);
        this.context = context;
        //this.context.deleteDatabase(BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Criando tabelas..");
        db.execSQL("CREATE TABLE IF NOT EXISTS counts (id TEXT PRIMARY KEY,company TEXT NOT NULL, start LONG NOT NULL, end LONG NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS itens (id INTEGER PRIMARY KEY AUTOINCREMENT, id_count TEXT NOT NULL,barcode TEXT NOT NULL, quantity INTEGER NOT NULL);");
        Log.d(TAG,"Tabelas criadas com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(Count c){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //CREATE ID
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
            Calendar id = Calendar.getInstance();
            c.setId(sdf.format(id.getTime()));

            ContentValues values = new ContentValues();
            values.put("id",c.getId());
            values.put("company",c.getCompany());
            values.put("start",c.getDateTimeStart().getTimeInMillis());
            values.put("end",c.getDateTimeEnd().getTimeInMillis());
            db.insert("counts","",values);
            if(new ItemDao(this.context).save(c.getId(),c.getItens()))
                Toast.makeText(this.context,"CONTAGEM SALVA COM SUCESSO!",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this.context,"ERRO AO SALVAR CONTAGEM!",Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
    }

    public List<Count> getCounts(){
        List<Count> counts = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor cursor = db.query("counts",null,null,null,null,null,null);
            while(cursor.moveToNext()){
                Count c = new Count();
                c.setId(cursor.getString(cursor.getColumnIndex("id")));
                c.setCompany(cursor.getString(cursor.getColumnIndex("company")));
                Calendar dateTimeStart = Calendar.getInstance();
                dateTimeStart.setTimeInMillis(cursor.getLong(cursor.getColumnIndex("start")));
                c.setDateTimeStart(dateTimeStart);
                Calendar dateTimeEnd = Calendar.getInstance();
                dateTimeEnd.setTimeInMillis(cursor.getLong(cursor.getColumnIndex("end")));
                c.setDateTimeEnd(dateTimeEnd);
                c.setItens(new ItemDao(this.context).getItens(c.getId()));
                counts.add(c);
            }
        }
        catch(Exception e){
            Toast.makeText(this.context,"ERRO AO LISTAR CONTAGENS!",Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
        return counts;
    }

}
