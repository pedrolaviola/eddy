package com.pedrolaviola.user.eddy;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import com.pedrolaviola.user.eddy.fragments.SignupFragment;

public class SignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_signup, new SignupFragment())
                .commit();



    }

}