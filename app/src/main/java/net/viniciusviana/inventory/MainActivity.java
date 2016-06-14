package net.viniciusviana.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.viniciusviana.inventory.adapter.CountAdapter;
import net.viniciusviana.inventory.dao.CountDao;
import net.viniciusviana.inventory.model.Count;
import net.viniciusviana.inventory.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener,AdapterView.OnItemClickListener{

    private List<Count> counts;
    private ListView lvCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //JOIN
        this.lvCounts = (ListView)findViewById(R.id.lvCounts);

        //ACTION
        this.lvCounts.setOnItemClickListener(this);

        //LIST COUNTS
        this.counts = new CountDao(this).getCounts();
        this.lvCounts.setAdapter(new CountAdapter(this,this.counts));
    }


    //OVERRIDE METHOD TO GENERATE THE UPPER MENU ICONS
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        List<MenuItem> menus = new ArrayList<>();

        MenuItem young = menu.add(0, 0, 0, "");
        young.setIcon(R.drawable.young);
        menus.add(young);

        /*MenuItem save = menu.add(0,1,1,"");
        save.setIcon(R.drawable.save);
        menus.add(save);*/

        MenuItem find = menu.add(0,2,2,"");
        find.setIcon(R.drawable.find);
        menus.add(find);

        /*MenuItem delete = menu.add(0,3,3,"");
        delete.setIcon(R.drawable.delete);
        menus.add(delete);*/

        for(MenuItem mI: menus) {
            mI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            mI.setOnMenuItemClickListener(this);
        }

        return (true);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent();
        switch(item.getItemId()){
            case 0:
                intent = new Intent(this,YoungActivity.class);
                break;
        }
        startActivity(intent);
        return false;
    }

    @Override
    protected void onStart(){
        super.onStart();
        //LIST COUNTS
        this.counts = new CountDao(this).getCounts();
        this.lvCounts.setAdapter(new CountAdapter(this,this.counts));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra("count",this.counts.get(position));
        startActivity(intent);
    }
}
