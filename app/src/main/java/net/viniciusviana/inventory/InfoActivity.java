package net.viniciusviana.inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import net.viniciusviana.inventory.model.Count;

public class InfoActivity extends AppCompatActivity {

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

        //TESTE

        this.c = (Count)getIntent().getSerializableExtra("count");

        this.lblCompany.setText(c.getCompany());
    }
}
