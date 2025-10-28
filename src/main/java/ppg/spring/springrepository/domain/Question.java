package ppg.spring.springrepository.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    // attribuutit
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "surveyId")
    private Survey survey;

    // konstruktorit
    public Question(String questionText, Survey survey) {
        this.questionText = questionText;
        this.survey = survey;
    }

    public Question() {
    }

    // getit ja setit
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", questionText=" + questionText + ", survey=" + survey + "]";
    }

}
