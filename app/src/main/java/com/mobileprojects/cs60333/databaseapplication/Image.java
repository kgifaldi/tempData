package com.mobileprojects.cs60333.databaseapplication;

/**
 * Created by bchaudhr on 3/22/2017.
 */

public class Image {

    private int _id;
    private int book_id;
    private String uri;
    private byte[] image;

    public Image(int _id, int book_id,  String uri, byte[] image) {
        this._id = _id;
        this.book_id = book_id;
        this.uri = uri;
        this.image = image;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_book_id() {
        return book_id;
    }

    public void set_book_id(int _id) {
        this.book_id = book_id;
    }

    public String get_uri() {
        return uri;
    }

    public void set_uri(String uri) {
        this.uri = uri;
    }

    public byte[] get_image() {
        return image;
    }

    public void set_image(byte[] image) {
        this.image = image;
    }
}
