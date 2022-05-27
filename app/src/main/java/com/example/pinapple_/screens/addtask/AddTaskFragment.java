package com.example.pinapple_.screens.addtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pinapple_.R;
import com.example.pinapple_.databinding.FragmentAddTaskBinding;
import com.example.pinapple_.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class AddTaskFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAddTaskBinding binding = FragmentAddTaskBinding.inflate(inflater);

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                getSubjects()
        );

        binding.subjectsList.setAdapter(adapter);

        return binding.getRoot();
    }

    private List<Subject>  getSubjects() {
        List<Subject> list = new ArrayList<>();
        list.add(new Subject("Математика"));
        list.add(new Subject("Русский"));
        list.add(new Subject("Физика"));

        return list;
    }
}
