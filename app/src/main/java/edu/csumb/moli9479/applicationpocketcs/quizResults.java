package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class quizResults extends AppCompatActivity {
    @BindView(R.id.textResult) TextView textResultView;
    @BindView(R.id.quizFeedBack) TextView quizFeedbackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        ButterKnife.bind(this);
        Bundle b  = getIntent().getExtras();
        int score = b.getInt("score");
        giveQuizFeedback(score);
        textResultView.setText("Your score is " + score + " out of 5.\n");
    }

    public void giveQuizFeedback(int score){
        if(score < 3){
            quizFeedbackView.setText(getResources().getString(R.string.lowQuizScore));
        }
        else if(score >= 3 && score < 5){
            quizFeedbackView.setText(getResources().getString(R.string.midQuizScore));
        }
        else{
            quizFeedbackView.setText(getResources().getString(R.string.highQuizScore));
        }
    }
    public void playagain(View o){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
