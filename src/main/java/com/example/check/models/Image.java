package com.example.check.models;

import javax.persistence.*;

@Entity(name = "images")
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "data")
    private byte[] data;


    public Image(byte[] data, String imageName) {
        this.data = data;
        this.imageName = imageName;
    }

    public Image() {

    }

    public String getId() {
        return id;
    }


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
