package net.viniciusviana.inventory.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Vinicius Viana on 08/06/2016.
 */
public class Count {

    private long id;
    private String company;
    private Calendar dateTimeStart;
    private Calendar dateTimeEnd;
    private List<Block> blocks;

    //GET
    public long getId() {
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
    public List<Block> getBlocks() {
        return blocks;
    }

    //SET
    public void setId(long id) {
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
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

}
