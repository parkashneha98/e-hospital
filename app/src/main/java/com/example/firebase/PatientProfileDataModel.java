package com.example.firebase;

public class PatientProfileDataModel {
    public String name_x;
    public String emailpatient;
    public String father;
    public String mother;
    public String DOB;
    public String mobileno;
    public String address;
    public String bloodgroup;
    public String weight;
    public String height;
    public String gender;
    public String complaint;

    public PatientProfileDataModel( String name , String emailpatient , String fathername , String mothername,String DOB,String moblieno,String address,String bloodgroup,String weight,String height,String gender,String complaint )
    {

        this.name_x = name;

        this.emailpatient = emailpatient;

        this.father = fathername;

        this.mother = mothername;

        this.DOB =DOB;

        this.mobileno = moblieno;

        this.address= address;

        this.bloodgroup = bloodgroup;

        this.weight = weight;

        this.height = height;

        this.gender = gender;

        this.complaint = complaint;

    }



}




