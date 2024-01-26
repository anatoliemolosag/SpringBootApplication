package com.anatolieCode;



public class CustomExceptions {


    private int statusCode;
    private String detail;
    private String message;



    public CustomExceptions(int statusCode, String detail, String message){

        this.statusCode = statusCode;
        this.detail = detail;
        this.message = message;

    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getDetail(){
        return detail;
    }


    public String getMessage() {
        return message;
    }
}
