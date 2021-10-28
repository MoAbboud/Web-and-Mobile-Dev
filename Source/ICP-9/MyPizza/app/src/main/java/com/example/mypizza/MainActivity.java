package com.example.mypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int pepperoniPrice = 3;
    int cheesePrice = 2;
    int peppersPrice = 1;
    int mushroomsPrice = 1;
    int quantity = 1;

    String name, ingredients, strQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Add(View view){
        TextView tvQuantity = findViewById(R.id.tbQuantity);
        quantity++;
        tvQuantity.setText(String.valueOf(quantity));
    }

    public void Subtract(View view){
        TextView tvQuantity = findViewById(R.id.tbQuantity);
        if(quantity > 1){
        quantity--;
        tvQuantity.setText(String.valueOf(quantity));
        }
    }

    public void Order(View view){

        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        intent.putExtra("orderName", name);
        intent.putExtra("orderIngredients", ingredients);
        intent.putExtra("orderQuantity", strQuantity);
        startActivity(intent);
    }

    public void Summary(View view){

        EditText etName = findViewById(R.id.tbName);
        TextView etQuantity = findViewById(R.id.tbQuantity);
        CheckBox cbMushrooms = findViewById(R.id.cbMushroom);
        CheckBox cbPeppers = findViewById(R.id.cbPeppers);
        CheckBox cbCheese = findViewById(R.id.cbCheese);
        CheckBox cbPepperoni = findViewById(R.id.cbPepperoni);

        name = etName.getText().toString();
        strQuantity = etQuantity.getText().toString();

        ingredients = "";
        if(cbMushrooms.isChecked()){
            ingredients += cbMushrooms.getText().toString();
            ingredients += ", ";
        }
        if(cbPeppers.isChecked()){
            ingredients += cbPeppers.getText().toString();
            ingredients += ", ";
        }
        if(cbCheese.isChecked()){
            ingredients += cbCheese.getText().toString();
            ingredients += ", ";
        }
        if(cbPepperoni.isChecked()){
            ingredients += cbPepperoni.getText().toString();
            ingredients += ", ";
        }
        ingredients = ingredients.trim();

        if(ingredients.charAt(ingredients.length()-1) == ','){
            ingredients = ingredients.substring(0, ingredients.length()-1);
        }

        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra("orderName", name);
        intent.putExtra("orderIngredients", ingredients);
        intent.putExtra("orderQuantity", strQuantity);
        startActivity(intent);
    }
}