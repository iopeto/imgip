package org.iopeto.imgip.web.model;

public class Dump {
    private final String dump;
    private final String imgName;
    private final String stringDate;

    public Dump(String dump, String imgName, String stringDate) {
        this.dump = dump;
        this.imgName = imgName;
        this.stringDate = stringDate;
    }

    public String getDump() {
        return dump;
    }

    public String getImgName() {
        return imgName;
    }

    public String getStringDate() {
        return stringDate;
    }
}
