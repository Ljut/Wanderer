package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettingsPref", MODE_PRIVATE);
        List<String> opcine = new ArrayList<>();
        for(Opcina opcina : SplashScreen.lista_opcina) {
            opcine.add(opcina.ime_opcine);
        }
        List<String> gradovi = new ArrayList<>();
        for(Grad grad : SplashScreen.lista_gradova) {
            gradovi.add(grad.ime_grada);
        }

        // Dropdown za gradove
        Spinner gradoviSpinner = findViewById(R.id.gradoviSpinner);
        ArrayAdapter<String> gradoviAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradovi);
        gradoviAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradoviSpinner.setAdapter(gradoviAdapter);
        int mojGrad = sharedPreferences.getInt("mojGrad",0);
        gradoviSpinner.setSelection(mojGrad);
        MainActivity.mojGrad = (String) gradoviSpinner.getSelectedItem();

        gradoviSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("mojGrad", position);
                editor.apply();
                MainActivity.mojGrad = (String) gradoviSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Dropdown za opstine
        Spinner opstineSpinner = findViewById(R.id.opstineSpinner);
        ArrayAdapter<String> opstineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcine);
        opstineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opstineSpinner.setAdapter(opstineAdapter);
        int mojaOpstina = sharedPreferences.getInt("mojaOpstina",0);
        opstineSpinner.setSelection(mojaOpstina);
        MainActivity.mojaOpcina = (String) opstineSpinner.getSelectedItem();
        opstineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Save the selected position to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("mojaOpstina", position);
                editor.apply();
                MainActivity.mojaOpcina = (String) opstineSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Dark mode switch
        Switch darkModeSwitch = findViewById(R.id.switch_dark_mode);
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