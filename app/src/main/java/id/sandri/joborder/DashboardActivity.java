package id.sandri.joborder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import id.sandri.joborder.ListMachineBM.MachineBMActivity;
import id.sandri.joborder.ListMachineD.MachineDActivity;
import id.sandri.joborder.ListMachineEX.MachineEXActivity;
import id.sandri.joborder.ListMachineP.MachinePActivity;
import id.sandri.joborder.ListMachineSL.MachineSLActivity;
import id.sandri.joborder.utils.LoginSession;

public class DashboardActivity extends AppCompatActivity {
    CardView btnMesin1, btnMesin2, btnMesin3, btnMesin4, btnMesin5, btnLogout;
    public LoginSession loginSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        loginSession = new LoginSession(this);

        btnMesin1 = findViewById(R.id.btnMesin1);
        btnMesin2 = findViewById(R.id.btnMesin2);
        btnMesin3 = findViewById(R.id.btnMesin3);
        btnMesin4 = findViewById(R.id.btnMesin4);
        btnMesin5 = findViewById(R.id.btnMesin5);
        btnLogout = findViewById(R.id.btnLogout);

        btnMesin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MachinePActivity.class);
                startActivity(intent);
            }
        });

        btnMesin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MachineDActivity.class);
                startActivity(intent);
            }
        });

        btnMesin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MachineSLActivity.class);
                startActivity(intent);
            }
        });

        btnMesin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MachineBMActivity.class);
                startActivity(intent);
            }
        });

        btnMesin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MachineEXActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStatus();
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
    public void saveStatus(){
        loginSession.saveSPBoolean(LoginSession.SP_SUDAH_LOGIN, false);
    }
}
