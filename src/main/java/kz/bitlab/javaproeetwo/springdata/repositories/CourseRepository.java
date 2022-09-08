package kz.bitlab.javaproeetwo.springdata.repositories;

import kz.bitlab.javaproeetwo.springdata.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {
}
