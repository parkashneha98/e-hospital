package com.example.firebase;

public class ProfileDataModel {
    public String name_x;
    public String email2;
    public String areaofinterest;
    public String education;
    public String experience;
    public String speciality;
    public String mobileno;
    public String workinghours;

    public String image = "";

    public ProfileDataModel(){

    }

    public ProfileDataModel( String name , String email2 , String areaofinterest , String education,String experience,String speciality,String mobileno,String workinghours )
    {

        this.name_x = name;

        this.email2 = email2;

        this.areaofinterest = areaofinterest;

        this.education = education;

        this.experience = experience;

        this.speciality = speciality;

        this.mobileno = mobileno;

        this.workinghours = workinghours;

    }



}
