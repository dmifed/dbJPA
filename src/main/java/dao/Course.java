package dao;

import javax.persistence.*;

/**
 * @author dmifed
 */

@Entity
@Table(name = "courses")
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select c from Course c")
})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "lenght")
    private int length;

    @Column(name = "descriptions")
    private String descriptions;


    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "#" + id + " " + title + ":" + length + "h " + "[" + descriptions + "]";
    }

}
