package com.example.pinapple_.screens.tasks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pinapple_.App;
import com.example.pinapple_.R;
import com.example.pinapple_.databinding.FragmentTasksBinding;
import com.example.pinapple_.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TasksFragment extends Fragment {
    TasksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        App app = (App)(getContext().getApplicationContext());
        FragmentTasksBinding binding = FragmentTasksBinding.inflate(inflater);

        TasksFragmentArgs args = TasksFragmentArgs.fromBundle(requireArguments());
        Toast.makeText(getContext(), args.getDate(), Toast.LENGTH_SHORT).show();

        TaskAdapter adapter = new TaskAdapter();

        viewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        viewModel.getAllTasks().observe(getViewLifecycleOwner(), (allTasks) -> {
            Log.d("mytag", allTasks.size() + "");
            adapter.setData(allTasks);
        });
//        adapter.setData(app.tasksService.getTasks());
        binding.taskList.setAdapter(adapter);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addDayFragment: createTask();
        }
        return true;
//        return super.onOptionsItemSelected(item);
    }

    private void createTask() {
        Log.d("mytag", "createTask()");
        Calendar date = Calendar.getInstance();
        date.set(2022, 6, 1);
        Task task = new Task(
                new SimpleDateFormat("YYYY-MM-dd").format(date.getTimeInMillis()),
                "информатика",
                "закончить презентацию к проекту IT ШКОЛА SAMSUNG"
        );
        viewModel.insert(task);
    }
}