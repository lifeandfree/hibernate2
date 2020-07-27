package ru.innopolis;


import ru.innopolis.dao.StudentDao;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Student;
import ru.innopolis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // create some courses
        List<Course> courses = new ArrayList<>();
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        courses.add(tempCourse1);
        Course tempCourse2 = new Course("The Pinball Masterclass");
        courses.add(tempCourse2);

        //create some students
        StudentDao studentDao = new StudentDao();

        Student tempStudent1 = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        tempStudent1.setCourses(Collections.singletonList(new Course("Personal Masterclass")));
        Integer id1 = studentDao.saveStudent(tempStudent1);


        Student tempStudent2 = new Student("Matvey", "Mashin", "mama@javaguides.com");
        tempStudent2.setCourses(courses);
        Integer id2 = studentDao.saveStudent(tempStudent2);

        studentDao.updateStudent(id1);
        studentDao.updateStudent(id2);


        studentDao.insertStudent();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("===================JPQL=======================");
        studentDao.getStudents();
        System.out.println("===================HIBC=======================");
        studentDao.getStudentsС();
        System.out.println("===================JPAC=======================");
        studentDao.getStudentsСJPA();
        System.out.println("==========================================");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        HibernateUtil.shutdown();
    }
}
