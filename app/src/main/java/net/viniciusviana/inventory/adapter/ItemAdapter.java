package net.viniciusviana.inventory.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.viniciusviana.inventory.R;
import net.viniciusviana.inventory.model.Count;
import net.viniciusviana.inventory.model.Item;

import java.util.List;

/**
 * Created by Vinicius Viana on 13/06/2016.
 */
public class ItemAdapter extends BaseAdapter{

    private Context context;
    private List<Item> itens;

    public ItemAdapter(Context context,List<Item> itens){
        super();
        this.context = context;
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return this.itens.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.adapter_item,parent,false);
        //INSERT THE VALUES IN ITEM ADAPTER
        Item i = this.itens.get(position);
        ((TextView)view.findViewById(R.id.lblBarCode)).setText(i.getBarCode());
        ((TextView)view.findViewById(R.id.lblQuantity)).setText(String.valueOf(i.getQuantity()));
        //CREATE EFFECT ZEBRA
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.llItemAdapter);
        if(position%2==0)
            ll.setBackgroundColor(Color.parseColor("#CCCCCC"));
        return view;
    }

}
