package com.ryandro.covid_19_tracker.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ryandro.covid_19_tracker.R;
import com.ryandro.covid_19_tracker.adapter.CountryAdapter;
import com.ryandro.covid_19_tracker.model.Feature;
import com.ryandro.covid_19_tracker.model.MainResponseObj;
import com.ryandro.covid_19_tracker.service.APIClient;
import com.ryandro.covid_19_tracker.service.APIInterface;
import com.ryandro.covid_19_tracker.utils.NetworkUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private FloatingActionButton mFabButton;
    private NavigationView mNavigationView;
    private LinearLayout llLoading;
    private APIInterface apiInterface = null;
    private RecyclerView rvList;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        setStatusBarColor();
        initializeControls();
        bindControl();
//        getInfoFromServer();
    }

    private void getInfoFromServer() {
        if (NetworkUtility.isNetworkAvailable(DashBoardActivity.this)) {
            displayProgressDialog();
            Call<MainResponseObj> call = apiInterface.getAllInfoFromServer();
            call.enqueue(new Callback<MainResponseObj>() {
                @Override
                public void onResponse(Call<MainResponseObj> call, Response<MainResponseObj> response) {
                    hideProgressDialog();
                    Log.d("Service call :", "Success");
                    Log.d("url ", "" + call.request().url().toString());
                    MainResponseObj mainResponseObj = response.body();
                    refreshViewData(mainResponseObj);
                }

                @Override
                public void onFailure(Call<MainResponseObj> call, Throwable t) {
                    Log.e("Service call :", "failed");
                    hideProgressDialog();
                }
            });
        } else {
            Toast.makeText(DashBoardActivity.this, getResources().getString(R.string.ConnectionIssue), Toast.LENGTH_SHORT).show();
        }

    }

    private void refreshViewData(MainResponseObj mainResponseObj) {
        Log.d("Main Data", new Gson().toJson(mainResponseObj).toString());
        adapter.refresh(mainResponseObj.getFeatures());
//        tv_data.setText(new Gson().toJson(mainResponseObj).toString());
    }

    private void bindControl() {
        mNavigationView.setNavigationItemSelectedListener(this);

        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInfoFromServer();
            }
        });


    }

    private void initializeControls() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mFabButton = (FloatingActionButton) findViewById(R.id.fab);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        llLoading = (LinearLayout) findViewById(R.id.llLoading);
        rvList = (RecyclerView) findViewById(R.id.rvList);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        adapter = new CountryAdapter(this, new ArrayList<Feature>());
        rvList.setAdapter(adapter);
    }

    private void setStatusBarColor() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayProgressDialog() {
        llLoading.setVisibility(View.VISIBLE);
        rvList.setVisibility(View.GONE);
    }

    private void hideProgressDialog() {
        llLoading.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);
    }
}
