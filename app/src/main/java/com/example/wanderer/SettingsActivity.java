package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.content.SharedPreferences;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Dropdown za gradove
        Spinner gradoviSpinner = findViewById(R.id.gradoviSpinner);
        ArrayAdapter<CharSequence> gradoviAdapter = ArrayAdapter.createFromResource(this,
                R.array.gradovi_array, android.R.layout.simple_spinner_item);
        gradoviAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradoviSpinner.setAdapter(gradoviAdapter);

        // Dropdown za opstine
        Spinner opstineSpinner = findViewById(R.id.opstineSpinner);
        ArrayAdapter<CharSequence> opstineAdapter = ArrayAdapter.createFromResource(this,
                R.array.opstine_array, android.R.layout.simple_spinner_item);
        opstineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opstineSpinner.setAdapter(opstineAdapter);

        // Dark mode switch
        Switch darkModeSwitch = findViewById(R.id.switch_dark_mode);
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettingsPref", MODE_PRIVATE);
        boolean isDarkModeOn = sharedPreferences.getBoolean("IsDarkModeOn", false);
        darkModeSwitch.setChecked(isDarkModeOn);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("IsDarkModeOn", isChecked);
            editor.apply();

            // Dodaj logiku za dark mode kad uradimo UI i Ajlin design !!!!!!!!!!
        });

        // Exit dugme za settings
        Button exitSettingsButton = findViewById(R.id.button_exit_settings);
        exitSettingsButton.setOnClickListener(v -> startMain());
    }
    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}