package io.github.umangjpatel.gallop.utils.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.github.umangjpatel.gallop.databinding.QuestionListItemBinding;
import io.github.umangjpatel.gallop.models.question.Question;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private QuestionListItemBinding mItemBinding;

    public QuestionViewHolder(@NonNull QuestionListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind(Question question) {
        mItemBinding.questionLabelTextView.setText(question.getLabel());
        mItemBinding.questionTextView.setText(question.getQuestion());
        mItemBinding.questionAuthorTextView.setText(question.getAuthor());
        mItemBinding.questionDateTextView.setText(formatDate(question.getDate()));
        mItemBinding.executePendingBindings();
    }

    private String formatDate(long date) {
        Date postDate = new Date(date);
        SimpleDateFormat formatter
                = new SimpleDateFormat("E, MMM dd, yyyy", Locale.ENGLISH);
        return formatter.format(postDate);
    }
}
