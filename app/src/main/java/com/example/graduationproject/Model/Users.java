package com.example.graduationproject.Model;

public class Users {

    private String id;
    private String name_surname;
    private String doctor_name;
    private String hospital_name;
    private String imageURL;
    private String search;

    public Users(){

    }

    public Users(String id, String name_surname, String doctor_name, String hospital_name, String imageURL, String search){
        this.id = id;
        this.name_surname = name_surname;
        this.doctor_name = doctor_name;
        this.hospital_name = hospital_name;
        this.imageURL = imageURL;
        this.search = search;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_Surname() {
        return name_surname;
    }

    public void setName_Surname(String name_surname) {
        this.name_surname = name_surname;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getHospital_Name() {
        return hospital_name;
    }

    public void setHospital_Name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDoctor_Name() {
        return doctor_name;
    }

    public void setDoctor_Name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

}
