package net.viniciusviana.inventory.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Vinicius Viana on 08/06/2016.
 */
public class Block {

    private long id;
    private Calendar dateTimeStart;
    private Calendar dateTimeEnd;
    private String location;
    private String comment;
    private List<Item> itens;

    //GET
    public long getId() {
        return id;
    }
    public Calendar getDateTimeStar() {
        return dateTimeStart;
    }
    public Calendar getDateTimeEnd() {
        return dateTimeEnd;
    }
    public String getLocation() {
        return location;
    }
    public String getComment() {
        return comment;
    }
    public List<Item> getItens(){ return itens; }

    //SET
    public void setId(long id) {
        this.id = id;
    }
    public void setDateTimeStar(Calendar dateTimeStar) {
        this.dateTimeStart = dateTimeStar;
    }
    public void setDateTimeEnd(Calendar dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setItens(List<Item> itens) { this.itens = itens; }

}
