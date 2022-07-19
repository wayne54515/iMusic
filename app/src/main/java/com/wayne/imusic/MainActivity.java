package com.wayne.imusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private List<MusicListItem> musicListItems;

    public static final String TAG = "music request";
    Button searchBtn;
    public String musicEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        musicListItems = new ArrayList<>();

        final EditText searchEditText = (EditText)findViewById(R.id.editTextMusicName);
        searchBtn = findViewById(R.id.searchButton);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                musicEditText = searchEditText.getText().toString();

                if (musicEditText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();
                }
                else{
                    String iTunesApiUrl = "https://itunes.apple.com/search?term=" + musicEditText + "&media=music";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, iTunesApiUrl,
                            new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        MusicListItem musicItem = new MusicListItem(
                                                object.getString("trackName"),
                                                object.getString("previewUrl")
                                        );
                                        musicListItems.add(musicItem);
                                    }
                                    System.out.println("test3");

                                    recyclerViewAdapter = new MusicAdapter(musicListItems, getApplicationContext());
                                    recyclerView.setAdapter(recyclerViewAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("test4");

                            }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error);
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                    stringRequest.setTag(TAG);
                    requestQueue.add(stringRequest);

                }
            }
        });

        if (requestQueue != null){
            requestQueue.cancelAll(TAG);
        }
    }

}