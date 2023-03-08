package com.example.getcontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText t1;
    EditText t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);

       t1 = findViewById(R.id.name);
       t2 = findViewById(R.id.number);
    }


    public void getNameButton(View view){
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(t2.getText().toString()));
            Cursor cursor = getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);

            String stringContactName = "INVALID";
            if (cursor != null){
                if (cursor.moveToFirst()){
                    stringContactName = cursor.getString(0);
                }
            }
            t1.setText(stringContactName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getNumberButton(View view){
        try {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE},
                    "DISPLAY_NAME = '" + t1.getText().toString() + "'", null, null);

            cursor.moveToFirst();
            String a = cursor.getString(0);

            t2.setText(a);

        }
        catch (Exception e){
            e.printStackTrace();
            t2.setText("NA");
        }
    }
}

//    public void getName(View view)
//    {
//        try {
//            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(t2.getText().toString()));
//            Cursor cursor = getContentResolver().query(uri,new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},null,null,null);
//            String a = "inavlid";
//            if(cursor != null)
//            {
//                if(cursor.moveToFirst())
//                {
//                    a = cursor.getString(0);
//                }
//            }
//            t1.setText(a);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    public void getNumber(View view)
//    {
//        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
//    }
