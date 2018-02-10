package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

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

import static android.R.attr.name;
import static android.R.attr.order;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        CheckBox checkMarkWhipped = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean hasWhippedCream = checkMarkWhipped.isChecked();

        CheckBox checkChocolate = (CheckBox) findViewById(R.id.notify_me_checkbox_chocolate);
        boolean hasChocolate = checkChocolate.isChecked();

        EditText text = (EditText)findViewById(R.id.enterText);
        String nameCheck = text.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);


        Log.v("MainActivity", "Has Whipped Cream: " + hasWhippedCream + hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, nameCheck);
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for:" + nameCheck);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);



        }

    }






    /**
     *
     * Calculates the price of the order
     *
     * @return total price
     */

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {


        int cost = 5;
        int whippedPrice = 0;
        int chocolatePrice = 0;

        if (hasWhippedCream){

            whippedPrice =  whippedPrice + 1;

        }

        if (hasChocolate) {

            chocolatePrice = chocolatePrice + 2;

        }

        int price = quantity * (cost + whippedPrice + chocolatePrice);


        return price;
    }

    /**
     * This method displays the name on the screen
     *
     *
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String nameCheck) {

        String order = getString(R.string.order_summary_name, nameCheck) + "\nAdd Whipped Cream? " + hasWhippedCream + "\nAdd Chocolate? " +
                hasChocolate + "\nQuantity : " + quantity + "\nTotal : $" + price + "\n" + getString(R.string.thank_you);

        return order;


    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "100 is the limit", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;

        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "1 is the least amount", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);}}

