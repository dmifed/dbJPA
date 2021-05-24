package dao;

import java.util.List;

/**
 * @author dmifed
 */
public interface CourseDAO {

    Course findById(int id);

    List<Course> findAll();

    List<Course> findByTitle(String title);

    void insert(Course course);

    void update(Course course);

    void delete(int id);

    int findLastId();
}
