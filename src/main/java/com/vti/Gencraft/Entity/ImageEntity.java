package com.vti.Gencraft.Entity;

import javax.persistence.*;

@Entity(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "url")
    private String url;

}
