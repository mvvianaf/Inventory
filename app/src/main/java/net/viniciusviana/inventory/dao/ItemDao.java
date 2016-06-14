package net.viniciusviana.inventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import net.viniciusviana.inventory.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius Viana on 13/06/2016.
 */
public class ItemDao extends SQLiteOpenHelper{

    private Context context;

    private static final String TAG = "sql";
    public static final  String BD = "inventory.sqlite";
    private static final int VERSION = 1;

    public ItemDao(Context context) {
        super(context, BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Criando tabela itens..");

        Log.d(TAG,"Tabela itens com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean save(String idCount,List<Item> itens){
        SQLiteDatabase db = getWritableDatabase();
        try{
            for(Item i:itens){
                ContentValues values = new ContentValues();
                values.put("id_count",idCount);
                values.put("barcode",i.getBarCode());
                values.put("quantity",i.getQuantity());
                db.insert("itens","",values);
            }
            return true;
        }
        catch(Exception e){
            Toast.makeText(this.context,"ERRO AO ITEM DA CONTAGEM "+idCount+" !",Toast.LENGTH_SHORT).show();
            return false;
        }
        finally {
            db.close();
        }
    }

    public List<Item> getItens(String idCount){
        List<Item> itens = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cursor = db.query("itens", null, "id_count="+idCount, null, null, null, null);
            while (cursor.moveToNext()) {
                Item i = new Item();
                i.setId(cursor.getLong(cursor.getColumnIndex("id")));
                i.setBarCode(cursor.getString(cursor.getColumnIndex("barcode")));
                i.setQuantity(cursor.getLong(cursor.getColumnIndex("quantity")));
                itens.add(i);
            }
        }
        catch(Exception e){
            Toast.makeText(this.context,"ERRO AO LISTAR ITENS DA CONTAGEM "+idCount+" !"+e,Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
        return itens;
    }

    public void delete(String idCount){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.delete("itens","id_count="+idCount,null);
        }catch(Exception e){
            Toast.makeText(this.context,"ERRO AO EXCLUIR OS ITENS DA CONTAGEM "+idCount+" !",Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
    }
}
