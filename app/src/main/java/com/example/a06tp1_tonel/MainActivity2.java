package com.example.a06tp1_tonel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private List<Integer> images = new ArrayList<>(Arrays.asList(R.drawable.camel, R.drawable.coala, R.drawable.fox, R.drawable.lion, R.drawable.monkey, R.drawable.wolf, R.drawable.camel, R.drawable.coala, R.drawable.fox, R.drawable.lion, R.drawable.monkey, R.drawable.wolf));
    private ImageButton[] imageButtons = new ImageButton[9];

    private int cardBack = R.drawable.blackrec;
    private int clicked = 0;
    private boolean turnOver = false;
    private int lastClicked = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button backButton = findViewById(R.id.backButton);
        Button restartButton = findViewById(R.id.restartButton);

        imageButtons[0] = findViewById(R.id.imageButton1);
        imageButtons[1] = findViewById(R.id.imageButton2);
        imageButtons[2] = findViewById(R.id.imageButton3);
        imageButtons[3] = findViewById(R.id.imageButton4);
        imageButtons[4] = findViewById(R.id.imageButton5);
        imageButtons[5] = findViewById(R.id.imageButton6);
        imageButtons[6] = findViewById(R.id.imageButton7);
        imageButtons[7] = findViewById(R.id.imageButton8);
        imageButtons[8] = findViewById(R.id.imageButton9);

        Collections.shuffle(images);

        for (int i = 0; i < 9; i++) {
            imageButtons[i].setBackgroundResource(cardBack);
            final int finalI = i;
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!turnOver) {
                        imageButtons[finalI].setImageResource(images.get(finalI));
                        if (clicked == 0) {
                            lastClicked = finalI;
                        }
                        clicked++;
                    }

                    if (clicked == 2) {
                        turnOver = true;
                        if (imageButtons[finalI].getDrawable().getConstantState().equals(imageButtons[lastClicked].getDrawable().getConstantState())) {
                            imageButtons[finalI].setClickable(false);
                            imageButtons[lastClicked].setClickable(false);
                            turnOver = false;
                            clicked = 0;
                        }
                        else {
                            flipCardsBack();
                        }
                    }
                }
            });
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        clicked = 0;
        turnOver = false;
        lastClicked = -1;
        Collections.shuffle(images);
        for (int i = 0; i < 9; i++) {
            imageButtons[i].setImageResource(cardBack);
            imageButtons[i].setClickable(true);
        }
    }

    private void flipCardsBack() {
        imageButtons[lastClicked].setImageResource(cardBack);
        imageButtons[clicked - 1].setImageResource(cardBack);
        clicked = 0;
        turnOver = false;
    }
}