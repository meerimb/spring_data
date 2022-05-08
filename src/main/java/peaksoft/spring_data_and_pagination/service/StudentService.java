package peaksoft.spring_data_and_pagination.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.spring_data_and_pagination.dto.StudentRequest;
import peaksoft.spring_data_and_pagination.dto.StudentResponse;
import peaksoft.spring_data_and_pagination.entity.Course;
import peaksoft.spring_data_and_pagination.entity.Student;
import peaksoft.spring_data_and_pagination.repository.CourseRepository;
import peaksoft.spring_data_and_pagination.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseService service;

    public StudentResponse create(StudentRequest studentRequest) {
        Student student = mapToEntity(studentRequest);
        studentRepository.save(student);

        return mapToResponse(student);
    }

    public StudentResponse update(long id, StudentRequest studentRequest) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            System.out.println("student is not found");
        }
        mapToUpdate(student.get(), studentRequest);
        return mapToResponse(studentRepository.save(student.get()));

    }

    public StudentResponse getById(long id) {

        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            System.out.println("student is not found");
        }
        return mapToResponse(studentRepository.findById(id).get());
    }

    public StudentResponse delete(long id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return mapToResponse(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }


    public Student mapToEntity(StudentRequest studentRequest) {
        List<Course> courses = new ArrayList<>();
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setAddress(studentRequest.getAddress());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        Course course = courseRepository.findById(studentRequest.getCourse()).get();
        courses.add(course);
        student.setCourses(courses);
        return student;
    }

    public Student mapToUpdate(Student student, StudentRequest studentRequest) {
        List<Course> courses = new ArrayList<>();
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setAddress(studentRequest.getAddress());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        Course course = courseRepository.findById(studentRequest.getCourse()).get();
        courses.add(course);
        student.setCourses(courses);
        return student;
    }

    public StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setSurname(student.getSurname());
        response.setAddress(student.getAddress());
        response.setEmail(student.getEmail());
        response.setPhoneNumber(student.getPhoneNumber());
        response.setCreated(LocalDateTime.now());
        response.setActive(student.isActive());
        response.setCourses(service.map(student.getCourses()));

        return response;
    }

    public List<StudentResponse> map(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(mapToResponse(student));
        }
        return responses;
    }

    //    public StudentResponseView getAllStudents(String name,int page,int size){
//        StudentResponseView studentResponseView=new StudentResponseView();
//        Pageable pageable= (Pageable) PageRequest.of(page,size);
//        studentResponseView.setStudentResponses(map(search(name,pageable)));
//        return studentResponseView;
//
//    }

//    List<Student> search(String name, Pageable pageable) {
//        String text = name == null ? "" : name;
//        return studentRepository.searchAndPagination(text.toUpperCase(), pageable);
//    }

}
