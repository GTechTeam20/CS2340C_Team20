package com.example.dungeongame.viewmodels;

import android.graphics.Color;
import android.widget.ImageButton;

import com.example.dungeongame.R;

public class ConfigurationViewModel {
    private ImageButton selectedImageButton;

    public boolean isInputValid(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public void onImageButtonClick(ImageButton imageButton) {
        if (selectedImageButton != null) {
            selectedImageButton.setColorFilter(Color.TRANSPARENT); // Reset the tint
        }
        imageButton.setColorFilter(R.color.purple_500);
        selectedImageButton = imageButton;
    }
}
