package com.example.currencyrate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ValuteAdapter adapter;
    private ArrayList<Valute> arrayList;
    private RequestQueue requestQueue;
    private String[] charCode;
    private DecimalFormat decimalFormat;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        charCode = new String[]{"AUD", "AZN", "GBP", "AMD", "BYN", "BGN", "BRL", "HUF", "HKD", "DKK", "USD",
                "EUR", "INR", "KZT", "CAD", "KGS", "CNY", "MDL", "NOK", "PLN", "RON", "XDR", "SGD", "TJS",
                "TRY", "TMT", "UZS", "UAH", "CZK", "SEK", "CHF", "ZAR", "KRW", "JPY"};
        decimalFormat = new DecimalFormat("#.####");
        context = this;
        requestQueue = Volley.newRequestQueue(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRates();
                swipeRefreshLayout.setRefreshing(false);
            }

        });

        getRates();
    }

    private void getRates() {
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    for (int i = 0; i < charCode.length; i++) {
                        JSONObject jsonObject = response.getJSONObject("Valute").getJSONObject(charCode[i]);
                        String countryId = jsonObject.getString("CharCode");
                        Double currentRate = Double.parseDouble(jsonObject.getString("Value"));
                        Double previousRate = Double.parseDouble(jsonObject.getString("Previous"));

                        Valute valute = new Valute();
                        valute.setCountryId(countryId);
                        valute.setCurrentRate(currentRate);
                        valute.setPreviousRate(previousRate);
                        valute.setDifference(currentRate, previousRate);
                        int imageID = MainActivity.this.getResources().getIdentifier(countryId.toLowerCase(Locale.ROOT), "drawable", getPackageName());
                        valute.setCountryFlag(imageID);

                        arrayList.add(valute);
                    }

                    adapter = new ValuteAdapter(MainActivity.this, arrayList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}