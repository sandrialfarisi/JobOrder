package id.sandri.joborder.ListMachineSL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import id.sandri.joborder.R;

public class MachineSLActivity extends AppCompatActivity {
    final Fragment fragmentSliting1 = new SL1Fragment();
    final Fragment fragmentSliting2 = new SL2Fragment();
    final Fragment fragmentSliting3 = new SL3Fragment();
    final Fragment fragmentSliting4 = new SL4Fragment();
    final Fragment fragmentSliting5 = new SL5Fragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentSliting1;

    ChipNavigationBar bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_sl);

        bottomNav = findViewById(R.id.bottom_navbar_sl);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.sl1, true);
        }

        fm.beginTransaction().add(R.id.frame_container_sl, fragmentSliting5, "5").hide(fragmentSliting5).commit();
        fm.beginTransaction().add(R.id.frame_container_sl, fragmentSliting4, "4").hide(fragmentSliting4).commit();
        fm.beginTransaction().add(R.id.frame_container_sl, fragmentSliting3, "3").hide(fragmentSliting3).commit();
        fm.beginTransaction().add(R.id.frame_container_sl, fragmentSliting2, "2").hide(fragmentSliting2).commit();
        fm.beginTransaction().add(R.id.frame_container_sl, fragmentSliting1, "1").commit();

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.sl1:
                        fm.beginTransaction().hide(active).show(fragmentSliting1).commit();
                        active = fragmentSliting1;
                        break;
                    case R.id.sl2:
                        fm.beginTransaction().hide(active).show(fragmentSliting2).commit();
                        active = fragmentSliting2;
                        break;
                    case R.id.sl3:
                        fm.beginTransaction().hide(active).show(fragmentSliting3).commit();
                        active = fragmentSliting3;
                        break;
                    case R.id.sl4:
                        fm.beginTransaction().hide(active).show(fragmentSliting4).commit();
                        active = fragmentSliting4;
                        break;
                    case R.id.sl5:
                        fm.beginTransaction().hide(active).show(fragmentSliting5).commit();
                        active = fragmentSliting5;
                        break;
                }
            }
        });
    }
}
