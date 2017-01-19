package com.example.amk.myfirstapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by AdilMateenKhan1 on 19-01-2017.
 */

public class UserSettingActivity  extends PreferenceActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);


    }
}
