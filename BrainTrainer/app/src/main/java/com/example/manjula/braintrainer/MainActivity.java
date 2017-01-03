package com.example.manjula.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> answers =new ArrayList<Integer>(4);
    int  answerIndex;
    TextView question;
    int correctAttempts=0;
    int allAttempts=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
         question = (TextView) findViewById(R.id.questionText);
        resetQuestion();


        final TextView timeView= (TextView) findViewById(R.id.timeTextVIew);

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            timeView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                timeView.setText(String.valueOf(0)+"s");
            }
        }.start();
        //


    }


    public void tapped(View view){
        allAttempts++;
        TextView reply= (TextView) findViewById(R.id.resultTextView);

        Button b = (Button)view;
        String buttonText = b.getText().toString();
        if (buttonText.equals(Integer.toString(answers.get(answerIndex)))){
            correctAttempts++;
             reply.setText("Correct!");
        }else{
            reply.setText("Wrong!");
        }
        resetQuestion();

    }

    public  void  resetQuestion(){
        TextView scoreText= (TextView) findViewById(R.id.scoreTextView);
        scoreText.setText(correctAttempts+"/"+allAttempts);
        Random rand = new Random();

        int  a = rand.nextInt(10);
        int  b = rand.nextInt(10);

        answerIndex = rand.nextInt(4);

        for(int i=0; i<4;i++){
            if(i==answerIndex){
                answers.add(i,a+b);

            }else{
                int wrongAnswer=rand.nextInt(20);
                if(wrongAnswer == (a+b)){
                    answers.add(i,a+b-1);
                }else{
                    answers.add(i,wrongAnswer);
                }
            }

        }


        Button button1= (Button) findViewById(R.id.button00);
        Button button2= (Button) findViewById(R.id.button01);
        Button button3= (Button) findViewById(R.id.button10);
        Button button4= (Button) findViewById(R.id.button11);

        assert button1 != null;
        button1.setText(""+answers.get(0));
        button2.setText(""+answers.get(1));
        button3.setText(""+answers.get(2));
        button4.setText(""+answers.get(3));

        question.setText(a+"+"+b);

    }
}
