package com.example.maadprojectthree;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Customer> list;


    public MyAdapter(Context context, ArrayList<Customer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
          return  new MyViewHolder(v);
}


    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Customer customer = list.get(position);
        holder.email.setText(customer.getEmail());
        holder.firstName.setText(customer.getFirstName());
        holder.lastName.setText(customer.getLastName());
        holder.contact.setText(customer.getContactNumber());
        holder.userName.setText(customer.getUsername());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView contact,email,firstName,lastName,userName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contact = itemView.findViewById(R.id.contact);
            email = itemView.findViewById(R.id.email);
            firstName = itemView.findViewById(R.id.fName);
            lastName = itemView.findViewById(R.id.lName);
            userName = itemView.findViewById(R.id.userName);


        }
    }

}
