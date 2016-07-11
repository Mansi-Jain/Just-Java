package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
        }
        else{
            display(0);
        }
    }

    /**

     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = quantity*5;
        EditText text = (EditText)findViewById(R.id.name_field);
        String value = text.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        if(hasWhippedCream==true && hasChocolate==false)
        {
            price=quantity*(5+1);
        }
        if(hasWhippedCream==false && hasChocolate==true)
        {
            price=quantity*(5+2);
        }
        if(hasWhippedCream==true && hasChocolate==true)
        {
            price=quantity*(5+2+1);
        }
        String priceMessage = "Order Summary"+"\n\n"+"Drink: "+value+"\n"+"Add Whipped Cream: "+hasWhippedCream+"\nAdd Chocolate: "+hasChocolate+"\nQuantity= "+quantity+"\nTotal : $" + price + "\n" + "Thank You!";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Costa Coffee order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}


