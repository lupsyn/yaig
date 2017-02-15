package com.tigerspike.yaig;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.tigerspike.business.logic.MainViewPresenter;
import com.tigerspike.data.LoggerController;
import com.tigerspike.network.NetworkController;
import com.tigerspike.yaig.fragments.MainListFragment;
import com.tigerspike.yaig.utils.ActivityUtils;
import com.tigerspike.yaig.utils.PermissionsManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;
    private PermissionsManager mPermissionManager;
    private MainListFragment mMainListFragment;
    private MainViewPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPermissionManager = new PermissionsManager(this);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);


        //getSupportActionBar().setDisplayShowTitleEnabled(false);


        MainListFragment mMainListFragment = (MainListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mMainListFragment == null) {
            mMainListFragment = MainListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMainListFragment, R.id.fragment_container);
        }
        NetworkController networkConnectivity = new NetworkController(this);
        LoggerController loggerController = new LoggerController();

        mPresenter = new MainViewPresenter(networkConnectivity, loggerController, null, mMainListFragment);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                mPresenter.retriveImage(text);
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


}

