package com.project.geekahr.ricoproyecto.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.project.geekahr.ricoproyecto.R;

public class SettingActivity extends AppCompatActivity {


    MainActivity Login = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Login.goLoginScreen();

    }
}
