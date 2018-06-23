package com.example.hp.tictactoe;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class TTTButton extends AppCompatButton {

   private int player=MainActivity.NO_PLAYER;

    public TTTButton(Context context){
        super(context);

    }

    public void setPlayer(int player){
        this.player=player;
        if(player==MainActivity.PLAYER_X){
            setText("X");
            setTextSize(28);
        }
        else if(player==MainActivity.PLAYER_O){
            setText("O");
            setTextSize(28);
        }
       setEnabled(false);
    }

    public int getPlayer(){
        return this.player;
    }

    public boolean isEmpty(){
        return this.player==MainActivity.NO_PLAYER;
    }
}
