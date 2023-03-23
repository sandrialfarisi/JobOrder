package id.sandri.joborder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.sandri.joborder.ApiHelper.BaseApiService;
import id.sandri.joborder.Model.User;
import id.sandri.joborder.ApiHelper.UtilsApiSignIn;
import id.sandri.joborder.utils.LoginSession;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<User> {
    EditText etUsername, etPassword;
    Button btnLogin;
    String token;
    Context mContext;
    BaseApiService mApiService;
    LoginSession loginSession;
    SweetAlertDialog sweetAlertDialog;
    CheckBox showPassword;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        showPassword = findViewById(R.id.cb_showPassword);
        tvRegister = findViewById(R.id.tvRegister);

        mContext = this;
        mApiService = UtilsApiSignIn.getApiService();
        loginSession = new LoginSession(mContext);

        if (loginSession.getSPSudahLogin()){
            startActivity(new Intent(MainActivity.this, DashboardActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username, password;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(MainActivity.this, "Please Input Your Username...", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Please Input Your Password...", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestLogin();
                sweetAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                sweetAlertDialog.setTitleText("Please Wait");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod .getInstance());
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sweetAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                sweetAlertDialog.setTitleText("Please Wait");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                sweetAlertDialog.dismissWithAnimation();
            }
        });

    }

    private void requestLogin(){
        final String username, password;
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        try {
            JSONObject object = new JSONObject();
            object.put("username", username);
            object.put("password", password);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), object.toString());
            Call<User> userCall = mApiService.getUser("", requestBody);
            userCall.enqueue(this);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(@NotNull Call<User> call, Response<User> response) {

        if (response.isSuccessful()){
            sweetAlertDialog.dismissWithAnimation();
            assert response.body() !=null;
            token = response.body().getToken();
            Log.d("token : ", token);
            loginSession.saveSPString(LoginSession.SP_TOKEN, token);
            loginSession.saveSPBoolean(LoginSession.SP_SUDAH_LOGIN, true);
            Intent intent = new Intent(mContext, DashboardActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(MainActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
            sweetAlertDialog.dismissWithAnimation();
        }
    }

    @Override
    public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
        if (t instanceof IOException) {
            Toast.makeText(MainActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            sweetAlertDialog.dismissWithAnimation();
        }else {
            Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
            sweetAlertDialog.dismissWithAnimation();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

}
