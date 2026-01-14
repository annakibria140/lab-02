package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    int selectedPosition = -1;
    Button deleteButton = findViewById(R.id.delete_button);
    Button addButton = findViewById(R.id.add_button);


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

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(MainActivity.this);
                //input.setHint("Enter City Name");
                new AlertDialog.Builder(MainActivity.this).setView(input)
                        .setPositiveButton("CONFIRM", (dialog, which) -> {
                            String cityName = input.getText().toString().trim();
                            if (!cityName.isEmpty()) {
                                dataList.add(cityName);
                                cityAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("CANCEL", null)
                        .show();


        //only allow the user to choose one city
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //when the user clicks a city, find the position of the city in the dataList
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

                //when the delete button is clicked, delete the one we chose from the dataList
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != 1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition = -1;
                }
            }
        });


    }
};