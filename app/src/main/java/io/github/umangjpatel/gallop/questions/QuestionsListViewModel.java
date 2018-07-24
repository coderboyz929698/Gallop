package io.github.umangjpatel.gallop.questions;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.umangjpatel.gallop.models.question.Question;
import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class QuestionsListViewModel extends AndroidViewModel {

    private static final DatabaseReference QUESTIONS_REF =
            FirebaseDatabase.getInstance().getReference().child("questions");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<Question>> mQuestionsLiveData;


    public QuestionsListViewModel(@NonNull Application application) {
        super(application);
        mLiveData = new FirebaseQueryLiveData(QUESTIONS_REF);
        mQuestionsLiveData = new MediatorLiveData<>();
        mQuestionsLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<Question> questions = new ArrayList<>();
                    for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                        Question question = questionSnapshot.getValue(Question.class);
                        questions.add(question);
                    }
                    Collections.reverse(questions);
                    mQuestionsLiveData.postValue(questions);
                }).start();
            } else
                mQuestionsLiveData.setValue(null);
        });
    }

    @NonNull
    public LiveData<List<Question>> getQuestionsLiveData() {
        return mQuestionsLiveData;
    }
}
