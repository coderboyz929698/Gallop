package io.github.umangjpatel.gallop.utils.viewholders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.gallop.databinding.AnswerListItemBinding;
import io.github.umangjpatel.gallop.models.answer.Answer;

public class AnswerViewHolder extends RecyclerView.ViewHolder {

    private AnswerListItemBinding mItemBinding;

    public AnswerViewHolder(@NonNull AnswerListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind(Answer answer) {
        mItemBinding.answerTextView.setText(answer.getAnswer());
        mItemBinding.answerAuthorTextView.setText(answer.getAuthor());
        mItemBinding.answerDateTextView.setText(formatDate(answer.getDate()));
        mItemBinding.executePendingBindings();
    }

    private String formatDate(long date) {
        Date postDate = new Date(date);
        SimpleDateFormat formatter
                = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
        return formatter.format(postDate);
    }
}
