package io.github.umangjpatel.gallop.questions.list;

import androidx.lifecycle.ViewModel;
import io.github.umangjpatel.gallop.repositories.QuestionPostRepository;

class AskQuestionViewModel extends ViewModel {

    private String mLabel, mQuestion;

    public void setLabel(String label) {
        mLabel = label;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void publishQuestion() {
        QuestionPostRepository.getInstance().addQuestion(mLabel, mQuestion);
    }
}
