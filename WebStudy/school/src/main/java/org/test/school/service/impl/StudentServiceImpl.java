package org.test.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.school.dao.StudentMapper;
import org.test.school.model.test.Student;
import org.test.school.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findByClassId() {
        Student student = studentMapper.findById(1);
        student.getAClass();
        return student;
    }
}
