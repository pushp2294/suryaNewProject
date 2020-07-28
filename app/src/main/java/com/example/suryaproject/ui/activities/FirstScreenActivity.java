package com.example.suryaproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.suryaproject.R;
import com.example.suryaproject.ui.fragment.CeleberitiesDataFragment;
import com.example.suryaproject.ui.fragment.EmailFragment;

public class FirstScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a,  new EmailFragment())
                .replace(R.id.container_b, new CeleberitiesDataFragment())
                .commit();

    }
}
