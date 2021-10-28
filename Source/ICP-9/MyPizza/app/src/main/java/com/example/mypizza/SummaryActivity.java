package com.example.mypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent i = getIntent();
        String name = i.getExtras().getString("orderName");
        String ingredients = i.getExtras().getString("orderIngredients");
        String quantity = i.getExtras().getString("orderQuantity");

        TextView tvSummary = findViewById(R.id.tvSummary);

        tvSummary.setText("Dear " + name + ",\n\n"
                + "The following pizza will have these ingredients: " + ingredients + ".\n\n"
                + "The amount of pizzas order is: " + quantity);
    }
}