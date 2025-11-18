package ppg.spring.springrepository.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    // attributes
    private String questionText;
    private String questionType;

    @ManyToOne
    @JoinColumn(name = "surveyId")
    private Survey survey;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @JsonIgnore

    private List<Response> responses = new ArrayList<>();

    // constructors

    public Question(String questionText, Survey survey) {
        this.questionText = questionText;
        this.survey = survey;
        this.questionType = questionType;
    }

    public Question() {
    }

    // gets & sets

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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", questionText=" + questionText + "questionType=" + questionType
                + ", survey=" + survey + "]";
    }

}