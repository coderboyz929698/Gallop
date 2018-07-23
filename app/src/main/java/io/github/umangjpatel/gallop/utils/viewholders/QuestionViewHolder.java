package io.github.umangjpatel.gallop.utils.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import io.github.umangjpatel.gallop.databinding.QuestionListItemBinding;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private QuestionListItemBinding mItemBinding;

    public QuestionViewHolder(@NonNull QuestionListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }
}
