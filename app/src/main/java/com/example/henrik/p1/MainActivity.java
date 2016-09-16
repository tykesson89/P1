package com.example.henrik.p1;

import android.app.Fragment;
import android.app.FragmentManager;


import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private FragmentIncome fragmentIncome;
    private FragmentLogin fragmentLogin;
    private MainFragment mainFragment;
    private String tag = "myFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        SharedPreferences prefs = getSharedPreferences("Inlogg", MODE_PRIVATE);
        String restoredText = prefs.getString("Firstname", null);
        if(restoredText != null) {

            FragmentManager fm = getFragmentManager();
            if(fm.findFragmentByTag(tag)==null) {
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.main_container, new MainFragment(), tag);
                fragmentTransaction.commit();
            }

        }else {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.main_container, new FragmentLogin(), tag);
            fragmentTransaction.commit();

        }
        Controller controller = new Controller(this, new MainFragment(), new FragmentIncome());
    }
    public void swapFrag(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container,fragment,tag);
        ft.addToBackStack(null);
        ft.commit();

    }
    public String getSharedPreferences(){
        SharedPreferences prefs = getSharedPreferences("Inlogg", MODE_PRIVATE);
        String firstname = prefs.getString("Firstname", null);
        String lastname = prefs.getString("Lastname", null);

        return firstname + " " + lastname;

    }
}
