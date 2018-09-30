package io.github.umangjpatel.gallop.questions.list;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.github.umangjpatel.gallop.models.question.Question;
import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class QuestionsListViewModel extends AndroidViewModel {

    private static final DatabaseReference QUESTIONS_REF =
            FirebaseDatabase.getInstance().getReference().child("questions");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<Question>> mQuestionsLiveData;


    public QuestionsListViewModel(@NonNull Application application) {
        super(application);
        mQuestionsLiveData = new MediatorLiveData<>();
        getQuestions();
    }

    public void getQuestions() {
        mLiveData = new FirebaseQueryLiveData(QUESTIONS_REF);
        addLiveDataSource(true);
    }

    @NonNull
    LiveData<List<Question>> getQuestionsLiveData() {
        return mQuestionsLiveData;
    }

    void searchQuestion(String query) {
        mLiveData = new FirebaseQueryLiveData(generateQuery(query));
        addLiveDataSource(false);
    }

    private void addLiveDataSource(boolean isReverse) {
        mQuestionsLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<Question> questions = new ArrayList<>();
                    for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                        Question question = questionSnapshot.getValue(Question.class);
                        questions.add(question);
                    }
                    if (isReverse)
                        Collections.reverse(questions);
                    mQuestionsLiveData.postValue(questions);
                }).start();
            } else
                mQuestionsLiveData.setValue(null);
        });
    }

    private Query generateQuery(String query) {
        return FirebaseDatabase
                .getInstance()
                .getReference()
                .child("questions")
                .orderByChild("questionSearch")
                .startAt(query)
                .endAt(query + "\uf8ff");
    }


}
