package com.onoh.mvplogin01.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.onoh.mvplogin01.Presenter.IUserPresenter;
import com.onoh.mvplogin01.Presenter.UserPresenter;
import com.onoh.mvplogin01.R;
import com.onoh.mvplogin01.View.ILoginView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ILoginView {

    String nomorTelepon;
    @BindView(R.id.et_nomorTelepon)
    TextInputEditText etNomorTelepon;
    @BindView(R.id.btn_nomorTelepon)
    Button btn_nomorTelepon;
    @BindView(R.id.pb_loading_login_number)
    ProgressBar pb_loading;
    IUserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sendNumber();
        //init presenter
        userPresenter = new UserPresenter(this);

    }


    private void sendNumber(){
        btn_nomorTelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPresenter.onNumberLogin(Objects.requireNonNull(etNomorTelepon.getText()).toString());
            }
        });


    }

    @Override
    public void onLoginResult(String message,int status) {
        if(status == 201){
            Intent intent_pin = new Intent(MainActivity.this, PinActivity.class);
            intent_pin.putExtra("loginNomorTelepon",nomorTelepon = Objects.requireNonNull(etNomorTelepon.getText()).toString());
            startActivity(intent_pin);
        }else if(message.equals("Nomor Telepon tidak boleh kosong")){
            etNomorTelepon.setError("Tidak boleh kosong");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onShowLoading() {
        pb_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        pb_loading.setVisibility(View.INVISIBLE);
    }
}
