package com.vti.Gencraft.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private Set<ImageEntity> listImage;
    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public Set<ImageEntity> getListImage() {
        return listImage;
    }

    public void setListImage(Set<ImageEntity> listImage) {
        this.listImage = listImage;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }
}
