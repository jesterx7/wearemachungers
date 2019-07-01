package com.machungapp.user.wearemachungers.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.machungapp.user.wearemachungers.Fragment.AgendaFragment;
import com.machungapp.user.wearemachungers.Fragment.BeritaFragment;
import com.machungapp.user.wearemachungers.Fragment.FAQFragment;
import com.machungapp.user.wearemachungers.Fragment.InformasiFragment;
import com.machungapp.user.wearemachungers.Fragment.KeuanganFragment;
import com.machungapp.user.wearemachungers.Fragment.KontakFragment;
import com.machungapp.user.wearemachungers.Fragment.LifeAtMachungFragment;
import com.machungapp.user.wearemachungers.Fragment.NewsletterFragment;
import com.machungapp.user.wearemachungers.R;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_bot_home:
                            Fragment fragmentHome = new BeritaFragment();
                            FragmentManager fragmentManagerHome = getFragmentManager();
                            FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
                            fragmentTransactionHome.replace(R.id.content_frame_user, fragmentHome);
                            fragmentTransactionHome.addToBackStack("berita");
                            fragmentTransactionHome.commit();
                            return true;
                        case R.id.nav_bot_agenda:
                            Fragment fragmentAgenda = new AgendaFragment();
                            FragmentManager fragmentManagerAgenda = getFragmentManager();
                            FragmentTransaction fragmentTransactionAgenda = fragmentManagerAgenda.beginTransaction();
                            fragmentTransactionAgenda.replace(R.id.content_frame_user, fragmentAgenda);
                            fragmentTransactionAgenda.addToBackStack("tag");
                            fragmentTransactionAgenda.commit();
                            return true;
                        case R.id.nav_bot_contact:
                            Fragment fragmentContact = new KontakFragment();
                            FragmentManager fragmentManagerContact = getFragmentManager();
                            FragmentTransaction fragmentTransactionContact = fragmentManagerContact.beginTransaction();
                            fragmentTransactionContact.replace(R.id.content_frame_user, fragmentContact);
                            fragmentTransactionContact.addToBackStack("tag");
                            fragmentTransactionContact.commit();
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);
        TextView username = hView.findViewById(R.id.nav_name);
        TextView email = hView.findViewById(R.id.nav_email);
        username.setText(SaveSharedPreference.getUserName(getApplicationContext()));
        email.setText(SaveSharedPreference.getNim(getApplicationContext()) + "@student.machung.ac.id");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        Fragment fragment = new BeritaFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_user, fragment);
        fragmentTransaction.addToBackStack("berita");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
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

        if (id == R.id.nav_berita_user) {
            Fragment fragment = new BeritaFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("berita");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_newsletter_user) {
            Fragment fragment = new NewsletterFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_agenda_user) {
            Fragment fragment = new AgendaFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_profil_user) {
            Fragment fragment = new InformasiFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_faq_user) {
            Fragment fragment = new FAQFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_life_at_user) {
            Fragment fragment = new LifeAtMachungFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        }  else if (id == R.id.nav_contact_user) {
            Fragment fragment = new KontakFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_academic) {

        } else if (id == R.id.nav_finance) {
            Fragment fragment = new KeuanganFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_user, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_student_information) {

        } else if (id == R.id.nav_sign_out) {
            SaveSharedPreference.setUserName(getApplicationContext(), "");
            SaveSharedPreference.setPassword(getApplicationContext(), "");
            SaveSharedPreference.setNim(getApplicationContext(), "");
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
