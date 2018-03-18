package com.mobile.Zailani_1202154223_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gogogogo(View view) {
        Intent i = new Intent(this,listvieww.class);
        startActivity(i);
    }

    public void gogogogogog(View view) {
        Intent i = new Intent(this,findPicture.class);
        startActivity(i);
    }
}
