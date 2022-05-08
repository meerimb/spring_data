package peaksoft.spring_data_and_pagination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.spring_data_and_pagination.entity.Student;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    List<Student> findAll();

    @Override
    <S extends Student> S save(S entity);

    @Override
    Optional<Student> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

//    @Query("select s from Student s inner join s.courses c where upper(s.name) like concat('%',:name,'%' )" +
//            "or upper(s.surname) like concat('%',:name,'%') or upper(s.email) like concat('%',:name,'%' ) " +
//            "or upper(c.courseName) like concat('%',:name,'%' ) ")
//    List<Student> searchAndPagination(@Param("name") String name, Pageable pageable);
}