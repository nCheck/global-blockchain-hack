package com.collekarry.globlockhackthon;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Statistics extends AppCompatActivity {

    TextView points;
    String point;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Statistics");
        setSupportActionBar(toolbar);toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backpress_white_icon);

        points = findViewById(R.id.points);

        updateStatus();

    }

    private void updateStatus() {

        progressDialog = ProgressDialog.show(this, "Loading", "Loading... Please wait", true, false);

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url;
        url = "http://therishabhsingh.com/helfy/getpoint.php?";
        String goodurl = url.replaceAll(" ", "%20");
        StringRequest postRequest = new StringRequest(Request.Method.GET, goodurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray = null;

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i<jsonArray.length(); i++) {

                        JSONObject post = jsonArray.getJSONObject(i);

                        point = post.getString("point");

                        points.setText(point);

                        progressDialog.dismiss();

                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();

                    progressDialog.dismiss();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error","error"+error.toString());
                    }
                }

        );

        queue.add(postRequest);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
