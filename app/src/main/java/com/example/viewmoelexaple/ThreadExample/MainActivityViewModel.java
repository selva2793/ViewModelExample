package com.example.viewmoelexaple.ThreadExample;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewmoelexaple.ThreadExample.MainActivity;

public class MainActivityViewModel extends ViewModel {

    private static  final String TAG = MainActivity.class.getSimpleName();

    Thread counterThread;
    int count = 0;
    boolean isCounterInprogress;
    MutableLiveData<Integer> counter;


    public MainActivityViewModel(){
        isCounterInprogress = false;
        counter = new MutableLiveData<>();
        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isCounterInprogress){
                    try {
                        Thread.sleep(1000);
                        count ++;
                        counter.postValue(count);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public MutableLiveData<Integer> getCounter(){
        return  counter;
    }

    public void startCounter(){

        try {
            if(!isCounterInprogress){
                isCounterInprogress = true;
                counterThread.start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public  void stopcounter(){

        isCounterInprogress =false;

    }

}
