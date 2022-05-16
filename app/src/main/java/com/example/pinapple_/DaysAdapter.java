package com.example.pinapple_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {
    List<String> data = new ArrayList<>();
    Context context;

    DaysAdapter() {
        data.add("Понедельник");
        data.add("Вторник");
        data.add("Среда");
        data.add("Четверг");
        data.add("Пятница");
    }


    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.day, parent, false);
        return new DaysViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
//        String day = data.get(position);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.parent.getContext(), "CLick", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class DaysViewHolder extends RecyclerView.ViewHolder {
        View parent;

        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
        }
    }
}

