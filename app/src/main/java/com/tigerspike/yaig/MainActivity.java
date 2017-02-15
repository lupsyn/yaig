package com.tigerspike.yaig;

import android.app.SearchManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.tigerspike.business.controller.network.ImagesCallBack;
import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.network.NetworkController;
import com.tigerspike.yaig.fragments.MainListFragment;
import com.tigerspike.yaig.utils.PermissionsManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;
    private PermissionsManager mPermissionManager;
    private MainListFragment mMainListFragment;

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

        if (mMainListFragment == null)
            mMainListFragment = new MainListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mMainListFragment).addToBackStack(MainListFragment.class.getName()).commit();
        getSupportFragmentManager().executePendingTransactions();

        final NetworkController controller = new NetworkController(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                controller.getPublicImages(new ImagesCallBack() {
                    @Override
                    public void onSuccess(List<FlickrImage> publicFeed) {

                        mMainListFragment.refresh(publicFeed);
                    }

                    @Override
                    public void onNetworkError() {

                    }

                    @Override
                    public void onError(String error) {

                    }

                });


            }
        }, 400);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
//        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
//
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String text) {
//                weatherRecyclerAdapter.clear();
//                mainPresenter.onQuery(text);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

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
