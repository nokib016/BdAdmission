package com.aos.bdadmission.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aos.bdadmission.BackgroudTask.AppBackground;
import com.aos.bdadmission.BaseApplication.MyApplication;
import com.aos.bdadmission.Fragment.Favourite;
import com.aos.bdadmission.Fragment.Private;
import com.aos.bdadmission.Fragment.Public;
import bdadmission.R;

import com.aos.bdadmission.Interface.AdShow;
import com.github.clans.fab.FloatingActionMenu;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements AdShow{


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private ImageView noticefav;

    private FloatingActionMenu fam;
    private com.github.clans.fab.FloatingActionButton announcementNoticeFab, fabDelete, fabAdd;

    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.adContext=this;

        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);







        intent=new Intent(this, AppBackground.class);
        startService(intent);
        noticefav = (ImageView) findViewById(R.id.notifyid);
        fabAdd = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);
        fabDelete = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab3);
        announcementNoticeFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);

       /* fam.setMenuButtonColorNormal(R.color.colorPrimaryDark);
        fam.setMenuButtonColorPressed(R.color.colorPrimaryDark);
        fam.setClosedOnTouchOutside(true);*/


        announcementNoticeFab.setOnClickListener(new View.OnClickListener() {
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
    public void showAd() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(MyApplication.mInterstitialAd.isLoaded()){
                    MyApplication.mInterstitialAd.show();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        //stopService(intent);

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Close BD Admission");
        builder.setMessage("Are you sure want to close BD Admission?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int which) {
                finish();
                dialogInterface.dismiss();
            }

        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // I do not need any action here you might
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button bp = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        Button bn = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0, 0, 0);
        bp.setLayoutParams(params);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            bp.setBackground(getResources().getDrawable(R.drawable.rounded_border_color));
            bn.setBackground(getResources().getDrawable(R.drawable.rounded_border_color));

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }
}
