package com.collekarry.globlockhackthon.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.collekarry.globlockhackthon.Model.Order;
import com.collekarry.globlockhackthon.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    //Creating an arraylist of POJO objects
    private ArrayList<Order> list_members = new ArrayList<>();
    private final LayoutInflater inflater;
    View view;
    MyViewHolder holder;
    private Context context;

    public OrdersAdapter(Context context){
        this.context=context;
        inflater= LayoutInflater.from(context);
    }
    //This method inflates view present in the RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view=inflater.inflate(R.layout.request_card, parent, false);
        holder=new MyViewHolder(view);
        return holder;
    }

    //Binding the data using get() method of POJO object
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Order list_items=list_members.get(position);

        holder.type.setText(list_items.getType());
        holder.status.setText(list_items.getStatus());


        Date d = new Date(Long.parseLong(list_items.getTimesatmp())*1000);
        @SuppressLint("SimpleDateFormat")
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        String date = f.format(d);

        String[] Date = date.split("-");

        holder.day.setText(Date[2]);
        holder.monthyear.setText(getMonth(Date[1])+Date[0]);

        String status = list_items.getStatus().trim();

        if (status.equals("New")) {
            holder.typeColor.setBackgroundColor(context.getResources().getColor(R.color.blue));
        } else if (status.equals("Accepted")) {
            holder.typeColor.setBackgroundColor(context.getResources().getColor(R.color.green));
        } else {
            holder.typeColor.setBackgroundColor(context.getResources().getColor(R.color.red));
        }

    }

    //Setting the arraylist
    public void setListContent(ArrayList<Order> list_members){
        this.list_members=list_members;
        notifyItemRangeChanged(0,list_members.size());

    }

    public String getMonth(String m){

        switch (m){

            case "01":
                return "JANUARY";

            case "02:":
                return "FEBRUARY";

            case "03":
                return "MARCH";

            case "04":
                return "APRIL";

            case "05":
                return "MAY";

            case "06":
                return "JUNE";

            case "07":
                return "JULY";

            case "08":
                return "AUGUST";

            case "09":
                return "SEPTEMBER";

            case "10":
                return "OCTOBER";

            case "11":
                return "NOVEMBER";

            case "12":
                return "DECEMBER";

        }

        return "NOT AVAILABLE";

    }

    @Override
    public int getItemCount() {
        return list_members.size();
    }

    //View holder class, where all view components are defined
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView type, day, monthyear, status, typeColor;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            type = itemView.findViewById(R.id.type);
            day = itemView.findViewById(R.id.day);
            monthyear = itemView.findViewById(R.id.month_and_year);
            status = itemView.findViewById(R.id.status);
            typeColor = itemView.findViewById(R.id.type_color);
        }


        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {

            //CustomPojo list_items=list_members.get(position);
            //Intent intent = new Intent(context, wallapaperShow.class);
            //intent.putExtra("Id",list_items.getId());
            //context.startActivity(intent);

        }


    }

    public void removeAt(int position) {
        list_members.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, list_members.size());
    }

}

