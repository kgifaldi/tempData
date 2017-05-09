package com.mobileprojects.cs60333.databaseapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    int num = 0;
  //  TextView booktext;
    ListView books_list;
    EditText book_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        book_name =(EditText) findViewById(R.id.edit_bookname);
        books_list = (ListView) findViewById(R.id.books_list);

        book_name.setText("");
        populateListView();

        //Open the image gallery of a book
        books_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

    private void populateListView() {
        //Get names of all the fields of the table Books
        String[] fields = dbHelper.getTableFields(DBHelper.TABLE_BOOK);

        //Get all the book entries from the table Books
        Cursor cursor = dbHelper.getAllEntries(DBHelper.TABLE_BOOK, fields);

        //Get ids of all the widgets in the custom layout for the listview
        int[] item_ids = new int[] {R.id.book_id, R.id.book_name};

        //Create the cursor that is going to feed information to the listview
        SimpleCursorAdapter bookCursor;

        //The adapter for the listview gets information and attaches it to appropriate elements
        bookCursor = new SimpleCursorAdapter(this, R.layout.item_layout, cursor, fields, item_ids, 0);
        books_list.setAdapter(bookCursor);
    }


    public void fn_insert(View view) {
        String bookname = String.valueOf(book_name.getText());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_NAME, bookname);
        dbHelper.insertData(DBHelper.TABLE_BOOK, contentValues);
        book_name.setText("");
        populateListView();

    }

    public void fn_delete(View view) {
        String bookid = String.valueOf(book_name.getText());
        dbHelper.deleteData(DBHelper.TABLE_BOOK, " _id = ?", Integer.parseInt(bookid));
        book_name.setText("");
        populateListView();
    }


}
