package com.machungapp.user.wearemachungers.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.machungapp.user.wearemachungers.Fragment.AgendaFragment;
import com.machungapp.user.wearemachungers.Fragment.BeritaFragment;
import com.machungapp.user.wearemachungers.Fragment.FAQFragment;
import com.machungapp.user.wearemachungers.Fragment.InformasiFragment;
import com.machungapp.user.wearemachungers.Fragment.KontakFragment;
import com.machungapp.user.wearemachungers.Fragment.LifeAtMachungFragment;
import com.machungapp.user.wearemachungers.Fragment.NewsletterFragment;
import com.machungapp.user.wearemachungers.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_bot_home:
                            Fragment fragmentHome = new BeritaFragment();
                            Bundle bundleHome = new Bundle();
                            bundleHome.putString("frame", "general");
                            fragmentHome.setArguments(bundleHome);
                            FragmentManager fragmentManagerHome = getFragmentManager();
                            FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
                            fragmentTransactionHome.replace(R.id.content_frame, fragmentHome);
                            fragmentTransactionHome.addToBackStack("berita");
                            fragmentTransactionHome.commit();
                            return true;
                        case R.id.nav_bot_agenda:
                            Fragment fragmentAgenda = new AgendaFragment();
                            Bundle bundleAgenda = new Bundle();
                            bundleAgenda.putString("frame", "general");
                            fragmentAgenda.setArguments(bundleAgenda);
                            FragmentManager fragmentManagerAgenda = getFragmentManager();
                            FragmentTransaction fragmentTransactionAgenda = fragmentManagerAgenda.beginTransaction();
                            fragmentTransactionAgenda.replace(R.id.content_frame, fragmentAgenda);
                            fragmentTransactionAgenda.addToBackStack("tag");
                            fragmentTransactionAgenda.commit();
                            return true;
                        case R.id.nav_bot_contact:
                            Fragment fragmentContact = new KontakFragment();
                            FragmentManager fragmentManagerContact = getFragmentManager();
                            FragmentTransaction fragmentTransactionContact = fragmentManagerContact.beginTransaction();
                            fragmentTransactionContact.replace(R.id.content_frame, fragmentContact);
                            fragmentTransactionContact.addToBackStack("tag");
                            fragmentTransactionContact.commit();
                            return true;
                        case R.id.nav_bot_sign:
                            Intent intent = new Intent(MainActivity.this, SignIn.class);
                            startActivity(intent);
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_berita);

        Fragment fragment = new BeritaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("frame", "general");
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack("berita");
        fragmentTransaction.commit();
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_berita) {
            Fragment fragment = new BeritaFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", "general");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("berita");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_newsletter) {
            Fragment fragment = new NewsletterFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", "general");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_agenda) {
            Fragment fragment = new AgendaFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", "general");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_profil) {
            Fragment fragment = new InformasiFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", "general");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_faq) {
            Fragment fragment = new FAQFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", "general");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_life_at) {
            Fragment fragment = new LifeAtMachungFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", "general");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_register) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mcis-pmb.machung.ac.id/"));
            startActivity(browserIntent);
        } else if (id == R.id.nav_sign) {
            Intent intent = new Intent(MainActivity.this, SignIn.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Fragment fragment = new KontakFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
