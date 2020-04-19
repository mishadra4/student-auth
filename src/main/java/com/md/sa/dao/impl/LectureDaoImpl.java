package com.md.sa.dao.impl;

import com.md.sa.dao.LectureDao;
import com.md.sa.model.Groups;
import com.md.sa.model.Lecture;
import com.md.sa.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LectureDaoImpl implements LectureDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createLecture() {
        Lecture lecture = new Lecture();
        lecture.setLectureId(3);
        entityManager.persist(lecture);
    }

    @Override
    public Lecture getLecture(Integer id) {
        String query= "from lecture where lectureId = ?1";
        TypedQuery<Lecture> typedQuery = entityManager.createQuery(query, Lecture.class);
        typedQuery.setParameter(1, id);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Lecture> getLectures(String lecturerUsername) {
        String query = "select l from lecture as l left join l.lecturer as lr where lr.username =?1 order by l.ordinalNumber";
        TypedQuery<Lecture> typedQuery = entityManager.createQuery(query, Lecture.class);
        typedQuery.setParameter(1, lecturerUsername);
        return typedQuery.getResultList();
    }

    @Override
    public List<Lecture> getLecturesBySubject(String subjectId) {
        String query = "select l from lecture as l left join l.subject as s where s.name =?1 order by l.ordinalNumber";
        TypedQuery<Lecture> typedQuery = entityManager.createQuery(query, Lecture.class);
        typedQuery.setParameter(1, subjectId);
        return typedQuery.getResultList();
    }

    @Override
    public Lecture saveLecture(Lecture lecture) {
        return entityManager.merge(lecture);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
