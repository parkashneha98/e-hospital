package com.example.firebase;

public class AppointListDataModel {
    public String doctorname_x;
    public String contactno;
    public String appointmentdate;
    public String appointmenttime;
    public String patientname;
    public String fathername;


    public AppointListDataModel(){

    }


    public AppointListDataModel( String doctorname_x , String contactno , String appointmentdate, String appointmenttime,String patientname,String fathername  )
    {

        this.doctorname_x = doctorname_x;

        this.contactno= contactno;

        this.appointmentdate = appointmentdate;

        this.appointmenttime = appointmenttime;

        this.patientname = patientname;

        this.fathername = fathername;



    }

}

