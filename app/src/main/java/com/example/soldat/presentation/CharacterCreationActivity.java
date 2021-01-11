package com.example.soldat.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soldat.R;
import com.example.soldat.objects.PC;

public class CharacterCreationActivity extends AppCompatActivity {
    private PC currCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);

    }
}
