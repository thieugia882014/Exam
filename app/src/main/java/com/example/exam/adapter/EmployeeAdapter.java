package com.example.exam.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.exam.R;
import com.example.exam.database.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter {

    Activity activity;
    List<Employee> List;

    public EmployeeAdapter(Activity activity, List<Employee> list) {
        this.activity = activity;
        this.List = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        UserHolder holder = new UserHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserHolder vh = (UserHolder) holder;
        Employee model = List.get(position);
        vh.tvID.setText(model.id + "");
        vh.edName.setText(model.name);
        vh.edDes.setText(model.des);
        vh.edSalary.setText(model.salary);

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView edName, tvID, edDes,edSalary;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            edName = itemView.findViewById(R.id.edName);

            edDes = itemView.findViewById(R.id.edDes);
            edSalary = itemView.findViewById(R.id.edSalary);
        }
    }
}





