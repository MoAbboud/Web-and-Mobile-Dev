package com.example.mypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent i = getIntent();
        String summary = i.getExtras().getString("orderSummary");
        TextView tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText(summary);
    }

    public void GoBack(View view) {
            finish();
    }
}