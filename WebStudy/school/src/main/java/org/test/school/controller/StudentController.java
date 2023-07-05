package org.test.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.common.model.vo.Result;
import org.test.school.model.test.Student;
import org.test.school.service.StudentService;

@RestController
@RequestMapping("/school/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/findByClassId")
    public Result<Student> findByClassId() {
        return Result.succeed(studentService.findByClassId());
    }
}
