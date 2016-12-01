package edu.csumb.moli9479.applicationpocketcs;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class quizTemplate extends AppCompatActivity {

    @BindView(R.id.txtQuestion) TextView txtQuestion;
    @BindView(R.id.timer) TextView times;
    @BindView(R.id.score) TextView scored;
    @BindView(R.id.firstOption) Button firstOption;
    @BindView(R.id.secondOption) Button secondOption;
    @BindView(R.id.thirdOption) Button thirdOption;
    int questionCounter = 1;
    CountDownTimer cTimer = null;
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    Bundle extras;
    int info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_template);
        ButterKnife.bind(this);
        OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);
        db.addPredeterminedQuestions();
        extras = getIntent().getExtras();

        if(extras != null){
            info = extras.getInt("quizType");
        }
        if(info == 1){
            quesList = db.getAlgorithmQuestions();
            Log.d("quizTemplate", "got algorithm questions");
            System.out.println(quesList);
        }
        else if(info == 2){
            quesList = db.getDatabaseQuestions();
        }
        else if(info == 3){
            quesList = db.getSoftwareDesignQuestions();
        }
        shuffleQuestions();
        printQuestionList(quesList);
        currentQ = quesList.get(qid);
        setQuestionView();
        startTimer();
    }

    @OnClick(R.id.firstOption)
    public void onFirstOptionButtonClick(){
        getAnswer(firstOption.getText().toString());
    }

    @OnClick(R.id.secondOption)
    public void onSecondOptionButtonClick(){
        getAnswer(secondOption.getText().toString());
    }

    @OnClick(R.id.thirdOption)
    public void onThirdOptionButtonClick(){
        getAnswer(thirdOption.getText().toString());
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {
            score++;
            scored.setText("Score : " + score);

        } else {
            //Player loses game.
            cTimer.cancel();
            Intent intent = new Intent(quizTemplate.this, quizResults.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
        if (questionCounter <= 5) {
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {
            cTimer.cancel();
            Intent intent = new Intent(quizTemplate.this, quizResults.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }

    }
    public void startTimer(){
        cTimer = new CountDownTimer(60000,1000){
            public void onTick(long millisUntilFinished){
                String hms = String.format(
                        "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                                .toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                                .toMinutes(millisUntilFinished)));
                times.setText(hms);
            }
            public void onFinish(){
                cTimer.cancel();
                times.setText("Time's up");
                Intent intent = new Intent(quizTemplate.this, quizResults.class);
                Bundle b = new Bundle();
                b.putInt("score", score);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        };
        cTimer.start();
    }

    private void setQuestionView() {
        questionCounter++;
        qid++;
        txtQuestion.setText(currentQ.getQUESTION());
        firstOption.setText(currentQ.getFIRSTOPTION());
        secondOption.setText(currentQ.getSECONDOPTION());
        thirdOption.setText(currentQ.getTHIRDOPTION());
    }

    public void shuffleQuestions(){
        Collections.shuffle(quesList);
    }

    public void printQuestionList(List quesList){
        for(int i = 0; i < quesList.size();i++){
            String question1 = quesList.get(i).toString();
            Log.d("Inside Quiz Template", question1);
        }
    }

}
