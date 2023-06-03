package com.qiaoqing.cruddemo.dao;

import com.qiaoqing.cruddemo.entity.Instructor;
import com.qiaoqing.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // add Transactional when updating the db
    @Override
    @Transactional
    public void save(Instructor theInstructor) {

        // save an instructor and instructorDetail because cascade
        entityManager.persist(theInstructor);
    }

    // retrieve an instructor by id and also the instructor_detail
    @Override
    public Instructor findInstructorById(int theId) {

        return entityManager.find(Instructor.class, theId);

    }

    // delete an instructor
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {

        return entityManager.find(InstructorDetail.class, theId);

    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);

    }
}
