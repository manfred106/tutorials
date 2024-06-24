package com.baeldung.h2db.schema;

import com.baeldung.h2db.schema.entity.Student;
import com.baeldung.h2db.schema.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InitScriptlCreateSchemaApplication.class)
@ActiveProfiles("schema-script")
class InitScriptCreateSchemaApplicationIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void whenSaveStudent_thenStudentIsSaved() {
        long beforeCount = studentRepository.count();

        Student student = Student.builder()
                .studentId("24567433")
                .name("David Lloyds")
                .build();
        student = studentRepository.save(student);

        assertThat(student.getId()).isNotNull();
        assertThat(studentRepository.count() - beforeCount).isEqualTo(1);
    }

}
