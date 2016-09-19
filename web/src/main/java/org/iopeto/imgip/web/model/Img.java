package org.iopeto.imgip.web.model;

public class Img {
    private String name;
    private String mimeType;
    private byte[] bytes;

    public Img(String name, String mimeType, byte[] bytes) {
        this.name = name;
        this.mimeType = mimeType;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public byte[] getBytes() {
        return bytes;
    }

}
