package ppg.spring.springrepository.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    //attributes

    private String responseText;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;


    //constructors

    public Response(String responseText, Question question) {
        this.responseText = responseText;
        this.question = question;
    }

    public Response() {
    }

    // gets & sets

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    //toString

    @Override
    public String toString() {
        return "Response [responseId=" + responseId + ", responseText=" + responseText + ", question=" + question + "]";
    }

    

    


    

    
    


     

    

    

    



}
