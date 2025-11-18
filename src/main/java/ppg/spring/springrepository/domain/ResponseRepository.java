package ppg.spring.springrepository.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ResponseRepository extends CrudRepository<Response, Long> {
    List<Response> findByQuestionIn(List<Question> questions);
}
