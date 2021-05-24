import dao.Course;
import dao.CourseDAO;
import dao.JPACourseDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmifed
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        CourseDAO courseDAO = context.getBean("jpaCourseService", CourseDAO.class);
        System.out.println(courseDAO.findAll());
        System.out.println(courseDAO.findById(1));
        Course addCourse = new Course();
        addCourse.setTitle("Python base");
        addCourse.setLength(20);
        addCourse.setDescriptions("Python base description");
        courseDAO.insert(addCourse);
        Course updCourse = courseDAO.findById(1);
        updCourse.setDescriptions(updCourse.getDescriptions().substring(0, 25) + "-Upd");
        courseDAO.update(updCourse);

        System.out.println(courseDAO.findByTitle("base"));

        courseDAO.delete(courseDAO.findLastId());

        System.out.println(courseDAO.findAll());
        context.close();



    }
}
