package com.example.dungeongame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class MainActivity extends AppCompatActivity {

    private Button btnSelectDifficulty;
    private Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelectDifficulty = findViewById(R.id.btnSelectDifficulty);
        btnStartGame = findViewById(R.id.btnStartGame);

        btnSelectDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyDialog();
            }
        });

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDifficultyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Difficulty");
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_difficulty, null);
        builder.setView(dialogView);

        final RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroup);
        final RadioButton radioEasy = dialogView.findViewById(R.id.radioEasy);
        final RadioButton radioMedium = dialogView.findViewById(R.id.radioMedium);
        final RadioButton radioHard = dialogView.findViewById(R.id.radioHard);

        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String selectedDifficulty = "Unknown"; // Default value

                if (selectedId == R.id.radioEasy) {
                    selectedDifficulty = "Easy";
                } else if (selectedId == R.id.radioMedium) {
                    selectedDifficulty = "Medium";
                } else if (selectedId == R.id.radioHard) {
                    selectedDifficulty = "Hard";
                }

                // You can now use the selectedDifficulty value as needed.
                // For example, you can store it in a variable or pass it to the game activity.
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startGame(View view) {

    }
}