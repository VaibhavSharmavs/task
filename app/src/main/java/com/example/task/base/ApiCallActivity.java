package com.example.task.base;


import android.app.Fragment;
import android.app.FragmentManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task.R;

public class ApiCallActivity extends AppCompatActivity {

    public void fragmentSwitching(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
}
