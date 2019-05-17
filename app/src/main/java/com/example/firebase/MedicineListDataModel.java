package com.example.firebase;

public class MedicineListDataModel {
    public String medicinename_x ;

    public String saltname;

    public String price ;

    public MedicineListDataModel()
    {

    }

    public MedicineListDataModel(String medicinename_x , String saltname,String price)
    {

        this.medicinename_x = medicinename_x;
        this.saltname = saltname;
        this.price = price;

    }
}
