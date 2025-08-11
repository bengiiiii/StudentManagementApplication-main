package com.eminpolat.restapi.webApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eminpolat.restapi.dataAccess.StudentRepository;
import com.eminpolat.restapi.entity.Student;


@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
		List<Student> students = studentRepository.findAll();
		
		return students;
	}
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id)
	{
		Student student = studentRepository.findById(id).get();
		
		return student;
	}
	
	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createStudent(@RequestBody Student student)
	{
		studentRepository.save(student);
	}
	
	@PutMapping("/student/update/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) 
	{
	    Student student = studentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

	    student.setName(updatedStudent.getName());
	    student.setPercentage(updatedStudent.getPercentage());
	    student.setBranch(updatedStudent.getBranch());

	    return studentRepository.save(student);
	}
	
	@DeleteMapping("/student/delete/{id}")
	public void removeStudent(@PathVariable int id)
	{
		Student student = studentRepository.findById(id).get();
		studentRepository.delete(student);
	}
}




