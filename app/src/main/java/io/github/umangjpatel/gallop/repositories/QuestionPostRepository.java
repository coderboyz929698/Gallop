package io.github.umangjpatel.gallop.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Objects;

import io.github.umangjpatel.gallop.models.question.Question;
import io.github.umangjpatel.gallop.models.user.UserInfoBuilder;

public class QuestionPostRepository {

    private static QuestionPostRepository sQuestionPostRepository;

    private static DatabaseReference sQuestionReference;


    private QuestionPostRepository() {
        sQuestionReference =
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("questions");
    }

    public static QuestionPostRepository getInstance() {
        if (sQuestionPostRepository == null) {
            synchronized (QuestionPostRepository.class) {
                if (sQuestionPostRepository == null)
                    sQuestionPostRepository = new QuestionPostRepository();
            }
        }
        return sQuestionPostRepository;
    }

    public void addQuestion(String label, String questionContent) {
        FirebaseUser currentUser = FirebaseAuth
                .getInstance()
                .getCurrentUser();
        Question question = new Question();
        question.setAuthor(new UserInfoBuilder()
                .setEmailAddress(Objects.requireNonNull(currentUser).getEmail())
                .createUserInfo().getDisplayName());
        question.setDate(new Date().getTime());
        question.setLabel(label);
        question.setQuestion(questionContent);
        question.setKey(sQuestionReference.push().getKey());
        sQuestionReference.child(question.getKey()).setValue(question);
    }
}
