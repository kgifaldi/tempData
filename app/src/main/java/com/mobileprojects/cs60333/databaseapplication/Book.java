package com.mobileprojects.cs60333.databaseapplication;

/**
 * Created by bchaudhr on 3/22/2017.
 */

public class Book {

    int _id;
    String bookname;

    public Book(int _id, String book_name) {
        this._id = _id;
        this.bookname = book_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
