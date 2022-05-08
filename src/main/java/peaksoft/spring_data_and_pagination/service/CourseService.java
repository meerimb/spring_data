package peaksoft.spring_data_and_pagination.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.spring_data_and_pagination.dto.CourseRequest;
import peaksoft.spring_data_and_pagination.dto.CourseResponse;
import peaksoft.spring_data_and_pagination.entity.Course;
import peaksoft.spring_data_and_pagination.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public CourseResponse create(CourseRequest courseRequest) {
        Course course = mapToEntity(courseRequest);
        repository.save(course);
        return mapToResponses(course);
    }

    public CourseResponse update(long id, CourseRequest courseRequest) {
        Optional<Course> courses = repository.findById(id);
        mapToUpdate(courses.get(), courseRequest);
        return mapToResponses(repository.save(courses.get()));

    }

    public CourseResponse getById(long id) {
//        Optional<Course> course = repository.findById(id);
        return mapToResponses(repository.findById(id).get());
    }

    public CourseResponse delete(long id) {
        Course course = repository.findById(id).get();
        repository.deleteById(id);
        return mapToResponses(course);
    }

    public Course mapToEntity(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        return course;
    }

    public Course mapToUpdate(Course course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        return course;
    }

    public CourseResponse mapToResponses(Course course) {
        CourseResponse response = new CourseResponse();
        response.setCourseName(course.getCourseName());
        response.setDescription(course.getDescription());
        response.setId(course.getId());
        return response;
    }

    public List<CourseResponse> map(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses) {
            responses.add(mapToResponses(course));
        }
        return responses;
    }
}
