package com.example.dungeongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    private Button btnContinue;
    private ImageView character1, character2, character3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        btnContinue = findViewById(R.id.btnContinue);
        character1 = findViewById(R.id.ibCharacter1);
        character2 = findViewById(R.id.ibCharacter2);
        character3 = findViewById(R.id.ibCharacter3);

        // Initially, disable the "Continue" button
        btnContinue.setEnabled(false);

        // Add OnClickListener to each character image
        character1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle character selection logic here
                // Enable the "Continue" button
                btnContinue.setEnabled(true);
            }
        });

        character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle character selection logic here
                // Enable the "Continue" button
                btnContinue.setEnabled(true);
            }
        });

        character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle character selection logic here
                // Enable the "Continue" button
                btnContinue.setEnabled(true);
            }
        });

        // Continue button click handler
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    // Implement validation logic for name input and difficulty selection.

    private boolean isInputValid() {
        // Implement validation logic and return true if everything is valid, false otherwise.
        return true;
    }
}
