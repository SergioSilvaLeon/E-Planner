package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.tecnologiasintech.e_planner.adapter.AdapterViewPager;
import com.tecnologiasintech.e_planner.R;

public class MainActivity extends AppCompatActivity {

    private AdapterViewPager mAdapterViewPager;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Costume Tablayout for main view.
        setTabLayout();

        // Create Costume Adapter for View Pager
        setViewPager();

    }

    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

         mAdapterViewPager = new AdapterViewPager(
                 getSupportFragmentManager(),
                 mTabLayout.getTabCount()
         );

         mViewPager.setAdapter(mAdapterViewPager);

         // Enables Swiping
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setTabLayout() {

        // Instanciate a Tab Layout
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        // We also asign a centered gravity.
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Add "Fixed Mode" so  all the tabs are the same size.
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //Added two tabs to tabLayout
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_sign_out){
            userSignOut();
        }

        if (item.getItemId() == R.id.action_add_user){
            addUser();
        }

        if (item.getItemId() == R.id.action_change_password){
            changeUserPassword();
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeUserPassword() {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    private void addUser() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    private void userSignOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
