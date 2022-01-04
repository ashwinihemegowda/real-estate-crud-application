package net.javaguides.springboot.property;

import javax.persistence.*;

@Entity
@Table(name="property")
public class Properties {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int property_id;

    @Column(nullable = false, length = 100, name = "property_name")
    private String propName;

    @Column(nullable = false, length = 150, name = "address")
    private String address;

    @Column(nullable = false, length = 100, name = "details")
    private String details;

    @Column(nullable = false, length = 50, name = "price")
    private double price;

/*public Properties() {
        super();
    }*/

    /*public Properties(String propName, String address, String details, double price) {
        this.propName = propName;
        this.address = address;
        this.details = details;
        this.price = price;
    }*/

    /*public Properties(int property_id, String propName, String address, String details, double price) {
        this.property_id = property_id;
        this.propName = propName;
        this.address = address;
        this.details = details;
        this.price = price;
    }*/

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int propId) {
        this.property_id = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
