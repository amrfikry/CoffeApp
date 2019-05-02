package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import javax.xml.datatype.Duration;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


    public void submitOrder(View view) {
        CheckBox whippedCreamCheckedbox =(CheckBox) findViewById(R.id.Whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckedbox.isChecked();

        CheckBox chocolateCheckedbox =(CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean hasChocolate = chocolateCheckedbox.isChecked();

        EditText name = (EditText) findViewById(R.id.name_view);
        String Name = name.getText().toString()  ;
        calculatePrice ();
        int price = calculatePrice() ;

        if (hasWhippedCream){
            price += (quantity * 1);
        }
        if (hasChocolate)
        {
            price += (quantity * 2);
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + Name);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(price , hasWhippedCream , hasChocolate , Name) );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    //

    /**
     * This method displays the given quantity value on the screen.
     */
    public void increment(View view) {
        if (quantity == 100){
            Toast.makeText(this , "You can't order above 100 cups" , Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity); }

    public void decrement(View view) {
        if (quantity == 1){
            Toast.makeText(this , "You can't order under 1 cup" , Toast.LENGTH_SHORT).show();

            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity); }
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

    /**
     * This method displays the given text on the screen.
     */
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int cup =5;

        int price = quantity * cup;

        return  price;
    }

    private String createOrderSummary(int total , boolean addWhippedCream , boolean addChocolate , String name ){

        String message ="Name : " + name + "\n" + "add whipped cream? " + addWhippedCream +"\n" +
               "add Chocolate? " + addChocolate + "\n" + "Quantity :"+  quantity + "\n" + "Total: $ "+
                total + "\n " + getString(R.string.thank_you);

                return message;
    }

}