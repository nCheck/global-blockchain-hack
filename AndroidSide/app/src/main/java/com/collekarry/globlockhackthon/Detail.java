package com.collekarry.globlockhackthon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Detail extends AppCompatActivity {

    CircleImageView pic;

    TextView statusText, addresstext, typetext, itemtext, emailtext, mobiletext, nametext;

    RelativeLayout itemBox;

    String name, status, timestamp, email, mobile, type, item, orgid, id, address;

    LinearLayout statusBox;

    Button accept, reject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Donation Info");
        setSupportActionBar(toolbar);toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backpress_white_icon);

        pic = findViewById(R.id.image);
        nametext = findViewById(R.id.name);
        statusText = findViewById(R.id.status);
        addresstext = findViewById(R.id.address);
        typetext = findViewById(R.id.type);
        mobiletext = findViewById(R.id.mobile);
        itemtext = findViewById(R.id.item);
        emailtext = findViewById(R.id.email);
        accept = findViewById(R.id.accept);
        reject = findViewById(R.id.reject);
        statusBox = findViewById(R.id.statusBox);
        itemBox = findViewById(R.id.itemBox);

        statusBox.setVisibility(View.GONE);

        Picasso.with(this)
                .load("https://scontent-bom1-1.xx.fbcdn.net/v/t1.0-9/30743463_1678653912214695_4925833537208188928_o.jpg?_nc_cat=0&oh=fe2e628513cb27dd92d705e6bdd90cfd&oe=5C276BDC")
                .error(R.drawable.user_icon)
                .placeholder(R.drawable.user_icon)
                .into(pic);

        final Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        String key;

        timestamp = bundle.getString("timestamp");
        nametext.setText(bundle.getString("name"));
        statusText.setText(bundle.getString("status"));
        addresstext.setText(bundle.getString("address"));
        typetext.setText(bundle.getString("type"));
        emailtext.setText(bundle.getString("email"));
        if (Objects.equals(bundle.get("type"), "Monetary")){
            itemtext.setText(bundle.getString("item"));
        } else {
            itemBox.setVisibility(View.GONE);
        }
        mobiletext.setText(bundle.getString("contact"));
        id = bundle.getString("id");
        orgid = bundle.getString("orgid");

        if (statusText.getText().equals("New")){
            statusBox.setVisibility(View.VISIBLE);
        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus("Accepted");
                statusText.setText("Accepted");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus("Rejected");
                statusText.setText("Rejected");
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent i = new Intent(Detail.this, Home.class);
        startActivity(i);
        finish();

    }

    private void updateStatus(String status) {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url;
        url = "http://therishabhsingh.com//helfy/updatestatus.php?id="+id+"&status="+status+"&orgid="+orgid+"&timestamp="+timestamp;
        String goodurl = url.replaceAll(" ", "%20");
        StringRequest postRequest = new StringRequest(Request.Method.GET, goodurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                print("Status updated successfully");
                statusBox.setVisibility(View.GONE);

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

    public void print(String s) {
        Toast.makeText(Detail.this, s, Toast.LENGTH_SHORT).show();
    }

}
