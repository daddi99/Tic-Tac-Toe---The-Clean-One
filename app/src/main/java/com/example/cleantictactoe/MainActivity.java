package com.example.cleantictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String currentTurn = "Circle";
    TextView turnText;
    int movesCounter = 0;
    Button topLeft,centerLeft,bottomLeft,topMid,centerMid,bottomMid,topRight,centerRight,bottomRight;
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ottiene tutte le view
        topLeft = findViewById(R.id.topLeft);
        centerLeft = findViewById(R.id.centerLeft);
        bottomLeft = findViewById(R.id.bottomLeft);
        topMid = findViewById(R.id.topMid);
        centerMid = findViewById(R.id.centerMid);
        bottomMid = findViewById(R.id.bottomMid);
        topRight = findViewById(R.id.topRight);
        centerRight = findViewById(R.id.centerRight);
        bottomRight = findViewById(R.id.bottomRight);

        buttonSound = MediaPlayer.create(this,R.raw.pop_sound);

        getSupportActionBar().hide();

        //Set the starting turn to circle
        turnText = findViewById(R.id.turnText);
        turnText.setText("Circle Turn");
    }

    public void press(View v)
    {
        Button clickedButton = (Button)v;

        //Controlla se il numero di turni abbia superato il massimo di 9 e che il bottone sia effettivamente vuoto
        if(movesCounter < 9 && clickedButton.getText().equals(""))
        {
            if(currentTurn.equals("Circle"))
            {
                buttonSound.start();

                clickedButton.setText("O");
                checkWin();

                //Se nessuno ha vinto cambia il turno e incrementa il numero di mosse
                currentTurn = "Cross";
                turnText.setText("Cross Turn");
                movesCounter++;

            }
            else if (currentTurn.equals("Cross"))
            {
                buttonSound.start();

                clickedButton.setText("X");
                checkWin();

                //Se nessuno ha vinto cambia il turno e incrementa il numero di mosse
                currentTurn = "Circle";
                turnText.setText("Circle Turn");
                movesCounter++;
            }
        }
    }

    public void checkWin()
    {
        //Controlla la colonna sinistra
        checkLine(topLeft,centerLeft,bottomLeft);
        //Colonna centrale
        checkLine(topMid,centerMid,bottomMid);
        //Colonna destra
        checkLine(topRight,centerRight,bottomRight);
        //Riga sopra
        checkLine(topLeft,topMid,topRight);
        //Riga al centro
        checkLine(centerLeft,centerMid,centerRight);
        //Riga in basso
        checkLine(bottomLeft,bottomMid,bottomRight);
        //Obliqui
        checkLine(topLeft,centerMid,bottomRight);
        checkLine(topRight,centerMid,bottomLeft);
    }

    //Metodo che controlla tutti i bottoni su una linea coincidono
    public void checkLine(Button button1, Button button2, Button button3)
    {
        //controlla che non siano vuoti e poi li confronta uno per uno
        if (!button1.getText().equals("") && button1.getText().equals(button2.getText()) && button2.getText().equals(button3.getText()))
        {
            button1.setTextColor(Color.RED);
            button2.setTextColor(Color.RED);
            button3.setTextColor(Color.RED);

            System.out.println(button1.getText() + " Won!");

            disableButtons();
        }
    }

    public void disableButtons()
    {
        topLeft.setEnabled(false);
        centerLeft.setEnabled(false);
        bottomLeft.setEnabled(false);
        topMid.setEnabled(false);
        centerMid.setEnabled(false);
        bottomMid.setEnabled(false);
        topRight.setEnabled(false);
        centerRight.setEnabled(false);
        bottomRight.setEnabled(false);
    }

    public void restartButtons()
    {
        //Pulisce i testi
        topLeft.setText("");
        centerLeft.setText("");
        bottomLeft.setText("");
        topMid.setText("");
        centerMid.setText("");
        bottomMid.setText("");
        topRight.setText("");
        centerRight.setText("");
        bottomRight.setText("");

        //Rimette i testi neri
        topLeft.setTextColor(Color.BLACK);
        centerLeft.setTextColor(Color.BLACK);
        bottomLeft.setTextColor(Color.BLACK);
        topMid.setTextColor(Color.BLACK);
        centerMid.setTextColor(Color.BLACK);
        bottomMid.setTextColor(Color.BLACK);
        topRight.setTextColor(Color.BLACK);
        centerRight.setTextColor(Color.BLACK);
        bottomRight.setTextColor(Color.BLACK);

        //Riattiva i bottoni
        topLeft.setEnabled(true);
        centerLeft.setEnabled(true);
        bottomLeft.setEnabled(true);
        topMid.setEnabled(true);
        centerMid.setEnabled(true);
        bottomMid.setEnabled(true);
        topRight.setEnabled(true);
        centerRight.setEnabled(true);
        bottomRight.setEnabled(true);

    }

    public void restartActivity(View v)
    {
        currentTurn = "Circle";
        movesCounter = 0;

        turnText.setText("Circle Turn");

        restartButtons();
    }
}