package com.example.pinapple_.screens.tasks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pinapple_.model.Task;
import com.example.pinapple_.model.TaskRepository;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private final LiveData<List<Task>> allTasks;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    void insert(Task task) {
        taskRepository.insert(task);
    }
}