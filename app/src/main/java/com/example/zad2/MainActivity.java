package com.example.zad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView list;
    Button add;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListAdapter adapter = new MyListAdapter(this, BazaObrazkow.tittle, BazaObrazkow.subtittle, BazaObrazkow.uri_photo);
        list = (ListView) findViewById(R.id.lista);
        add = (Button) findViewById(R.id.add_photo);

        list.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this, LagrePhotoActivity.class);
                intent.putExtra("idPhoto", position);
                startActivity(intent);
            }
        });
    }
}