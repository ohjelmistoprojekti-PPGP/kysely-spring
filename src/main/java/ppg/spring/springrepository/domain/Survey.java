package ppg.spring.springrepository.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey", orphanRemoval = true)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    // attributes

    private String surveyName;
    private String surveyDesc;
    private String createdDate;
    private String startingDate;
    private String endingDate;

    // constructors
    public Survey(String surveyName, String surveyDesc, String createdDate, String startingDate,
            String endingDate) {
        this.surveyName = surveyName;
        this.surveyDesc = surveyDesc;
        this.createdDate = createdDate;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Survey() {
        this.surveyName = null;
        this.surveyDesc = null;
        this.createdDate = null;
        this.startingDate = null;
        this.endingDate = null;
    }

    // getters and setters, toString
    public Long getSurveyId() {
        return surveyId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public String toString() {
        return "Survey [surveyId=" + surveyId + ", questions=" + questions + ", surveyName=" + surveyName
                + ", surveyDesc=" + surveyDesc + ", createdDate=" + createdDate + ", startingDate=" + startingDate
                + ", endingDate=" + endingDate + "]";
    }

}
