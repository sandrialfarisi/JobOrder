package id.sandri.joborder.ListMachineEX;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import id.sandri.joborder.ListMachineSL.MachineSLActivity;
import id.sandri.joborder.R;

public class MachineEXActivity extends AppCompatActivity {
    final Fragment fragmentExtru1 = new EX1Fragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentExtru1;

    ChipNavigationBar bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_ex);

        bottomNav = findViewById(R.id.bottom_navbar_ex);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.ex, true);
        }

        fm.beginTransaction().add(R.id.frame_container_ex, fragmentExtru1, "1").commit();

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                if (i == R.id.ex) {
                    fm.beginTransaction().hide(active).show(fragmentExtru1).commit();
                    active = fragmentExtru1;
                }
            }
        });

    }
}
