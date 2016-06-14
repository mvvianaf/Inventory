package net.viniciusviana.inventory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vinicius Viana on 08/06/2016.
 */
public class Count implements Serializable{

    private String id;
    private String company;
    private Calendar dateTimeStart;
    private Calendar dateTimeEnd;
    private List<Item> itens;

    public Count(){
        this.itens = new ArrayList<>();
    }

    //GET
    public String getId() {
        return id;
    }
    public String getCompany() {
        return company;
    }
    public Calendar getDateTimeStart() {
        return dateTimeStart;
    }
    public Calendar getDateTimeEnd() {
        return dateTimeEnd;
    }
    public List<Item> getItens() {
        return itens;
    }

    //SET
    public void setId(String id) {
        this.id = id;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setDateTimeStart(Calendar dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }
    public void setDateTimeEnd(Calendar dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }
    public void setItens(List<Item> itens) {
        this.itens= itens;
    }

    //METHODS
    public long getTotCount(){
        long temp=0;
        for(Item i:this.itens)
            temp+=i.getQuantity();
        return temp;
    }

    public void setItem(Item i){
        this.itens.add(i);
    }

}
