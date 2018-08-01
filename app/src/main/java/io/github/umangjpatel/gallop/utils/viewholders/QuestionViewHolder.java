package io.github.umangjpatel.gallop.utils.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.github.umangjpatel.gallop.databinding.QuestionListItemBinding;
import io.github.umangjpatel.gallop.models.question.Question;
import io.github.umangjpatel.gallop.questions.detail.QuestionDetailActivity;

public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private QuestionListItemBinding mItemBinding;
    private Question mQuestion;

    public QuestionViewHolder(@NonNull QuestionListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind(Question question) {
        mQuestion = question;
        mItemBinding.questionLabelTextView.setText(question.getLabel());
        mItemBinding.questionTextView.setText(question.getQuestion());
        mItemBinding.questionAuthorTextView.setText(question.getAuthor());
        mItemBinding.questionDateTextView.setText(formatDate(question.getDate()));
        itemView.setOnClickListener(this);
        mItemBinding.executePendingBindings();
    }

    private String formatDate(long date) {
        Date postDate = new Date(date);
        SimpleDateFormat formatter
                = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
        return formatter.format(postDate);
    }

    @Override
    public void onClick(View v) {
        mItemBinding
                .getRoot()
                .getContext()
                .startActivity(QuestionDetailActivity
                        .newIntent(mItemBinding.getRoot().getContext(),
                                mQuestion.getKey()));
    }
}
