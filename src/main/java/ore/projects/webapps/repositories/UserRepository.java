package ore.projects.webapps.repositories;

import ore.projects.webapps.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOne(Long userID);

    User findByEmailAndPassword(String email, String password);

    List<User> findAll();

    List<User> findByEmail(String email);

    User save(User user);

    void deleteByUserID(Long userID);

}
