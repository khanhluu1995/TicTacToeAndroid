package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Integer> player1 = new ArrayList<>();
    List<Integer> player2 = new ArrayList<>();
    int[][] winCondition = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,8}};
    boolean playable = true;
    boolean isRedTurn = true;
    boolean redWin = false;
    boolean yellowWin = false;
    int turnCount = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.i("check","winCon length" + winCondition.length);
    }

    public void appearToScene(View view){
        turnCount--;
        ImageView imageView = (ImageView) view;
        playerControlStatement(imageView);
        imageView.animate().alpha(1).setDuration(500);
        checkWinningCondition();
        if(turnCount==0)
            Log.i("draw","No one win, both are stupid...");
    }

    private void checkWinningCondition() {
        if(redWin) {
            Toast.makeText(this, "Red has won the game", Toast.LENGTH_LONG).show();
            playable = false;
        }
        else if (yellowWin) {
            Toast.makeText(this, "Yellow has won the game", Toast.LENGTH_LONG).show();
            playable = false;
        }
    }

    private void playerControlStatement(ImageView imageView) {

        if(isRedTurn && checkAvailability(imageView)) {
            imageView.setImageResource(R.drawable.red);
            player1.add(Integer.parseInt(imageView.getTag().toString()));
            for(int i = 0; i < winCondition.length;i++){
                int p1Count = 0;
                for(int num : player1){
                    for(int j : winCondition[i]){
                        if(num == j)
                            p1Count++;
                        if(p1Count == 3){
                            redWin = true;
                        }
                    }
                }
            }
            isRedTurn = false;
        }
        else if(!isRedTurn && checkAvailability(imageView)){
            imageView.setImageResource(R.drawable.yellow);
            player2.add(Integer.parseInt(imageView.getTag().toString()));
            for(int i = 0; i < winCondition.length;i++){
                int p2Count = 0;
                for(int num : player2){
                    for(int j : winCondition[i]){
                        if(num == j)
                            p2Count++;
                        if(p2Count == 3){
                            yellowWin = true;
                        }
                    }
                }
            }
            isRedTurn = true;
        }

        else
            return;
    }

    private boolean checkAvailability(ImageView imageView) {
        if (imageView.getDrawable() == null && playable)
            return true;
        else
            return false;

    }
}