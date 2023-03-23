package id.sandri.joborder.ListMachineBM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import id.sandri.joborder.ListMachineSL.MachineSLActivity;
import id.sandri.joborder.R;

public class MachineBMActivity extends AppCompatActivity {
    final Fragment fragmentBag1 = new BM1Fragment();
    final Fragment fragmentBag2 = new BM2Fragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentBag1;

    ChipNavigationBar bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_bm);

        bottomNav = findViewById(R.id.bottom_navbar_bm);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.bm1, true);
        }

        fm.beginTransaction().add(R.id.frame_container_bm, fragmentBag2, "2").hide(fragmentBag2).commit();
        fm.beginTransaction().add(R.id.frame_container_bm, fragmentBag1, "1").commit();

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;

                switch (i){
                    case R.id.bm1:
                        fm.beginTransaction().hide(active).show(fragmentBag1).commit();
                        active = fragmentBag1;
                        break;
                    case R.id.bm2:
                        fm.beginTransaction().hide(active).show(fragmentBag2).commit();
                        active = fragmentBag2;
                        break;
                }
            }
        });
    }
}
