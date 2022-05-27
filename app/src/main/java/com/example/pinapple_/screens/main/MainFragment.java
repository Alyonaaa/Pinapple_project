package com.example.pinapple_.screens.main;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pinapple_.App;
import com.example.pinapple_.R;
import com.example.pinapple_.databinding.AddDayBinding;
import com.example.pinapple_.screens.main.DaysAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        App app = (App)(getContext().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.day_list);
        DaysAdapter adapter = new DaysAdapter();
        adapter.setData(app.daysService.getDays());
        recyclerView.setAdapter(adapter);

        app.daysService.addListener(() -> {
            adapter.notifyDataSetChanged();
        });

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addDayFragment: showDialog();
        }
        return true;
//        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        App app = (App)(getContext().getApplicationContext());

        AddDayBinding addDayBinding = AddDayBinding.inflate(getLayoutInflater());
        addDayBinding.calendar.setOnDateChangeListener((view, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            view.setDate(calendar.getTimeInMillis(), true, true);
        });

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Add day")
                .setView(addDayBinding.getRoot())
                .setPositiveButton("add", (a, b) -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Toast.makeText(getContext(), dateFormat.format(addDayBinding.calendar.getDate()), Toast.LENGTH_SHORT).show();
                    app.daysService.addDay(addDayBinding.calendar.getDate());
                })
                .create();
        dialog.show();
    }
}