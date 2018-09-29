package io.github.umangjpatel.gallop.utils.adapters.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.umangjpatel.gallop.databinding.AnswerListItemBinding;
import io.github.umangjpatel.gallop.models.answer.Answer;
import io.github.umangjpatel.gallop.utils.viewholders.AnswerViewHolder;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

    private List<Answer> mAnswers;

    public AnswerListAdapter(List<Answer> answers) {
        mAnswers = answers;
    }

    public void setAnswers(List<Answer> answers) {
        mAnswers = answers;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AnswerListItemBinding itemBinding = AnswerListItemBinding
                .inflate(inflater, parent, false);
        return new AnswerViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        Answer answer = mAnswers.get(position);
        holder.bind(answer);
    }

    @Override
    public int getItemCount() {
        return mAnswers.size();
    }
}
