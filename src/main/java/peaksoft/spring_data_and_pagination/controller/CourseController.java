package peaksoft.spring_data_and_pagination.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.spring_data_and_pagination.dto.CourseRequest;
import peaksoft.spring_data_and_pagination.dto.CourseResponse;
import peaksoft.spring_data_and_pagination.service.CourseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService service;

    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public CourseResponse update(@PathVariable long id, @RequestBody CourseRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("{id}")
    public CourseResponse delete(@PathVariable long id){
        return service.delete(id);
    }

    @GetMapping("{id}")
    public CourseResponse getById(@PathVariable long id){
        return service.getById(id);
    }
}
