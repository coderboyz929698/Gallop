package io.github.umangjpatel.gallop.utils.adapters.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.github.umangjpatel.gallop.databinding.QuestionListItemBinding;
import io.github.umangjpatel.gallop.utils.viewholders.QuestionViewHolder;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionListItemBinding itemBinding = QuestionListItemBinding
                .inflate(inflater, parent, false);
        return new QuestionViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
