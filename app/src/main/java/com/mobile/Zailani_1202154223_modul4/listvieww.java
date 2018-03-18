package com.mobile.Zailani_1202154223_modul4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listvieww extends AppCompatActivity {
    private ListView listView;
    private static int rotate =0;
    private ProgressDialog progressDialog;
    private String[] namaMahasiswa = {"zai","zen","za'i","zailan","","zail","lani","sina"
    ,"bariba" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //deklarasi komponen yang di gunakan
        setContentView(R.layout.activity_list);
        listView =(ListView)findViewById(R.id.listviewxml);
        Button buton = (Button)findViewById(R.id.button);
        //set adapter
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));
       //aksi saat tombol di click
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Taskgo().execute();
            }
        });
        //pengecakan apakah savedInstanceState sudah berisi sesuatu
        if(savedInstanceState != null){
            if(savedInstanceState.getInt("LOAD")==1){
                new Taskgo().execute();
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
    class Taskgo extends AsyncTask<Void,String,String>{
        ArrayAdapter<String> adapter;
        int i;
        @Override
        //method sebelum aksi di lakukan
        protected void onPreExecute() {
            //adapter untuk mengisi listview
            adapter = (ArrayAdapter<String>) listView.getAdapter();
            //inisialisasi progress dialog
            progressDialog = new ProgressDialog(listvieww.this);
            progressDialog.setTitle("Loading Data");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(0);
            progressDialog.show();
            i=0;
        }
        //method saat prosess berjalan
        @Override
        protected String doInBackground(Void... voids) {
            //memnaggil array
            for(String Name : namaMahasiswa){
                publishProgress(Name);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Sucsess";
        }
        //method yang melakukan update saat publishProgress di kirim dari doInBackground
        @Override
        protected void onProgressUpdate(String... values) {
            //mengisi array pada listview
            adapter.add(values[0]);
            //perhitungan persentase
            i++;
            int proses = i *100/namaMahasiswa.length;
            progressDialog.setProgress(proses);
            progressDialog.setMessage(proses+"%");
        }
        //method saat proses sudah selesai beroprasi
        @Override
        protected void onPostExecute(String result) {
            //menghilangkan progressdialog
            progressDialog.hide();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            //menambahkan nilai pada rotate
            rotate =1;

        }
    }

}
