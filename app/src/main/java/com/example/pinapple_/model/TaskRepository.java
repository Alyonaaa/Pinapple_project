package com.example.pinapple_.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> tasks;

    public TaskRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
        taskDao = taskDatabase.taskDao();
        tasks = taskDao.getAllTasks();
//        populateTextData();
    }

    public LiveData<List<Task>> getAllTasks() {
        return tasks;
    }

    public void insert(Task... tasks) {
        TaskDatabase.databaseExecutor.execute(() -> {
            taskDao.insert(tasks);
        });
    }

    public void populateTextData() {
        TaskDatabase.databaseExecutor.execute(() -> {
            taskDao.deleteAll();

            Calendar date = Calendar.getInstance();
            date.set(2022, 6, 3);

            Task task1 = new Task(
                    new SimpleDateFormat("yyyy-mm-dd").format(date.getTimeInMillis()),
                    "информатика",
                    "закончить проект IT ШКОЛА SAMSUNG"
            );

            date.set(2022, 6, 1);
            Task task2 = new Task(
                    new SimpleDateFormat("yyyy-mm-dd").format(date.getTimeInMillis()),
                    "информатика",
                    "закончить презентацию к проекту IT ШКОЛА SAMSUNG"
            );

            date.set(2022, 5, 31);
            Task task3 = new Task(
                    new SimpleDateFormat("yyyy-mm-dd").format(date.getTimeInMillis()),
                    "математика",
                    "что-то там сделать, блаблабла"
            );
            taskDao.insert(task1, task2, task3);
        });
    }

    public void update(Task task) {
        TaskDatabase.databaseExecutor.execute(() -> {
           taskDao.update(task);
        });
    }
}
