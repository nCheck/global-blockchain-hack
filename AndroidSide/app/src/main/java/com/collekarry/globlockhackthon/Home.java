package com.collekarry.globlockhackthon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gaurav.helfy.Adapter.OrdersAdapter;
import com.example.gaurav.helfy.Model.Order;

import org.json.JSONArray;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;

    OrdersAdapter adapter;

    public ArrayList<String> id = new ArrayList<String>();
    public ArrayList<String> type = new ArrayList<String>();
    public ArrayList<String> name = new ArrayList<String>();
    public ArrayList<String> timestamp = new ArrayList<String>();
    public ArrayList<String> email = new ArrayList<String>();
    public ArrayList<String> org = new ArrayList<String>();
    public ArrayList<String> status = new ArrayList<String>();
    public ArrayList<String> address = new ArrayList<String>();
    public ArrayList<String> contact = new ArrayList<String>();
    public ArrayList<String> item = new ArrayList<String>();
    public ArrayList<String> orgid = new ArrayList<String>();
    private ArrayList<Order> listContentArr= new ArrayList<>();

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Dashboard");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        request();

    }

    public void request() {
        progressDialog = ProgressDialog.show(this, "Loading", "Loading... Please wait", true, false);
        RequestQueue queue = Volley.newRequestQueue(Home.this);
        final String url;
        url = "http://therishabhsingh.com/helfy/fetchorders.php";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray = null;

//                try {
//
//                    JSONObject jsonObject = new JSONObject(response);
//                    jsonArray = jsonObject.getJSONArray("data");
//
//                    for (int i = 0; i<jsonArray.length(); i++) {
//
//                        JSONObject post = jsonArray.getJSONObject(i);
//
//                        id.add(post.getString("id"));
//                        type.add(post.getString("type"));
//                        name.add(post.getString("name"));
//                        timestamp.add(post.getString("timestamp"));
//                        email.add(post.getString("emial"));
//                        org.add(post.getString("org"));
//                        status.add(post.getString("status"));
//                        address.add(post.getString("address"));
//                        contact.add(post.getString("contact"));
//                        item.add(post.getString("item"));
//                        orgid.add(post.getString("orgid"));
//
//                    }
//
//                } catch (JSONException e1) {
//                    e1.printStackTrace();
//                }

                for (int i=0; i<10; i++) {

                    id.add(String.valueOf(i));
                    type.add("Cloths");
                    name.add("Cloth Donation");
                    timestamp.add(String.valueOf(15000));
                    email.add("udhay@gmail.com");
                    org.add("Smile Foundation");
                    status.add("Pending");
                    address.add("Chennai, India");
                    contact.add("9999997788");
                    item.add("Shirts");
                    orgid.add("12");

                }

                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
                adapter = new OrdersAdapter(Home.this);

                for (int iter = 1; iter <10; iter++) {

                    Order pojoObject = new Order();

                    pojoObject.setType(type.get(iter-1));
                    pojoObject.setName(name.get(iter-1));
                    pojoObject.setAddress(address.get(iter-1));
                    pojoObject.setTimesatmp(timestamp.get(iter-1));
                    pojoObject.setEmail(email.get(iter-1));
                    pojoObject.setOrg(org.get(iter-1));
                    pojoObject.setStatus(status.get(iter-1));
                    pojoObject.setContact(contact.get(iter-1));
                    pojoObject.setItem(item.get(iter-1));

                    listContentArr.add(pojoObject);
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                adapter.setListContent(listContentArr);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(Home.this, new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(child);

                            Intent go = new Intent(Home.this, Detail.class);
                            go.putExtra("id", id.get(position));
                            go.putExtra("orgid",orgid.get(position));
                            go.putExtra("name", listContentArr.get(position).getName());
                            go.putExtra("timestamp", listContentArr.get(position).getTimesatmp());
                            go.putExtra("email", listContentArr.get(position).getEmail());
                            go.putExtra("address", listContentArr.get(position).getAddress());
                            go.putExtra("status", listContentArr.get(position).getStatus());
                            go.putExtra("org", listContentArr.get(position).getOrg());
                            go.putExtra("contact", listContentArr.get(position).getContact());
                            go.putExtra("item", listContentArr.get(position).getItem());
                            go.putExtra("type", listContentArr.get(position).getType());
                            startActivity(go);
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                    }
                });

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        print(error.toString());
                        progressDialog.dismiss();
                    }
                }

        );

        queue.add(postRequest);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id){
            case R.id.social_media_item:
                Intent intent1 = new Intent(Home.this, SocialMedia.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.statistics_item:
                Intent i = new Intent(Home.this, Statistics.class);
                startActivity(i);
                finish();
                break;
            case R.id.funds_item:
                Intent intent = new Intent(Home.this, Funds.class);
                startActivity(intent);
                finish();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void print(String s) {
        Toast.makeText(Home.this, s, Toast.LENGTH_SHORT).show();
    }

}
