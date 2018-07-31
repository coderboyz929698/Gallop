package io.github.umangjpatel.gallop.questions.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class QuestionDetailViewModel extends AndroidViewModel {

    private static DatabaseReference ANSWERS_REF;

    private FirebaseQueryLiveData mFirebaseQueryLiveData;

    public QuestionDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setDatabaseReference(String questionKey) {
        ANSWERS_REF = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("answers")
                .child(questionKey);
        mFirebaseQueryLiveData = new FirebaseQueryLiveData(ANSWERS_REF);
    }
}
