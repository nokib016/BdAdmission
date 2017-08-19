package com.aos.bdadmission.Activity; // ei page er code gula main page e likhte hbe

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import bdadmission.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class FavButton extends AppCompatActivity {

    private FloatingActionMenu fam;
    private FloatingActionButton fabEdit, fabDelete, fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_button);

        fabAdd = (FloatingActionButton) findViewById(R.id.fab2);
        fabDelete = (FloatingActionButton) findViewById(R.id.fab3);
        fabEdit = (FloatingActionButton) findViewById(R.id.fab1);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);

        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    showToast("Menu is opened");
                } else {
                    showToast("Menu is closed");
                }
            }
        });

        //handling each floating action button clicked
        fabDelete.setOnClickListener(onButtonClick());
       // fabEdit.setOnClickListener(onButtonClick());
        fabAdd.setOnClickListener(onButtonClick());
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FavButton.this, "njnjjj", Toast.LENGTH_SHORT).show();
                //here
               // startActivity(new Intent(FavButton.this,DateNotice.class));
            }
        });

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabAdd) {
                    showToast("Button Add clicked");
                } else if (view == fabDelete) {
                    showToast("Button Delete clicked");
                } else {
                    showToast("Button Edit clicked");
                }
                fam.close(true);
            }
        };
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
