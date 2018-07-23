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

    private static final int EMPTY_QUESTIONS = 0, LOADING_COURSES = 1, LOADED_COURSES = 2;

    private FragmentListQuestionsBinding mQuestionsBinding;
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
        mQuestionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_questions, container, false);
        mQuestionsListViewModel = ViewModelProviders.of(this).get(QuestionsListViewModel.class);
        updateUI(LOADING_COURSES);
        mQuestionsListViewModel.getQuestionsLiveData().observe(this, questions -> {
            if (questions != null && questions.size() > 0) {
                updateUI(LOADED_COURSES);
                if (mQuestionListAdapter == null) {
                    mQuestionListAdapter = new QuestionListAdapter(questions);
                    mQuestionsBinding.questionsRecyclerView.setAdapter(mQuestionListAdapter);
                } else {
                    mQuestionListAdapter.setQuestions(questions);
                    mQuestionListAdapter.notifyDataSetChanged();
                }
            } else
                updateUI(EMPTY_QUESTIONS);
        });


        return mQuestionsBinding.getRoot();
    }

    private void updateUI(int layoutType) {
        switch (layoutType) {
            case LOADING_COURSES:
                mQuestionsBinding.emptyQuestions.setVisibility(View.GONE);
                mQuestionsBinding.progressBar.setVisibility(View.VISIBLE);
                mQuestionsBinding.questionsRecyclerView.setVisibility(View.GONE);
                mQuestionsBinding.questionsRecyclerView.setLayoutManager(null);
                break;
            case LOADED_COURSES:
                mQuestionsBinding.emptyQuestions.setVisibility(View.GONE);
                mQuestionsBinding.progressBar.setVisibility(View.GONE);
                mQuestionsBinding.questionsRecyclerView.setVisibility(View.VISIBLE);
                mQuestionsBinding.questionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case EMPTY_QUESTIONS:
                mQuestionsBinding.emptyQuestions.setVisibility(View.VISIBLE);
                mQuestionsBinding.progressBar.setVisibility(View.GONE);
                mQuestionsBinding.questionsRecyclerView.setVisibility(View.GONE);
                mQuestionsBinding.questionsRecyclerView.setLayoutManager(null);
                break;
        }

    }

}
