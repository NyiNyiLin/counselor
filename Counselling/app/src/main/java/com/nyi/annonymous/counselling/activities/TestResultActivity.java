package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestResultActivity extends AppCompatActivity {

    private static final String ARG_RESULT = "result";

    private int result;

    @BindView(R.id.tv_test_result_test)
    TextView tvResult;

    @BindView(R.id.tv_test_result_score)
    TextView tvScore;

    @BindView(R.id.btn_test_register)
    Button btnRegister;

    @BindView(R.id.tv_test_result_back)
    TextView tvBack;

    public static Intent newIntnet(int result){
        Intent intent = new Intent(Counselling.getContext(), TestResultActivity.class);
        intent.putExtra(ARG_RESULT, result);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        final Intent intent = getIntent();
        result = intent.getIntExtra(ARG_RESULT, 0);

        switch (result){
            case 0:
                tvScore.setText("100 %");
                tvResult.setText("သင့္စိတ္အေျခအေနအလြန္ေကာင္းမြန္ပါသည္\n" +
                        " အႀကံေပးသူမ်ားနွင့္ ေဆြးေနြးနိုင္ရန္ အခုပ ဲRegister လုပ္ထားလိုက္ပါ\n" +
                        "သင့္အခက္အခဲျဖစ္လာလ်ွင္ ကူညီရန္ က်ြန္ုပ္တို႔ အသင့္ရွိေနပါတယ္");
                break;
            case  1:
                tvScore.setText("75 %");
                tvResult.setText("သင့္စိတ္အေျခအေနသည္ အေကာင္းဘက္၌ရွိသည္\n" +
                        "အႀကံေပးသူမ်ားနွင့္ ေဆြးေနြးနိုင္ရန္ အခုပ ဲRegister လုပ္ထားလိုက္ပါ \n" +
                        "သင့္အခက္အခဲျဖစ္လာလ်ွင္ ကူညီရန္ က်ြန္ုပ္တို႔ အသင့္ရွိေနပါတယ္\n");
                break;
            case 2:
                tvScore.setText("50 %");
                tvResult.setText("သင့္စိတ္အေျခအေနသည္ ပံုမွန္အဆင့္သာရွိသည္\n" +
                        " ပိုမိုေကာင္းမြန္ရန္ Register လုပ္ထား၍ အခုပဲ ဝင္ေဆြးေနြးၾကည့္လိုက္ပါ\n" +
                        " ပိုေကာင္းလာတာကို သင္ခံစားမိမွာပါ\n");
                break;
            case 3:
                tvScore.setText("25 %");
                tvResult.setText("သင့္စိတ္အေျခအေန အသင့္အတင့္သာရွိသည္\n" +
                        "ပိုမိုေကာင္းမြန္ရန္ Register လုပ္ထား၍ အခုပဲ ဝင္ေဆြးေနြးၾကည့္လိုက္ပါ\n" +
                        " ပိုေကာင္းလာတာကို သင္ခံစားမိမွာပါ\n");
                break;
            case 4:
                tvScore.setText("10 %");
                tvResult.setText("သင့္စိတ္အေျခအေန ဆိုး႐ြားေနသည္ \n" +
                        "သင့္ကို ကူညီနိုင္ရန္ အခုပဲ Register လုပ္လိုက္ပါ \n" +
                        "အႀကံေပးက်ြမ္းက်င္သူမွ သင္အခက္အခဲမ်ားကို ကူညီေျဖရွင္းေပးမွာပါ\n");
                break;
            case 5:
                tvScore.setText("5 %");
                tvResult.setText("သင့္စိတ္အေျခအေန အရမ္းဆိုး႐ြားေနပါသည္ \n" +
                        "မပူပါနဲ႔ သင့္ကို ကူညီရန္ က်ြန္ုပ္တို႔ရွိေနပါတယ္ \n" +
                        "အခုပဲ Register လုပ္ၿပီး အႀကံေပးက်ြမ္းက်င္သူနဲ႔ တိုင္ပင္လိုက္ပါ\n");
                break;
            default:
                tvScore.setText("0 %");
                tvResult.setText("သင့္စိတ္အေျခအေန အရမ္းဆိုး႐ြားေနပါသည္ \n" +
                        "မပူပါနဲ႔ သင့္ကို ကူညီရန္ က်ြန္ုပ္တို႔ရွိေနပါတယ္ \n" +
                        "အခုပဲ Register လုပ္ၿပီး အႀကံေပးက်ြမ္းက်င္သူနဲ႔ တိုင္ပင္လိုက္ပါ\n");
                break;
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), LogInActivity.class);

                startActivity(intent1);
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
