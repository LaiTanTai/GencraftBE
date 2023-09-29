package com.vti.Gencraft.Entity;

import javax.persistence.*;

@Entity(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id ;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserEntity getUserEntity() {
        return userId;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userId = userEntity;
    }
}
