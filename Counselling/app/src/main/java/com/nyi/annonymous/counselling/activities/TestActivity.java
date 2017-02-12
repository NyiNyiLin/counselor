package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.tv_question_text)
    TextView tvQuestion;

    @BindView(R.id.tv_question_title)
    TextView tvTitle;

    @BindView(R.id.tv_test_next)
    TextView tvNext;

    @BindView(R.id.tv_test_back)
    TextView tvBack;

    List<Question> quesList = new ArrayList<>();

    int position = 0;
    int resultValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this, this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
            
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindListener();

        dummyData();

        setData(quesList.get(position));
    }

    private void bindListener() {
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultValue ++;
                nextSlide();
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextSlide();
            }
        });
    }

    private void nextSlide(){
        position ++;

        if(position < quesList.size()) {
            Question question = quesList.get(position);
            setData(question);
        }else{
            Intent intent = TestResultActivity.newIntnet(resultValue);
            startActivity(intent);

            finish();
        }

    }

    private void setData(Question question){
        tvTitle.setText(question.getTitle());
        tvQuestion.setText(question.getText());
        tvNext.setText(question.getNext());
        tvBack.setText(question.getBack());
    }

    private void dummyData(){
        quesList.add(new Question("#1", "သင္အၾကံေပးေဆြးေႏြးမႈရယူသင့္တယ္ဟုသင့္သူငယ္ခ်င္းမ်ားကအၾကံျပဳဖူးပါသလား?", "ျပဳဖူးတယ္", "မျပဳဖူးပါ"));
        quesList.add(new Question("#2", "ကိုယ့္ကိုယ္ကိုယ္ေသေၾကာင္းၾကံစည္ဖို႔စဥ္းစားဖူးပါသလား?", "စဥ္းစားဖူးတယ္", "မစဥ္းစားဖူးပါ"));
        quesList.add(new Question("#3", "သင္တစ္ခုခုကိုအလြန္အၾကဴးစဲြလမ္းမႈျဖစ္ေနပါသလား?(အရက္၊မူးယစ္ေဆး၊ Game)", "စဲြလမ္းတယ္", "မစဲြလမ္းပါ"));
        quesList.add(new Question("#4", "သင့္မွာစိုးရိမ္ေၾကာင့္ၾကမႈေတြဖိစီးေနပါသလား?", "ဖိစီးေနတယ္", "မဖိစီးပါ"));
        quesList.add(new Question("#5", "သင့္အလုပ္မွာေပ်ာ္ရြင္ရဲ႕လား", "Yes", "No"));
        quesList.add(new Question("#6", "သင္သည္ယခုေလာေလာဆယ္တြင္ေဆး၀ါးကုသမႈခံယူေနသူျဖစ္ပါသလား?", "ခံယူေနတယ္", "မခံယူပါ"));

    }


}
