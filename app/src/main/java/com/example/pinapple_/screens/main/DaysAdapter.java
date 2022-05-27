package com.example.pinapple_.screens.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple_.databinding.DayBinding;
import com.example.pinapple_.model.Day;

import java.text.SimpleDateFormat;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {
    List<Day> data;
    public void setData(List<Day> list) {
        data = list;
        notifyDataSetChanged();
    }

//    DaysAdapter() {
//        data.add("Понедельник");
//        data.add("Вторник");
//        data.add("Среда");
//        data.add("Четверг");
//        data.add("Пятница");
//    }


    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DayBinding binding = DayBinding.inflate(inflater, parent, false);
        return new DaysViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {

        Day day = data.get(position);
        String date = new SimpleDateFormat("YYYY-MM-dd").format(day.date);
        holder.binding.dayName.setText(date);
        holder.binding.dayDate.setText(date);
        holder.binding.dayCountTasks.setText("5 дз");
        holder.binding.getRoot().setOnClickListener((v) -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(MainFragmentDirections.actionMainFragmentToTasksFragment(date));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DaysViewHolder extends RecyclerView.ViewHolder {
        DayBinding binding;

        public DaysViewHolder(@NonNull DayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

