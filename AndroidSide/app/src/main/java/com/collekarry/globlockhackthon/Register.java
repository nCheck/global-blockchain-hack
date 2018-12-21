package com.collekarry.globlockhackthon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class Register extends AppCompatActivity {

    String email="", name="", phone="", password="", address="";

    EditText Email, Name, Phone, Password, Address;

    Button Submit;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = findViewById(R.id.email);
        Name = findViewById(R.id.name);
        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        Address = findViewById(R.id.address);
        Submit = findViewById(R.id.loginButton);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()){
                    addNGO();
                }
            }
        });

    }

    public Boolean checkFields() {

        email = Email.getText().toString().trim();
        name = Name.getText().toString().trim();
        phone = Phone.getText().toString().trim();
        password = Password.getText().toString().trim();
        address = Address.getText().toString().trim();

        if (email.isEmpty()) {
            print("Enter email id to continue");
            return false;
        } else if (name.isEmpty()){
            print("Enter name to continue");
            return false;
        } else if (phone.isEmpty()){
            print("Enter Phone number to continue");
            return false;
        } else if (password.isEmpty()){
            print("Enter Password to continue");
            return false;
        } else if (address.isEmpty()){
            print("Enter Address to continue");
            return false;
        }

        return true;

    }

    public void print(String s) {
        Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
    }

    private void addNGO()
    {

        progressDialog = ProgressDialog.show(this, "Loading", "Loading... Please wait", true, false);

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url;
        url = "http://therishabhsingh.com/helfy/neworg.php?email="+email+"&name="+name+"&phone="+phone+"&address="+address+"&password="+password;
        String goodurl = url.replaceAll(" ", "%20");
        StringRequest postRequest = new StringRequest(Request.Method.GET, goodurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                print("We have recorded your details, will contact you for further updates");
                onBackPressed();

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
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Register.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
