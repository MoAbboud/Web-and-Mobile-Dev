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

    int price = 0;
    String name, ingredients, strQuantity;
    String priceSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Add(View view) {
        TextView tvQuantity = findViewById(R.id.tbQuantity);
        quantity++;
        tvQuantity.setText(String.valueOf(quantity));
    }

    public void Subtract(View view) {
        TextView tvQuantity = findViewById(R.id.tbQuantity);
        if (quantity > 1) {
            quantity--;
            tvQuantity.setText(String.valueOf(quantity));
        }
    }

    public void Order(View view) {

        String summary = getSummaryText();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        intent.putExtra(Intent.EXTRA_SUBJECT, name + "'s Order.");
        intent.setType("text/plain");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Summary(View view) {

        String summary = getSummaryText();

        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra("orderSummary", summary);
        startActivity(intent);
    }

    public String getSummaryText() {

        EditText etName = findViewById(R.id.tbName);
        TextView etQuantity = findViewById(R.id.tbQuantity);
        CheckBox cbMushrooms = findViewById(R.id.cbMushroom);
        CheckBox cbPeppers = findViewById(R.id.cbPeppers);
        CheckBox cbCheese = findViewById(R.id.cbCheese);
        CheckBox cbPepperoni = findViewById(R.id.cbPepperoni);

        name = etName.getText().toString();
        price = 3;
        strQuantity = etQuantity.getText().toString();
        price += Integer.parseInt(strQuantity);

        priceSummary = "3$ per Pizza.";

        ingredients = "";
        if (cbMushrooms.isChecked()) {
            ingredients += cbMushrooms.getText().toString();
            ingredients += ", ";
            price += mushroomsPrice;
            priceSummary += mushroomsPrice + "$ for mushrooms and ";
        }
        if (cbPeppers.isChecked()) {
            ingredients += cbPeppers.getText().toString();
            ingredients += ", ";
            price += peppersPrice;
            priceSummary += peppersPrice + "$ for peppers and ";
        }
        if (cbCheese.isChecked()) {
            ingredients += cbCheese.getText().toString();
            ingredients += ", ";
            price += cheesePrice;
            priceSummary += cheesePrice + "$ for cheese and ";
        }
        if (cbPepperoni.isChecked()) {
            ingredients += cbPepperoni.getText().toString();
            ingredients += ", ";
            price += pepperoniPrice;
            priceSummary += pepperoniPrice + "$ for pepperoni and ";
        }
        ingredients = ingredients.trim();

        if (ingredients.charAt(ingredients.length() - 1) == ',') { //Remove last comma.
            ingredients = ingredients.substring(0, ingredients.length() - 1);
        }

        if (priceSummary.substring(priceSummary.length() - 5).equals(" and ")) { //Remove last AND
            priceSummary = priceSummary.substring(0, priceSummary.length() - 5);
            priceSummary += ".";
        }

        String summary = "Dear " + name + ",\n\n"
                + "The following pizza will have these ingredients: " + ingredients + ".\n\n"
                + "The amount of pizzas order is: " + quantity + ".\n\n"
                + "Price: " + price + "." + "\n\n" + priceSummary;

        return summary;
    }
}