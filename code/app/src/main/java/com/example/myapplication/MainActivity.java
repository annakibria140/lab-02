package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Declare the variables so that you will be able to reference them later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    ListView city_list;
    Button submitButton;
    EditText input_text;
    Button delete_button;
    int selected_position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        submitButton  = findViewById(R.id.submit_button);
        input_text  = findViewById(R.id.input_text);


        //adding a city when the submit button is clicked
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = input_text.getText().toString().trim();
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                input_text.setText("");
            }
        });


        city_list = findViewById(R.id.city_list);
        //handling click events in listView
        city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_position = position;
            }
        });

        //delete that item when the delete city button is clicked
        delete_button = findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected_position != -1){
                    dataList.remove(selected_position);
                    cityAdapter.notifyDataSetChanged();
                    selected_position = -1;
                }

            }
        });


    }
};