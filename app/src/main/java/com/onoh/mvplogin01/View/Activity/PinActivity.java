package com.onoh.mvplogin01.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.onoh.mvplogin01.Presenter.IUserPresenter;
import com.onoh.mvplogin01.Presenter.UserPresenter;
import com.onoh.mvplogin01.R;
import com.onoh.mvplogin01.View.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PinActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_pin)
    EditText etPin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    String getNomorTelepon, pin;
    @BindView(R.id.pb_pin)
    ProgressBar pb_pin;

    IUserPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        ButterKnife.bind(this);
        getDataNomorTelepon();

        //init presenter
        loginPresenter = new UserPresenter(this);

        //event login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin = etPin.getText().toString();
                loginPresenter.onLogin(getNomorTelepon,pin);
            }
        });

    }

    private void getDataNomorTelepon(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            getNomorTelepon = extras.getString("loginNomorTelepon");
        }
    }




    @Override
    public void onLoginResult(String message,int status) {
       if(status == 201){
           Intent intent_home = new Intent(this,HomeActivity.class);
           startActivity(intent_home);
       }else{
           Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onShowLoading() {
        pb_pin.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        pb_pin.setVisibility(View.INVISIBLE);
    }
}
