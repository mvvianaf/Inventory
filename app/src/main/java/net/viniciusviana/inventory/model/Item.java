package net.viniciusviana.inventory.model;

/**
 * Created by Vinicius Viana on 08/06/2016.
 */
public class Item {

    private long id;
    private String barCode;
    private long quantity;

    //GET
    public long getId() {
        return id;
    }
    public String getBarCode() {
        return barCode;
    }
    public long getQuantity() {
        return quantity;
    }

    //SET
    public void setId(long id) {
        this.id = id;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}
