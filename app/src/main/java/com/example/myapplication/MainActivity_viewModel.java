package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class MainActivity_viewModel extends ViewModel {

    private MutableLiveData<Integer> count;

    public void MainviewModel(){
        count.setValue(0);
    }

    public String updateCount(String amount){
        int amountint = Integer.parseInt(amount);
        count.setValue(amountint);
        return count.toString();
    }

}
