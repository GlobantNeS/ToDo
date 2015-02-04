package com.example.kaineras.todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements ListFragment.TasksListener{


    final static int RESQUEST_CODE=0;
    Toolbar toolbar;
    Bundle bundle=new Bundle();
    Tools tools;
    boolean isTask=false;
    ArrayList<String> tasks=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tools=new Tools();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null || !isTask)
            loadEmpty();
        else
            loadListView(bundle);
        prepareToolbar();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("SAVE_TASKS",tasks);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tasks=savedInstanceState.getStringArrayList("SAVE_TASKS");
    }

    private void loadEmpty() {
        EmptyFragment f = new EmptyFragment();
        tools.loadFragment(getFragmentManager(), f, R.id.container, "START");
    }

    private void loadListView(Bundle bundle) {
        ListFragment f = new ListFragment();
        f.setArguments(bundle);
        tools.loadFragment(getFragmentManager(), f, R.id.container, "START");
    }

    private void prepareToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        //setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        boolean handler;

        switch (id)
        {
            case R.id.action_add:
                Intent intent=new Intent(MainActivity.this,CreateTask.class);
                bundle=new Bundle();
                startActivityForResult(intent,RESQUEST_CODE);
                handler=true;
                break;
            default:
                handler=super.onOptionsItemSelected(item);
                break;
        }
        return handler;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.v("DATOS",String.valueOf( tasks.size()));
                tasks.add(data.getStringExtra("TITLE"));
                bundle.putStringArrayList("TASKS",tasks);
                isTask=true;
                loadListView(bundle);
            }
        }
    }

    @Override
    public void TasksListener(String task) {
        /*ViewFragment f = (ViewFragment) getFragmentManager().findFragmentById(R.id.rightpane);
        f.setOptions(optionMenu);*/
    }
}