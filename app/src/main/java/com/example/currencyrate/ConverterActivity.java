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
    TextView firstConvertedValue;
    TextView secondConvertedValue;
    DecimalFormat decimalFormat;
    Converter converter;

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
        firstConvertedValue = findViewById(R.id.firstConvertedValue);
        secondConvertedValue = findViewById(R.id.secondConvertedValue);
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
                String convertedValue;
//                converter = new Converter(Double.parseDouble(firstCountry.getText().toString()), rate);
                if (charSequence.length() != 0) {
//                    convertedValue = converter.convertValuteToRuble();
                    convertedValue = convertRubleToValute(String.valueOf(rate), firstCountry.getText().toString());
                secondConvertedValue.setText(decimalFormat.format(Double.parseDouble(convertedValue)));
                } else {
                    secondConvertedValue.setText("");
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
//                converter = new Converter(Double.parseDouble(secondCountry.getText().toString()), rate);
                if (charSequence.length() != 0) {
//                    convertedValue = converter.convertRubleToValute();
                    convertedValue = convertValuteToRuble(String.valueOf(rate), secondCountry.getText().toString());
                    firstConvertedValue.setText(decimalFormat.format(Double.parseDouble(convertedValue)));
                } else {
                    firstConvertedValue.setText("");
                }
        }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public String convertValuteToRuble(String course, String count) {
        return String.valueOf(Double.parseDouble(count) * Double.parseDouble(course));
    }

    public String convertRubleToValute(String course, String count) {
        return String.valueOf(Double.parseDouble(count) / Double.parseDouble(course));
    }
}