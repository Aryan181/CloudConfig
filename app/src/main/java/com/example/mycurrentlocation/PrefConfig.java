package com.example.mycurrentlocation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class PrefConfig {
    private static final String LIST_KEY = "list_key";
    public static <TaskModel> void writeListInPref(Context context, List<TaskModel>list)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }
    public static <TaskModel> List<TaskModel> readListFromPref(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY,"");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TaskModel>>(){}.getType();
        List<TaskModel> list = gson.fromJson(jsonString, type);
        return list;
    }

}