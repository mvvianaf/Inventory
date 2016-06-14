package net.viniciusviana.inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import net.viniciusviana.inventory.adapter.ItemAdapter;
import net.viniciusviana.inventory.dao.CountDao;
import net.viniciusviana.inventory.model.Count;
import net.viniciusviana.inventory.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class YoungActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtCompany;
    private EditText txtBarCode;
    private Button btnStart;
    private Button btnInsert;
    private Button btnEnd;
    private ListView lvItens;

    private Count c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_young);

        //START COUNT AND LIST OF ITENS
        this.c = new Count();

        //JOIN
        this.txtCompany = (EditText)findViewById(R.id.txtCompany);
        this.txtBarCode = (EditText)findViewById(R.id.txtBarCode);
        this.lvItens = (ListView)findViewById(R.id.lvItens);
        this.btnStart = (Button)findViewById(R.id.btnStart);
        this.btnInsert = (Button)findViewById(R.id.btnInsert);
        this.btnEnd = (Button)findViewById(R.id.btnEnd);

        //SET ONCLICK
        this.btnStart.setOnClickListener(this);
        this.btnInsert.setOnClickListener(this);
        this.btnEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStart:
                start();
                break;
            case R.id.btnInsert:
                insertCodeBar();
                break;
            case R.id.btnEnd:
                save();
                break;
        }
    }

    private void start(){
        //GET THE NAME OF COMPANY AND DATE TIME THE BEGIN
        this.c.setCompany(this.txtCompany.getText().toString());
        this.c.setDateTimeStart(Calendar.getInstance());
        //DISABLE INFORMATION ABOUT COMPANY
        this.txtCompany.setEnabled(false);
        this.btnStart.setEnabled(false);
        //ENABLE INOUT CODE BARS
        this.btnInsert.setEnabled(true);
        this.txtBarCode.setEnabled(true);
        //SET FOCUS
        this.txtBarCode.requestFocus();
    }

    private void insertCodeBar(){
        //CHECK IF THE INPUT THERE ISN'T EMPTY
        if(!this.txtBarCode.getText().toString().equals("")) {
            Item i = new Item();
            i.setBarCode(this.txtBarCode.getText().toString());
            i.setQuantity(1);
            groupCodeBar(i);
            this.lvItens.setAdapter(new ItemAdapter(this, this.c.getItens()));
            //MENSAGE OF THE SUCESS
            Toast.makeText(this,"CODIGO DE BARRA ADICIONADO COM SUCESSO!",Toast.LENGTH_SHORT).show();
            //CLEAN INPUT
            this.txtBarCode.setText("");
            //ENABLE THE END BUTTON
            if(this.c.getItens().size()==1)
                this.btnEnd.setEnabled(true);
        }
        else{
            Toast.makeText(this,"CODIGO DE BARRA INVALIDO!",Toast.LENGTH_SHORT).show();
        }
    }

    private void groupCodeBar(Item i){
        boolean exist = false;
        for(Item item:this.c.getItens())
            if(item.getBarCode().equals(i.getBarCode())) {
                item.setQuantity(item.getQuantity() + 1);
                exist = true;
                break;
            }
        if(!exist)
            this.c.setItem(i);
    }

    private void save(){
        this.c.setDateTimeEnd(Calendar.getInstance());
        new CountDao(this).save(this.c);

        //CLEANING UP INPUTS FOR A NEW COUNT
        this.txtBarCode.setText("");
        this.txtBarCode.setEnabled(false);
        this.txtCompany.setText("");
        this.txtCompany.setEnabled(true);
        this.txtCompany.requestFocus();
        this.btnStart.setEnabled(true);
        this.btnInsert.setEnabled(false);
        this.btnEnd.setEnabled(false);
        this.c = new Count();
        this.lvItens.setAdapter(new ItemAdapter(this, this.c.getItens()));
    }
}
