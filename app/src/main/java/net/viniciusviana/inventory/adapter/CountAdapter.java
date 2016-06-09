package net.viniciusviana.inventory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.viniciusviana.inventory.R;
import net.viniciusviana.inventory.model.Count;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Vinicius Viana on 09/06/2016.
 */
public class CountAdapter extends BaseAdapter{

    private Context context;
    private List<Count> counts;

    public CountAdapter(Context context, List<Count> counts){
        super();
        this.context = context;
        this.counts = counts;
    }

    @Override
    public int getCount() {
        return counts.size();
    }

    @Override
    public Object getItem(int position) {
        return counts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.adapter_count,parent,false);
        //INSERT THE VALUES IN COUNT ADAPTER
        Count c = this.counts.get(position);
        ((TextView)view.findViewById(R.id.lblCompany)).setText(c.getCompany());
        ((TextView)view.findViewById(R.id.lblCount)).setText(String.valueOf(c.getTotCount()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
        ((TextView)view.findViewById(R.id.lblDate)).setText(sdf.format(c.getDateTimeStart().getTime()));
        return view;
    }

}
