package com.example.henrik.p1;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;


/**
 * Created by Henrik on 2016-09-09.
 */
public class Controller {




    public Controller(Activity activity, MainFragment mainFragment, FragmentIncome fragmentIncome ){
        mainFragment.setController(this);
        fragmentIncome.setController(this);

        initResources(activity);

    }

    private void initResources(Activity activity){
        Resources res = activity.getResources();
    }




}
