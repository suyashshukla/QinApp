package com.nikaas.suyashshukla.qin;

public class Login {

    String type;
    int resource;

    public Login(String type, int resource){

        this.type = type;
        this.resource = resource;

    }

    public int getResource() {
        return resource;
    }

    public String getType() {
        return type;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public void setType(String type) {
        this.type = type;
    }
}
