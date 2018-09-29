package io.github.umangjpatel.gallop.questions.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.ActivityQuestionDetailBinding;
import io.github.umangjpatel.gallop.utils.adapters.recyclerview.AnswerListAdapter;

public class QuestionDetailActivity extends AppCompatActivity {

    private static final String EXTRA_QUESTION_DETAIL =
            QuestionDetailActivity.class.getSimpleName() + ".extra_question_detail",
            KEY_QUESTION_DETAIL = "key_question_detail";

    private ActivityQuestionDetailBinding mQuestionDetailBinding;
    private QuestionDetailViewModel mQuestionDetailViewModel;

    private AnswerListAdapter mAnswerListAdapter;

    private String mQuestionKey;

    public static Intent newIntent(Context packageContext, String questionKey) {
        Intent intent = new Intent(packageContext, QuestionDetailActivity.class);
        intent.putExtra(EXTRA_QUESTION_DETAIL, questionKey);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_detail);
        mQuestionDetailBinding.setLifecycleOwner(this);
        mQuestionDetailViewModel = ViewModelProviders.of(this).get(QuestionDetailViewModel.class);
        mQuestionKey = savedInstanceState != null ? savedInstanceState.getString(KEY_QUESTION_DETAIL) : getIntent().getStringExtra(EXTRA_QUESTION_DETAIL);
        if (mQuestionKey != null) {
            mQuestionDetailViewModel.setDatabaseReference(mQuestionKey);
        }
        mQuestionDetailViewModel
                .getQuestionLiveData()
                .observe(this, question -> {
                    if (question != null) {
                        setTitle(question.getLabel());
                        mQuestionDetailBinding.detailQuestionAuthorTextView
                                .setText(getString(R.string.question_author, question.getAuthor()));
                        mQuestionDetailBinding.questionDetailTextView.setText(question.getQuestion());
                    }
                });

        mQuestionDetailBinding.answersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQuestionDetailViewModel
                .getAnswersLiveData()
                .observe(this, answers -> {
                    if (answers != null) {
                        if (mAnswerListAdapter == null) {
                            mAnswerListAdapter = new AnswerListAdapter(answers);
                            mQuestionDetailBinding.answersRecyclerView.setAdapter(mAnswerListAdapter);
                        } else {
                            mAnswerListAdapter.setAnswers(answers);
                            mAnswerListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_QUESTION_DETAIL, mQuestionKey);
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
