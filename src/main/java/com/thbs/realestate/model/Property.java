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

    private String category;

    private String propertyName;

    private String description;

    private String price;

    @Lob
    @Column(columnDefinition = "LONGBLOB")

    private String image;

    private String address;

    private String facilities;

    private String ownerName;

    private long contactNo;

    private String email;

    public Property(int propertyId, String category, String propertyName, String description, String price, String address, String facilities, String ownerName, long contactNo, String email) {
        this.propertyId = propertyId;
        this.category = category;
        this.propertyName = propertyName;
        this.description = description;
        this.price = price;
        this.address = address;
        this.facilities = facilities;
        this.ownerName = ownerName;
        this.contactNo = contactNo;
        this.email = email;
    }

    public Property(String category, String propertyName, String description, String price, String address, String facilities, String ownerName, long contactNo) {
        this.category = category;
        this.propertyName = propertyName;
        this.description = description;
        this.price = price;
        this.address = address;
        this.facilities = facilities;
        this.ownerName = ownerName;
        this.contactNo = contactNo;
    }
}