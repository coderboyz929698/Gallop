package io.github.umangjpatel.gallop.questions;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.FragmentListQuestionsBinding;
import io.github.umangjpatel.gallop.utils.adapters.recyclerview.QuestionListAdapter;

public class QuestionsListFragment extends Fragment {

    private FragmentListQuestionsBinding mListQuestionsBinding;
    private QuestionsListViewModel mQuestionsListViewModel;

    private QuestionListAdapter mQuestionListAdapter;

    public QuestionsListFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static QuestionsListFragment newInstance() {
        return new QuestionsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListQuestionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_questions, container, false);
        mQuestionsListViewModel = ViewModelProviders.of(this).get(QuestionsListViewModel.class);
        mListQuestionsBinding.questionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mQuestionsListViewModel.getQuestionsLiveData().observe(this, questions -> {
            if (mQuestionListAdapter == null) {
                mQuestionListAdapter = new QuestionListAdapter(questions);
            } else {
                mQuestionListAdapter.setQuestions(questions);
                mQuestionListAdapter.notifyDataSetChanged();
            }
            mListQuestionsBinding.questionsRecyclerView.setAdapter(mQuestionListAdapter);
        });


        return mListQuestionsBinding.getRoot();
    }

}
