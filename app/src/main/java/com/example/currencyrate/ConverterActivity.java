package com.example.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;

public class ConverterActivity extends AppCompatActivity {
    EditText firstCountry;
    EditText secondCountry;
    ImageView firstImage;
    ImageView secondImage;
    TextView firstCountryID;
    TextView secondCountryID;
    String countryID;
    String stringRate;
    Converter converter;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        Intent intent = getIntent();
        firstCountry = findViewById(R.id.firstCountryEditText);
        secondCountry = findViewById(R.id.secondCountryEditText);
        firstImage = findViewById(R.id.firstCountryFlag);
        secondImage = findViewById(R.id.secondCountryFlag);
        firstCountryID = findViewById(R.id.firstCountryId);
        secondCountryID = findViewById(R.id.secondCountryId);
        countryID = intent.getStringExtra("countryID");
        stringRate = intent.getStringExtra("rate").replace(",", ".");
        double rate = Double.parseDouble(stringRate);
        int imageID = ConverterActivity.this.getResources().getIdentifier(countryID.toLowerCase(Locale.ROOT), "drawable", getPackageName());
        decimalFormat = new DecimalFormat("#.##");

        firstImage.setImageResource(R.drawable.rub);
        secondImage.setImageResource(imageID);
        firstCountryID.setText("RUB");
        secondCountryID.setText(intent.getStringExtra("countryID"));


        firstCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
//                    secondCountry.setText(convert(String.valueOf(rate), firstCountry.getText().toString()), TextView.BufferType.EDITABLE);
                } else {
                    secondCountry.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        secondCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String convertedValue;
                if (charSequence.length() != 0) {
                convertedValue = convert(String.valueOf(rate), secondCountry.getText().toString());
                firstCountry.setText(decimalFormat.format(Double.parseDouble(convertedValue)));
            }
        }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public String convert(String course, String count) {
        return String.valueOf(Double.parseDouble(count) * Double.parseDouble(course));
    }
}