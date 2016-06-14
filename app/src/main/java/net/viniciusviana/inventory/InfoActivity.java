package net.viniciusviana.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import net.viniciusviana.inventory.adapter.ItemAdapter;
import net.viniciusviana.inventory.controller.CountController;
import net.viniciusviana.inventory.dao.CountDao;
import net.viniciusviana.inventory.model.Count;

import java.text.SimpleDateFormat;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView lblCompany;
    private TextView lblTotFound;
    private TextView lblDataStart;
    private TextView lblDataEnd;
    private Button btnDelete;
    private Button btnSend;
    private ListView lvItens;

    private Count c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //JOIN
        this.lblCompany = (TextView)findViewById(R.id.lblCompany);
        this.lblTotFound = (TextView)findViewById(R.id.lblTotFound);
        this.lblDataStart = (TextView)findViewById(R.id.lblStart);
        this.lblDataEnd = (TextView)findViewById(R.id.lblEnd);
        this.btnDelete = (Button)findViewById(R.id.btnDelete);
        this.btnSend = (Button)findViewById(R.id.btnSend);
        this.lvItens = (ListView)findViewById(R.id.lvItens);

        //ACTION
        this.btnDelete.setOnClickListener(this);
        this.btnSend.setOnClickListener(this);

        //SET VIEW
        this.c = CountController.c;
        this.lblCompany.setText(c.getCompany());
        this.lblTotFound.setText(String.valueOf(c.getTotCount()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        this.lblDataStart.setText(sdf.format(c.getDateTimeStart().getTime()));
        this.lblDataEnd.setText(sdf.format(c.getDateTimeEnd().getTime()));
        this.lvItens.setAdapter(new ItemAdapter(this, this.c.getItens()));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnDelete:
                delete();
                break;
            case R.id.btnSend:
                send();
                break;
        }
    }

    private void delete(){
        new CountDao(this).delete(this.c.getId());
        finish();
    }

    private void send(){
        Intent itEmail = new Intent(Intent.ACTION_SEND);
        itEmail.setType("plain/text");
        itEmail.putExtra(Intent.EXTRA_SUBJECT, "CONTAGEM "+this.c.getId()+" - "+this.c.getCompany().toUpperCase());
        itEmail.putExtra(Intent.EXTRA_TEXT, this.c.getEmail());
        itEmail.putExtra(Intent.EXTRA_EMAIL, "mvvianaf@gmail.com");
        startActivity(Intent.createChooser(itEmail,"Escolha a App..."));
    }
}
