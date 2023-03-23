package id.sandri.joborder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.sandri.joborder.ApiHelper.BaseApiService;
import id.sandri.joborder.ApiHelper.UtilsApiRegister;
import id.sandri.joborder.Model.ApiError;
import id.sandri.joborder.Model.Register;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements Callback<Register> {
    EditText etUsername, etPassword, etConfirmPassword, etEmail, etNo_hp, etDepartmen;
    Button btnRegister;
    Context mContext;
    BaseApiService mApiService;
    SweetAlertDialog sweetAlertDialog;
    CheckBox showPassword;
    TextView tvLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etEmail = findViewById(R.id.etEmail);
        etNo_hp = findViewById(R.id.etHandphone);
        etDepartmen = findViewById(R.id.etDepartmen);
        tvLogin = findViewById(R.id.tvLogin);
        showPassword = findViewById(R.id.cb_showPassword);
        btnRegister = findViewById(R.id.btnRegister);

        mContext = this;
        mApiService = UtilsApiRegister.getApiService();


        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username, password, confirmPass, email, no_hp, departmen;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                confirmPass = etConfirmPassword.getText().toString();
                email = etEmail.getText().toString();
                no_hp = etNo_hp.getText().toString();
                departmen = etDepartmen.getText().toString();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterActivity.this, "Please Input Your Username...", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(password) || password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Please Input Your Password...", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Please Input Your Email...", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(no_hp)){
                    Toast.makeText(RegisterActivity.this, "Please Input Your Phone Number...", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(departmen)){
                    Toast.makeText(RegisterActivity.this, "Please Input Your Departmen...", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!password.equals(confirmPass)){
                    Toast.makeText(RegisterActivity.this, "Password and Confirm Password Does'nt Matching...", Toast.LENGTH_SHORT).show();
                    return;
                }

                requestRegister();
                sweetAlertDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                sweetAlertDialog.setTitleText("Please Wait");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sweetAlertDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                sweetAlertDialog.setTitleText("Please Wait");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                sweetAlertDialog.dismissWithAnimation();
            }
        });
    }

    private void requestRegister(){
        final String username, password, email, no_hp, departmen;
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        no_hp = etNo_hp.getText().toString();
        departmen = etDepartmen.getText().toString();

        try {
            JSONObject object = new JSONObject();
            object.put("username", username);
            object.put("password", password);
            object.put("email", email);
            object.put("no_hp", no_hp);
            object.put("departmen", departmen);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), object.toString());
            Call<Register> userCall = mApiService.registerRequest(requestBody);
            userCall.enqueue(this);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(@NotNull Call<Register> call, Response<Register> response) {
        if (response.isSuccessful()){
            sweetAlertDialog.dismissWithAnimation();
            new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Register Succesfull")
                    .setContentText("You Can Login Now")
                    .setConfirmText("Confirm")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
            etUsername.setText("");
            etPassword.setText("");
            etConfirmPassword.setText("");
            etEmail.setText("");
            etNo_hp.setText("");
            etDepartmen.setText("");

        }else if (response.code() == 400){
            assert response.errorBody() != null;
            ApiError message = new Gson().fromJson(response.errorBody().charStream(), ApiError.class);
            Toast.makeText(RegisterActivity.this, "" + message.getMessage(), Toast.LENGTH_LONG).show();
            sweetAlertDialog.dismissWithAnimation();
        }
        else {
            Toast.makeText(RegisterActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
            sweetAlertDialog.dismissWithAnimation();
        }
    }

    @Override
    public void onFailure(@NotNull Call<Register> call, @NotNull Throwable t) {
        if (t instanceof IOException) {
            Toast.makeText(RegisterActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            sweetAlertDialog.dismissWithAnimation();
        }else {
            Toast.makeText(RegisterActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
            sweetAlertDialog.dismissWithAnimation();
        }
    }
}