package ru.innopolis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.innopolis.entity.Course;
import ru.innopolis.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseDao {
    public void saveCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(course);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Course getCourse(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<Course> query = builder.createQuery(Course.class);
            final Root<Course> root = query.from(Course.class);
            query.select(root).where(builder.equal(root.get("id"), id));
            return session.createQuery(query).uniqueResult();
        }
    }

    public List<Course> getCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<Course> query = builder.createQuery(Course.class);
            query.from(Course.class);
            return session.createQuery(query).list();
        }
    }
}
