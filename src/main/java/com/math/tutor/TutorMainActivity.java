package com.math.tutor;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TutorMainActivity extends Activity {

    /**
     * Called when the activity is first created.
     *
     * @param savedIns
     * tanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */

    public String[] mNavigationDrawerItemTitles;
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    public ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();


        mNavigationDrawerItemTitles =
            getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];
        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_copy, "Create");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_refresh, "Read");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_action_share, "Help");

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(
            this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Second part:
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            R.drawable.ic_drawer,
            R.string.drawer_open,
            R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(mNavigationDrawerItemTitles[1]);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.math.tutor.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }



    /**
     * Created by mukthar.ahmed on 10/08/14.
     */
    class DrawerItemClickListener implements ListView.OnItemClickListener {

        public ListView mDrawerList;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }



        private void selectItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new CreateFragment();
                    break;
                //            case 1:
                //                fragment = new ReadFragment();
                //                break;
                //            case 2:
                //                fragment = new HelpFragment();
                //                break;

                default:
                    break;
            }

            if (fragment != null) {

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                getActionBar().setTitle(mNavigationDrawerItemTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        }

    }
}

