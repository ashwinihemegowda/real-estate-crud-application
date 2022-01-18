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

}