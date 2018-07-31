package io.github.umangjpatel.gallop.questions.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.ActivityQuestionDetailBinding;
import io.github.umangjpatel.gallop.models.question.Question;

public class QuestionDetailActivity extends AppCompatActivity {

    private static final String EXTRA_QUESTION_DETAIL =
            QuestionDetailActivity.class.getSimpleName() + ".extra_question_detail",
            KEY_QUESTION_DETAIL = "key_question_detail";

    private ActivityQuestionDetailBinding mQuestionDetailBinding;
    private QuestionDetailViewModel mQuestionDetailViewModel;

    private Question mQuestion;

    public static Intent newIntent(Context packageContext, Question question) {
        Intent intent = new Intent(packageContext, QuestionDetailActivity.class);
        intent.putExtra(EXTRA_QUESTION_DETAIL, question);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_detail);
        mQuestionDetailBinding.setLifecycleOwner(this);
        mQuestionDetailViewModel = ViewModelProviders.of(this).get(QuestionDetailViewModel.class);
        mQuestion = savedInstanceState != null ? (Question) savedInstanceState.getSerializable(KEY_QUESTION_DETAIL) : (Question) getIntent().getSerializableExtra(EXTRA_QUESTION_DETAIL);
        if (mQuestion != null) {
            mQuestionDetailViewModel.setDatabaseReference(mQuestion.getKey());
            setTitle(mQuestion.getLabel());
            mQuestionDetailBinding.detailQuestionAuthorTextView.setText(
                    getString(R.string.question_author, mQuestion.getAuthor()));
            mQuestionDetailBinding.questionDetailTextView.setText(mQuestion.getQuestion());
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_QUESTION_DETAIL, mQuestion);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
