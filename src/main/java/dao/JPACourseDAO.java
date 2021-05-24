package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by DIMA, on 21.05.2021
 */
@Service("jpaCourseService")
@Repository
@Transactional
public class JPACourseDAO implements CourseDAO{

    private final static Log LOG = LogFactory.getLog(JPACourseDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        TypedQuery<Course> typedQuery = entityManager.
                createQuery("select  c from Course c where c.id = :id", Course.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return entityManager.createNamedQuery("Course.findAll", Course.class).getResultList();
        //return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public List<Course> findByTitle(String title) {
        TypedQuery<Course> tq = entityManager.
                createQuery("select c from Course c where c.title like :title", Course.class);
        tq.setParameter("title", "%" + title + "%");
        return tq.getResultList();
    }

    @Override
    public void insert(Course course) {
        entityManager.persist(course);
        LOG.info("Insert course with id: " + course.getId());

    }

    @Override
    public void update(Course course) {
        if(course.getId() != 0 && entityManager.find(Course.class, course.getId()) != null){
            entityManager.merge(course);
            LOG.info("Updated course with id: " + course.getId());
        }

    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
        LOG.info("Deleted course with id: " + id);
    }

    @Override
    public int findLastId(){
        return (Integer) entityManager.createQuery("select max(id) from Course").getSingleResult();
    }
}
