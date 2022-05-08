package peaksoft.spring_data_and_pagination.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.spring_data_and_pagination.dto.StudentRequest;
import peaksoft.spring_data_and_pagination.dto.StudentResponse;
import peaksoft.spring_data_and_pagination.dto.StudentResponseView;
import peaksoft.spring_data_and_pagination.entity.Student;
import peaksoft.spring_data_and_pagination.service.StudentService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
public class StudentController {

    private final StudentService service;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public StudentResponse update(@PathVariable long id, @RequestBody StudentRequest request){
        return service.update(id,request);
    }

    @GetMapping(("{id}"))
    public StudentResponse getById(@PathVariable long id){
        return service.getById(id);
    }

    @DeleteMapping("{id}")
    public StudentResponse delete(@PathVariable long id){
        return service.delete(id);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return service.getAll();
    }

//    @GetMapping
//    public StudentResponseView getAllStudents(@RequestParam(name = "name",required = false)String name,
//                                              @RequestParam int size,
//                                              @RequestParam int page){
//        return service.getAllStudents(name,size,page);
//    }

}
