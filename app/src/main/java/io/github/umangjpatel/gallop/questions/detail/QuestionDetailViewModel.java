package io.github.umangjpatel.gallop.questions.detail;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.github.umangjpatel.gallop.models.answer.Answer;
import io.github.umangjpatel.gallop.models.question.Question;
import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class QuestionDetailViewModel extends AndroidViewModel {

    private static DatabaseReference QUESTION_REF, ANSWERS_REF;

    private FirebaseQueryLiveData mQuestionFirebaseLiveData, mAnswersFirebaseLiveData;

    private MediatorLiveData<Question> mQuestionLiveData;
    private MediatorLiveData<List<Answer>> mAnswersLiveData;

    public QuestionDetailViewModel(@NonNull Application application) {
        super(application);
        mQuestionLiveData = new MediatorLiveData<>();
        mAnswersLiveData = new MediatorLiveData<>();
    }

    public void setDatabaseReference(String questionKey) {
        QUESTION_REF = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("questions")
                .child(questionKey);
        mQuestionFirebaseLiveData = new FirebaseQueryLiveData(QUESTION_REF);
        mQuestionLiveData.addSource(mQuestionFirebaseLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> mQuestionLiveData.postValue(dataSnapshot
                        .getValue(Question.class))).start();
            } else
                mQuestionLiveData.setValue(null);
        });

        ANSWERS_REF = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("answers")
                .child(questionKey);
        mAnswersFirebaseLiveData = new FirebaseQueryLiveData(ANSWERS_REF);
        mAnswersLiveData.addSource(mAnswersFirebaseLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<Answer> answerList = new ArrayList<>();
                    for (DataSnapshot answerSnapshot : dataSnapshot.getChildren())
                        answerList.add(answerSnapshot.getValue(Answer.class));
                    Collections.reverse(answerList);
                    mAnswersLiveData.postValue(answerList);
                }).start();
            } else
                mAnswersLiveData.setValue(null);
        });

    }

    @NonNull
    public LiveData<Question> getQuestionLiveData() {
        return mQuestionLiveData;
    }

    @NonNull
    public LiveData<List<Answer>> getAnswersLiveData() {
        return mAnswersLiveData;
    }
}
