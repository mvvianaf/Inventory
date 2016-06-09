package net.viniciusviana.inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import net.viniciusviana.inventory.adapter.CountAdapter;
import net.viniciusviana.inventory.model.Block;
import net.viniciusviana.inventory.model.Count;
import net.viniciusviana.inventory.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gvCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //JOIN
        this.gvCounts = (GridView)findViewById(R.id.gvCounts);

        //FOR TEST
        List<Count> counts = new ArrayList<>();
        for(int i=0;i<10;i++){
            Count c = new Count();
            c.setId(i);
            c.setCompany("Company"+i);
            c.setDateTimeStart(Calendar.getInstance());
            c.setDateTimeEnd(Calendar.getInstance());
            List<Block> blocks = new ArrayList<>();
            for(int j=0;j<10;j++){
                Block b = new Block();
                b.setId(j);
                b.setLocation("Location"+j);
                b.setComment("Comment"+j);
                b.setDateTimeStar(Calendar.getInstance());
                b.setDateTimeEnd(Calendar.getInstance());
                List<Item> itens = new ArrayList<>();
                for(int y=0;y<10;y++){
                    Item it = new Item();
                    it.setId(y);
                    it.setBarCode(String.valueOf((i*j*y)));
                    it.setQuantity(i*j*y);
                    itens.add(it);
                }
                b.setItens(itens);
                blocks.add(b);
            }
            c.setBlocks(blocks);
        }


        this.gvCounts.setAdapter(new CountAdapter(this,counts));
    }
}
