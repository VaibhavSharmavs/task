package com.example.task;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.task.base.ApiCallActivity;
import com.example.task.fragment.CategoryListFragment;
import com.example.task.fragment.ItemListFragment;

public class MainActivity extends ApiCallActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        fragmentSwitching(new CategoryListFragment());

    }
}

