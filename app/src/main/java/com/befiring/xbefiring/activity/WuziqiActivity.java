package com.befiring.xbefiring.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.befiring.xbefiring.CustomView.WuziqiPanel;
import com.befiring.xbefiring.R;

public class WuziqiActivity extends AppCompatActivity {

    WuziqiPanel wuziqiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuziqi);

        wuziqiPanel= (WuziqiPanel) findViewById(R.id.wuziqi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
