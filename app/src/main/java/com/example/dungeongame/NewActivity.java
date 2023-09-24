package com.example.dungeongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    private Button btnContinue;
    private ImageView character1, character2, character3;
    private String selectedCharacter = "";
    private EditText etPlayerName;
    private RadioGroup radioGroupDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        btnContinue = findViewById(R.id.btnContinue);
        character1 = findViewById(R.id.ibCharacter1);
        character2 = findViewById(R.id.ibCharacter2);
        character3 = findViewById(R.id.ibCharacter3);
        etPlayerName = findViewById(R.id.etPlayerName);
        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);

        // Initially, disable the "Continue" button
        btnContinue.setEnabled(false);

        // Add OnClickListener to each character image
        character1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCharacter = "monkey";
                btnContinue.setEnabled(true);
            }
        });

        character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCharacter = "pickle";
                btnContinue.setEnabled(true);
            }
        });

        character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCharacter = "banana";
                btnContinue.setEnabled(true);
            }
        });

        // Continue button click handler
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = radioGroupDifficulty.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedDifficulty = selectedRadioButton.getText().toString();
                Intent intent = new Intent(NewActivity.this, GameActivity.class);
                intent.putExtra("selectedCharacter", selectedCharacter);
                intent.putExtra("playerName", etPlayerName.getText().toString().trim());
                intent.putExtra("selectedDifficulty", selectedDifficulty);
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
