package io.github.umangjpatel.gallop.questions;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.umangjpatel.gallop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsListFragment extends Fragment {


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_questions, container, false);
    }

}
