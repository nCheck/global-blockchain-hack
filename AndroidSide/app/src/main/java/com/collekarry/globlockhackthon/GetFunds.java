package com.collekarry.globlockhackthon;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class GetFunds extends AppCompatActivity {


    String[] reasons = {"Reason [Select]","Construction", "Accessories", "Daily Expense", "Emergency", "Electronics", "Bills", "Transportation", "Salary Payments", "Others"};

    String Reason="", others="", amount="";

    EditText Others, Amount;

    Button Submit;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_funds);

        Spinner spinner = findViewById(R.id.reason);
        Others = findViewById(R.id.other);
        Amount = findViewById(R.id.amount);
        Submit = findViewById(R.id.button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reasons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Reason = reasons[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFields()){
                    addpayment();
                }
            }
        });

    }

    private void addpayment() {

        progressDialog = ProgressDialog.show(this, "Loading", "Loading... Please wait", true, false);

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url;
        url = "http://therishabhsingh.com/helfy/newpayment.php?orgid=0&amount="+amount+"&reason="+reasons;
        String goodurl = url.replaceAll(" ", "%20");
        StringRequest postRequest = new StringRequest(Request.Method.GET, goodurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onBackPressed();
                finish();

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

    public Boolean checkFields() {

        others = Others.getText().toString().trim();
        amount = Amount.getText().toString().trim();

        if (Reason.equals("Reason [Select]")){
            print("Choose a Reason for a withdrawal to continue");
        }

        if (Reason.equals("Others")){
            if (others.isEmpty()){
                print("Enter a reason for the withdrawal");
                return false;
            } else {
                Reason = others;
            }
        } else if (amount.isEmpty()){
            print("Enter a specific amount for the withdrawal");
            return false;
        }

        return true;

    }

    public void print(String s) {
        Toast.makeText(GetFunds.this, s, Toast.LENGTH_SHORT).show();
    }


}
