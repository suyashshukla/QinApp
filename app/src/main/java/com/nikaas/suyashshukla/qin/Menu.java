package com.nikaas.suyashshukla.qin;

public class Menu {

    String name;
    int resource;

    Menu(String name, int resource){

        this.name = name;
        this.resource = resource;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public int getResource() {
        return resource;
    }

}
