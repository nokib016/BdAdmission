package com.aos.bdadmission.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.aos.bdadmission.BackgroudTask.AppBackground;
import com.aos.bdadmission.Fragment.Favourite;
import com.aos.bdadmission.Fragment.Private;
import com.aos.bdadmission.Fragment.Public;
import bdadmission.R;
import com.github.clans.fab.FloatingActionMenu;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private ImageView noticefav;

    private FloatingActionMenu fam;
    private com.github.clans.fab.FloatingActionButton fabEdit, fabDelete, fabAdd;

    Intent intent;


    /*ArrayList<Item> publicVersityList=new ArrayList<>();
    ArrayList<Item> privateVersityList=new ArrayList<>();
    ArrayList<Item> favouriteVersityList=new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        intent=new Intent(this, AppBackground.class);
        startService(intent);
        noticefav = (ImageView) findViewById(R.id.notifyid);
        fabAdd = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);
        fabDelete = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab3);
        fabEdit = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DateNotice.class));
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ResultNotice.class));
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OtherNotice.class));
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        noticefav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,All_Notice.class));
            }
        });

    }

    @Override
    protected void onStop() {
        stopService(intent);

        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new Public();
            }else if(position==1){
                return new Private();

            }else{
                return new Favourite();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Public";
                case 1:
                    return "Private";
                case 2:
                    return "Favourite";
            }
            return null;
        }
    }
}
