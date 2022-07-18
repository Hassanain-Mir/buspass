package com.example.finalproject;

class Buspass {
//    @SerializedName("product_name")

    private String pass_id;
    private String pass_name;
    private String pass_term;
    private String pass_occupation;


    private String pass_amount;
    private String pass_details;
    private String pass_from_place;

    private String pass_to_place;
    private String document_submission;






    public Buspass(String pass_id, String pass_name, String pass_term, String pass_occupation, String pass_amount, String pass_details, String pass_from_place, String pass_to_place, String document_submission) {

        this.pass_id = pass_id;
        this.pass_name = pass_name;
        this.pass_term = pass_term;
        this.pass_occupation = pass_occupation;
        this.pass_amount = pass_amount;
        this.pass_details = pass_details;
        this.pass_from_place = pass_from_place;


        this.pass_to_place = pass_to_place;
        this.document_submission = document_submission;




    }
    public String getpass_id() {
        return pass_id;
    }
    public String getpass_name() {
        return pass_name;
    }
    public String getpass_term() {
        return pass_term;
    }
    public String getpass_occupation() {
        return pass_occupation;
    }
    public String getpass_amount() {
        return pass_amount;
    }
   public String getpass_details() {
        return pass_details;
    }
 public String getpass_from_place() {
        return pass_from_place;
    }
    public String getpass_to_place() {
        return pass_to_place;
    }
    public String getdocument_submission() {
        return document_submission	;
    }


}
