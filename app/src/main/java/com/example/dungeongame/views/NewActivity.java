package com.example.dungeongame.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.R;
import com.example.dungeongame.viewmodels.ConfigurationViewModel;

public class NewActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;
    private Button btnContinue;
    private ImageButton character1;
    private ImageButton character2;
    private ImageButton character3;
    private String selectedCharacter = "";
    private EditText etPlayerName;
    private RadioGroup radioGroupDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // Initialize the ViewModel
        viewModel = new ConfigurationViewModel();

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
                viewModel.onImageButtonClick(character1);
            }
        });

        character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCharacter = "pickle";
                btnContinue.setEnabled(true);
                viewModel.onImageButtonClick(character2);
            }
        });

        character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCharacter = "banana";
                btnContinue.setEnabled(true);
                viewModel.onImageButtonClick(character3);
            }
        });

        // Continue button click handler
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.isInputValid(etPlayerName.getText().toString())) {
                    int selectedRadioButtonId = radioGroupDifficulty.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedDifficulty = selectedRadioButton.getText().toString();
                    Intent intent = new Intent(NewActivity.this, GameActivity.class);
                    intent.putExtra("selectedCharacter", selectedCharacter);
                    intent.putExtra("playerName", etPlayerName.getText().toString().trim());
                    intent.putExtra("selectedDifficulty", selectedDifficulty);
                    startActivity(intent);
                }
            }
        });
    }
}
