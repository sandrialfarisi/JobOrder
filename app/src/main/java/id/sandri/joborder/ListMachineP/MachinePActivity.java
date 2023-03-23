package id.sandri.joborder.ListMachineP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import id.sandri.joborder.R;

public class MachinePActivity extends AppCompatActivity {
    final Fragment fragmentPrinting1 = new P1Fragment();
    final Fragment fragmentPrinting2 = new P2Fragment();
    final Fragment fragmentPrinting3 = new P3Fragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentPrinting1;

    ChipNavigationBar bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_p);

        bottomNav = findViewById(R.id.bottom_navbar_p);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.p1, true);
        }

        fm.beginTransaction().add(R.id.frame_container_p, fragmentPrinting3, "3").hide(fragmentPrinting3).commit();
        fm.beginTransaction().add(R.id.frame_container_p, fragmentPrinting2, "2").hide(fragmentPrinting2).commit();
        fm.beginTransaction().add(R.id.frame_container_p, fragmentPrinting1, "1").commit();

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.p1:
                        fm.beginTransaction().hide(active).show(fragmentPrinting1).commit();
                        active = fragmentPrinting1;
                        break;
                    case R.id.p2:
                        fm.beginTransaction().hide(active).show(fragmentPrinting2).commit();
                        active = fragmentPrinting2;
                        break;
                    case R.id.p3:
                        fm.beginTransaction().hide(active).show(fragmentPrinting3).commit();
                        active = fragmentPrinting3;
                        break;
                }
            }
        });
    }

}
