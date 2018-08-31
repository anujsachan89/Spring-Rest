package hello;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.ws.soap.Addressing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.entity.Student;
import hello.errorresponse.StudentErrorResponse;

@RestController
@RequestMapping("/test")
public class DemoRestController {
	private List<Student> theStudent;
	
	// Define post construct to load the student data...only once
	
	@PostConstruct
	public void loadData() {
		
		theStudent = new ArrayList<>();
		theStudent.add(new Student("Anuj","Sachan"));
		theStudent.add(new Student("Cola","Bola"));
		theStudent.add(new Student("Tim","Bachulka"));
		theStudent.add(new Student("Steve","Job"));
	}
	// add code the hello endpoint
	
	@GetMapping("/hello")
	public String sayHello() {
		return "You Have Done it";
	}
	
	// define  endppoint to students- return list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudent;
	}
	// define  endppoint to /students/{students-id}- return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudentId(@PathVariable int studentId) {
		if(studentId>=theStudent.size() || studentId<0) {
			throw new StudentNotFoundException("Student id not found: "+studentId);
		}
		return theStudent.get(studentId);
	}
	
}
