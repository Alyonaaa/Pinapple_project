package com.example.pinapple_.screens.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple_.R;
import com.example.pinapple_.databinding.DayBinding;
import com.example.pinapple_.model.Day;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {
    List<Day> data;
    public void setData(List<Day> list) {
        data = list;
        notifyDataSetChanged();
    }

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
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day.date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String date = new SimpleDateFormat("YYYY-dd-MM").format(day.date);
        holder.binding.dayName.setText(getDayName(dayOfWeek));
        holder.binding.dayDate.setText(date);
        holder.binding.dayCountTasks.setText("5 дз");
        holder.binding.getRoot().setOnClickListener((v) -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(MainFragmentDirections.actionMainFragmentToTasksFragment(date));
        });
    }

    private String getDayName(int num) {
        Log.d("mytag", num + "");
        if (num == 1){
            return "Понедельник";

        }
        if (num == 2){
            return "Вторник";
        }
        if (num == 3){
            return "Среда";
        }
        if (num == 4){
            return "Четверг";
        }
        if (num == 5){
            return "Пятница";
        }
        if (num == 6){
            return "Суббота";
        }
        if (num == 7){
            return "Воскресенье";
        }
        return "";
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

