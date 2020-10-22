package com.example.akanksha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int mod(int n, int m) {
        return ((n % m) + m) % m;
    }
    int currentScreen = 0;
    int[] buttonIds = new int[]{
                R.id.button,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9,
                R.id.button10
        };

    Map<Integer,Integer> buttonToScreen = new HashMap<>();
    Map<Integer,int[]> screenToButtons = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] viewIds = new int[]{
                R.layout.activity_main,
                R.layout.activity_main1,
                R.layout.activity_main2,
                R.layout.activity_main3,
                R.layout.activity_main4,
        };
        for (int i = 0; i < buttonIds.length; i++){
            int viewIndex = (int) Math.floor(i / 2);
            int nextViewIndex  = (i%2 == 0) ? mod(viewIndex-1, 5) : ((viewIndex+1) % 5);
            buttonToScreen.put(buttonIds[i], viewIds[nextViewIndex]);
        }
        for (int i = 1; i < buttonIds.length; i+=2){
            int viewIndex = (int) Math.floor(i / 2);
            screenToButtons.put(viewIds[viewIndex], new int[] {buttonIds[i-1], buttonIds[i]});
        }

        setContentView(R.layout.activity_main);
        int[] buttonIds  =screenToButtons.get(R.layout.activity_main);
        findViewById(buttonIds[0]).setOnClickListener(this);
        findViewById(buttonIds[1]).setOnClickListener(this);

    }

    @Override
    public void onClick(View button) {
        int viewId = buttonToScreen.get(button.getId());
        setContentView(viewId);
        int[] buttonIds  =screenToButtons.get(viewId);
        findViewById(buttonIds[0]).setOnClickListener(this);
        findViewById(buttonIds[1]).setOnClickListener(this);
    }
}