package com.example.currencyrate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ValuteAdapter adapter;
    private ArrayList<Valute> arrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);


        getRates();
    }

    private void getRates() {
        Log.e("Infotag", "1 этап пройден!");
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("Infotag", "2 этап пройден!");
                    JSONObject jsonObject = response.getJSONObject("AUD");
                    Log.e("Infotag", "3 этап пройден!");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String countryId = jsonObject.getString("CharCode");
                        Double currentRate = Double.parseDouble(jsonObject.getString("Value"));
                        Double previousRate = Double.parseDouble(jsonObject.getString("Previous"));

                        Log.e("Infotag", countryId);
                        Valute valute = new Valute();
                        valute.setCountryId(countryId);
                        valute.setCurrentRate(currentRate);
                        valute.setPreviousRate(previousRate);

                        arrayList.add(valute);

//                    }

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