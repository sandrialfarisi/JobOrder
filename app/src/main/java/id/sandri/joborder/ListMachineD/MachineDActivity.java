package id.sandri.joborder.ListMachineD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import id.sandri.joborder.R;

public class MachineDActivity extends AppCompatActivity {
    final Fragment fragmentDry1 = new D1Fragment();
    final Fragment fragmentDry2 = new D2Fragment();
    final Fragment fragmentDry3 = new D3Fragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentDry1;

    ChipNavigationBar bottomNav;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_d);

        bottomNav = findViewById(R.id.bottom_navbar_d);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.d1, true);
        }

        fm.beginTransaction().add(R.id.frame_container_d, fragmentDry3, "3").hide(fragmentDry3).commit();
        fm.beginTransaction().add(R.id.frame_container_d, fragmentDry2, "2").hide(fragmentDry2).commit();
        fm.beginTransaction().add(R.id.frame_container_d, fragmentDry1, "1").commit();


        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i) {
                    case R.id.d1:
                        fm.beginTransaction().hide(active).show(fragmentDry1).commit();
                        active = fragmentDry1;
                        break;
                    case R.id.d2:
                        fm.beginTransaction().hide(active).show(fragmentDry2).commit();
                        active = fragmentDry2;
                        break;
                    case R.id.d3:
                        fm.beginTransaction().hide(active).show(fragmentDry3).commit();
                        active = fragmentDry3;
                        break;
                }
            }
        });
    }
}
