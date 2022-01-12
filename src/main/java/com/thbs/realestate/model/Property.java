package com.thbs.realestate.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int propertyId;

    @Column(nullable = false, length = 100, name = "category")
    private String category;

    @Column(nullable = false, length = 100, name = "propertyName")
    private String propertyName;

    @Column(nullable = false, length = 150, name = "city")
    private String description;

    @Column(nullable = false, length = 50, name = "price")
    private String price;
    @Lob
    @Column(columnDefinition = "LONGBLOB")

    private String image;

    private String address;

    private String facilities;

    private String ownerName;

    private long contactNo;

    private String email;




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

}