package com.example.firebase;

public class AddInpatientDataModel {

        public String patincename;
        public String emailid;
        public String ward;
        public String disease;
        public String treatment;

        public AddInpatientDataModel()
        {

        }

        public AddInpatientDataModel(String patincename,String emailid,String ward,String disease,String treatment)
        {
            this.patincename = patincename;
            this.emailid=emailid;
            this.ward=ward;
            this.disease=disease;
            this.treatment=treatment;

        }


    }


