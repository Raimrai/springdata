package kz.bitlab.javaproeetwo.springdata.repositories;

import kz.bitlab.javaproeetwo.springdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User>findByHandledIsTrue();
    List<User>findByHandledFalse();
}
