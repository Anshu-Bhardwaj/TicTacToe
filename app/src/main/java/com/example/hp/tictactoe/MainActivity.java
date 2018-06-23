package com.example.hp.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout rootLayout;

    public static final int PLAYER_X =1;
    public static final int PLAYER_O =0;
    public static final int NO_PLAYER =-1;

    public static final int INCOMPLETE=1;
    public static final int PLAYER_X_WON=2;
    public static final int PLAYER_O_WON=3;
    public static final int DRAW=4;

    public int currentStatus;

    public int currentPlayer;

    public static int m=3,n=4;

   public ArrayList<LinearLayout> rows;
   public TTTButton[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout=findViewById(R.id.rootLayout);

      setupBoard();
    }

    public void setupBoard(){
        currentStatus=INCOMPLETE;
        currentPlayer=PLAYER_O;
        rows=new ArrayList<>();
        board=new TTTButton[m][n];
        rootLayout.removeAllViews();

        for(int i=0;i<m;i++){
            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);

            rootLayout.addView(linearLayout);
            rows.add(linearLayout);

        }

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++) {

                TTTButton button= new TTTButton(this);
                LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);

                button.setOnClickListener(this);
                LinearLayout row=rows.get(i);
                row.addView(button);
                board[i][j]=button;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.resetItem){
            setupBoard();
        }
        else if(id==R.id.size3){
            m=3;
            n=3;
            setupBoard();
        }
        else if(id==R.id.size4){
            m=4;
            n=4;
            setupBoard();
        }
        else if(id==R.id.size5){
            m=5;
            n=5;
            setupBoard();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (currentStatus==INCOMPLETE){
            TTTButton button = (TTTButton) view;
            button.setPlayer(currentPlayer);
            checkGameStatus();
            togglePlayer();
        }

    }

    private void checkGameStatus(){
        //ROWS
    for(int i=0;i<m;i++){
        boolean rowSame=true;
        TTTButton firstButton=board[i][0];
        for(int j=0;j<m;j++){
           TTTButton button=board[i][j];
            if(button.isEmpty() || button.getPlayer()!=firstButton.getPlayer()){
                rowSame=false;
                break;

            }

        }

        if(rowSame){
            int playerWon=firstButton.getPlayer();
           updateStatus(playerWon);
           return;
        }
    }
       //COLUMNS
        for(int j=0;j<n;j++){
            boolean columnSame=true;
            TTTButton firstButton=board[0][j];
            for(int i=0;i<m;i++){
                TTTButton button=board[i][j];
                if(button.isEmpty() || button.getPlayer()!=firstButton.getPlayer()){
                    columnSame=false;
                    break;

                }

            }

            if(columnSame){
                int playerWon=firstButton.getPlayer();
                updateStatus(playerWon);
                return;
            }
        }
         //FIRST DIAGONAL
        boolean diagonalSame=true;
        TTTButton firstButton=board[0][0];
        for(int i=0;i<m;i++){
            TTTButton button=board[i][i];
            if(button.isEmpty() || button.getPlayer()!=firstButton.getPlayer()){
                diagonalSame=false;
                break;

            }

        }

        if(diagonalSame){
            int playerWon=firstButton.getPlayer();
            updateStatus(playerWon);
            return;
        }

        //SECOND DIAGONAL
        diagonalSame=true;
         firstButton=board[0][n-1];
        for(int i=0;i<m;i++){
            TTTButton button=board[i][n-i-1];
            if(button.isEmpty() || button.getPlayer()!=firstButton.getPlayer()){
                diagonalSame=false;
                break;

            }

        }

        if(diagonalSame){
            int playerWon=firstButton.getPlayer();
            updateStatus(playerWon);
            return;
        }

        //DRAW
        for(int i=0; i<m;i++) {
         for (int j=0;j<n;j++) {
             TTTButton button = board[i][j];
             if (button.isEmpty()){
                 return;
             }
         }
     }

        currentStatus=DRAW;
     Toast.makeText(this,"MATCH TIE",Toast.LENGTH_LONG).show();

    }

    public void updateStatus(int player){
        if(player==PLAYER_X){
            currentStatus=PLAYER_X_WON ;
            Toast.makeText(this,"PLAYER_X_WON",Toast.LENGTH_LONG).show();
        }
        else  if(player==PLAYER_O){
            currentStatus=PLAYER_O_WON ;
            Toast.makeText(this,"PLAYER_O_WON",Toast.LENGTH_LONG).show();
        }
    }

    public void togglePlayer(){
        if(currentPlayer==PLAYER_O){
            currentPlayer=PLAYER_X;
        }
        else{
            currentPlayer=PLAYER_O;
        }
    }
}
