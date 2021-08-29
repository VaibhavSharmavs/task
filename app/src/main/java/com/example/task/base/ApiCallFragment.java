package com.example.task.base;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



import com.example.task.R;
import com.example.task.network.Api;
import com.example.task.network.ThisApp;
import com.example.task.utils.Helper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiCallFragment<T> extends Fragment {


    @Override
    public void onResume() {
        super.onResume();
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onPause() {
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Subscribe
    public void timeOut(String msg) {
        String cap = msg.substring(0, 1).toUpperCase() + msg.substring(1);
        dialogFailed(cap);
    }


    public Api getApi() {
        return ThisApp.getApiCommonController(getActivity());
    }


    public void apiCallBack(Call<T> callback) {
        if (!Helper.isConnected(getContext())) {
            Dialog dialog = new Dialog(getActivity());
            dialog.getWindow().requestFeature(1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.setContentView(R.layout.dialog_internet);
            dialog.setCancelable(true);
            LinearLayout retry = (LinearLayout) dialog.findViewById(R.id.retry);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    apiCallBack(callback);
                }
            });

            dialog.show();
        } else {
//            MyProgressDialog.getInstance(getActivity()).show();
            callback.enqueue(new Local<T>());
        }

    }


    public void dialogFailed(String msg) {
        try {

            Dialog dialog = new Dialog(getActivity());
            dialog.getWindow().requestFeature(1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.setContentView(R.layout.dialog_failed);
            dialog.setCancelable(false);
            LinearLayout retry = dialog.findViewById(R.id.retry);
            TextView message = dialog.findViewById(R.id.message);
            message.setText(msg);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class Local<T> implements Callback<T> {

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
//            MyProgressDialog.setDismiss();
            if (response.code() == 200) {
                T body = response.body();
                EventBus.getDefault().post(body);
            } else {
                EventBus.getDefault().post(Helper.ERROR + " " + response.code());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
//            MyProgressDialog.setDismiss();
            EventBus.getDefault().post(t.getMessage());
        }
    }




}
