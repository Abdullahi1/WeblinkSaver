package com.example.abdullahi.weblinksaver;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abdullahi.weblinksaver.data.DataManager;

import java.io.Console;

public class MainActivity extends AppCompatActivity
        implements WebLinkListDialogFragment.Listener,
                        DialogBox.DialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataManager manager = DataManager.getInstance();
        RecyclerView recyclerView = findViewById(R.id.link_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        WebLinkRecyclerAdapter adapter = new WebLinkRecyclerAdapter(this,manager.getLinks());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainer,WebLinkListDialogFragment.newInstance(5))
//                        .addToBackStack(null)
//                        .commit();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null){
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                DialogFragment newFragment = WebLinkListDialogFragment.newInstance();
                newFragment.show(ft,"dialog");


            }
        });

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onWebLinkClicked(String webLink, String webTitle) {
    //    Toast.makeText(this, "The web title is : " + webTitle, Toast.LENGTH_LONG).show();
        Log.v("MainActivityValue","Web Title is : "+webTitle);

        DialogBox dialogBox = DialogBox.newInstance(webTitle,webLink);
        dialogBox.show(getSupportFragmentManager(),"New Dialog");

    }

    @Override
    public void applyText(String webLink, String webTitle) {

    }
}
