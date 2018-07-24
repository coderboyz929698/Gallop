package io.github.umangjpatel.gallop.questions.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.ActivityQuestionDetailBinding;

public class QuestionDetailActivity extends AppCompatActivity {

    private ActivityQuestionDetailBinding mQuestionDetailBinding;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, QuestionDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_detail);
        mQuestionDetailBinding.setLifecycleOwner(this);
    }
}
