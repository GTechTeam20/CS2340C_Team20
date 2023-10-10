package com.example.dungeongame;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.ImageButton;

import com.example.dungeongame.viewmodels.ConfigurationViewModel;
import com.example.dungeongame.views.NewActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void inputChecker_isTrue() {
        ConfigurationViewModel vm = new ConfigurationViewModel();
        assertEquals(true, vm.isInputValid("asd"));
        assertEquals(true, vm.isInputValid("123test"));
    }

    @Test
    public void inputChecker_isFalse() {
        ConfigurationViewModel vm = new ConfigurationViewModel();
        assertEquals(false, vm.isInputValid(""));
        assertEquals(false, vm.isInputValid("   "));
    }



}