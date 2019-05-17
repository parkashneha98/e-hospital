package com.example.firebase;

public class NurseProfileDataModel {
    public String name_x;
    public String emailnurse;
    public String areaofinterest;
    public String education;
    public String experience;
    public String speciality;
    public String mobileno;
    public String workinghours;

    public NurseProfileDataModel(){

    }


    public NurseProfileDataModel( String name , String emailnurse , String areaofinterest , String education,String experience,String speciality,String mobileno,String workinghours )
    {

        this.name_x = name;

        this.emailnurse = emailnurse;

        this.areaofinterest = areaofinterest;

        this.education = education;

        this.experience = experience;

        this.speciality = speciality;

        this.mobileno = mobileno;

        this.workinghours = workinghours;

    }



}


