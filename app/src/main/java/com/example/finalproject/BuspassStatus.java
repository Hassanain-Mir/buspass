package com.example.finalproject;

class BuspassStatus {
//    @SerializedName("product_name")

    private String pass_id;
    private String user_mno;
    private String from_date;
    private String expired_date;







    public BuspassStatus(String pass_id, String user_mno, String from_date, String expired_date) {

        this.pass_id = pass_id;
        this.user_mno = user_mno;
        this.from_date = from_date;
        this.expired_date = expired_date;




    }
    public String getpass_id() {
        return pass_id;
    }
    public String getuser_mno() {
        return user_mno;
    }
    public String getfrom_date() {
        return from_date;
    }
    public String getexpired_date() {
        return expired_date;
    }


}
