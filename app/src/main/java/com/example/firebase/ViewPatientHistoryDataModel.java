package com.example.firebase;

public class ViewPatientHistoryDataModel {
    public String patiencename_x;
    public String emailid;
    public String bloodpressureup;
    public String bloodpressurelow;
    public String bodytemperature;
    public String morningdos;
    public String afternoondos;
    public String eveningdos;
    public String morningmeal;
    public String afternoonmeal;
    public String eveningmeal;





    public ViewPatientHistoryDataModel()
    {

    }

    public ViewPatientHistoryDataModel( String patiencename_x , String emailid , String bloodpressureup, String bloodpressurelow,String bodytemperature,String morningdos,String afternoondos,String eveningdos,String morningmeal,String afternoonmeal,String eveningmeal )
    {

        this.patiencename_x = patiencename_x;

        this.emailid= emailid;

        this.bloodpressureup = bloodpressureup;

        this.bloodpressurelow = bloodpressurelow;

        this.bodytemperature=bodytemperature;

        this.morningdos = morningdos;
        this.afternoondos = afternoondos;
        this.eveningdos = eveningdos;
        this.morningmeal = morningmeal;
        this.afternoonmeal = afternoonmeal;
        this.eveningmeal = eveningmeal;




    }
}

