package io.github.umangjpatel.gallop.questions.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.ActivityAskQuestionBinding;

public class AskQuestionActivity extends AppCompatActivity {

    private ActivityAskQuestionBinding mAskQuestionBinding;
    private AskQuestionViewModel mAskQuestionViewModel;

    @NonNull
    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, AskQuestionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAskQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_ask_question);
        mAskQuestionBinding.setLifecycleOwner(this);
        mAskQuestionViewModel = ViewModelProviders.of(this).get(AskQuestionViewModel.class);
    }

    private void startPublishQuestion() {
        mAskQuestionViewModel
                .setLabel(mAskQuestionBinding.questionLabelEditText.getText().toString());
        mAskQuestionViewModel
                .setQuestion(mAskQuestionBinding.questionContentEditText.getText().toString());
        mAskQuestionViewModel.publishQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_ask_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.question_publish:
                startPublishQuestion();
                finish();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
