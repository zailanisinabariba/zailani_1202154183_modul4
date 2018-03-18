package com.mobile.Zailani_1202154223_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class findPicture extends AppCompatActivity {
    private EditText mtext;
    private Button mbutton;
    private ImageView mimage;
    private ProgressDialog progressDialog;
    private static int rotate = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inisialisasi
        setContentView(R.layout.activity_findpicture);
        mtext =(EditText)findViewById(R.id.editText);
        mbutton =(Button)findViewById(R.id.button3);
        mimage = (ImageView)findViewById(R.id.imageView);
        mtext.setText("https://img-k.okeinfo.net/content/2018/03/15/598/1873326/bocoran-episode-130-dragon-ball-super-jiren-kalahkan-goku-JxLv3h95ym.jpg");
        //aksi saat button di click
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cari().execute(mtext.getText().toString());
            }
        });
        //pengecakan apakah savedInstanceState sudah berisi sesuatu
        if(savedInstanceState != null){
            if(savedInstanceState.getInt("LOAD")==1){
                new cari().execute(mtext.getText().toString());
                Log.d("ASu","Rotate Susccess"+savedInstanceState.getInt("LOAD"));
            }
        }


    }
    //method yang menyimpan sesuatu pada package
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("LOAD",rotate);
    }
    //class asynctask
    private class cari extends AsyncTask<String,Bitmap,Bitmap>{
        //method sebelum aksi di lakukan
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //inisialisasi progress dialog
            progressDialog = new ProgressDialog(findPicture.this);
            progressDialog = new ProgressDialog(findPicture.this);
            progressDialog.setTitle("Loading Data");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(0);
            progressDialog.setMessage("Wait A Minutes");
            progressDialog.show();

        }
        //method saat prosess berjalan
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try{
                //melakuakn openstream ke link
                InputStream input = new java.net.URL(imageURL).openStream();
                //decode stream data yang di ambil menjadi bitmap
                bitmap = BitmapFactory.decodeStream(input);
                publishProgress(BitmapFactory.decodeStream(input));
            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //set imange yang sudah di download
            mimage.setImageBitmap(result);
            progressDialog.dismiss();
            rotate=1;
        }
    }
}
