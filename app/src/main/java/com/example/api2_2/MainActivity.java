package com.example.api2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataBahasa> dataBahasas = new ArrayList();
    protected final String urlAPI = "https://ewinsutriandi.github.io/mockapi/bahasa_pemrograman.json";
    JSONObject dataBahasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataAPI();
    }

    void setupLisView(){
        ListView listView = findViewById(R.id.listView);
        BahasaAdapter adapter = new BahasaAdapter(this, dataBahasas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataBahasa fSELECTED = dataBahasas.get(position);
            Toast.makeText(MainActivity.this, fSELECTED.getReadMore(), Toast.LENGTH_SHORT).show();
            toLink(fSELECTED);
        }
    };

    void getDataAPI(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlAPI, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dataBahasa = response;
                        refreshView();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error",error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    void toLink(DataBahasa data) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(data.getReadMore()));
        startActivity(intent);
    }

    void refreshView(){
        Iterator<String> key = dataBahasa.keys();
        while (key.hasNext()){
            String namaBahasaPemerograman = key.next();
            try {
                JSONObject data = dataBahasa.getJSONObject(namaBahasaPemerograman);
                String deskripsi = data.getString("description");
                String contohKode = data.getString("hello_world");
                String logo = data.getString("logo");
                String readMore = data.getString("read_more");

                dataBahasas.add(new DataBahasa(namaBahasaPemerograman,deskripsi,readMore,contohKode,logo));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        setupLisView();
    }

}