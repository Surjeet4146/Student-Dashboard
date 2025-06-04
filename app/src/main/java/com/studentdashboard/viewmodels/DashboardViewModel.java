```java
package com.studentdashboard.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.studentdashboard.models.Course;
import com.studentdashboard.models.Student;
import com.studentdashboard.repositories.FirebaseRepository;
import java.util.List;

public class DashboardViewModel extends ViewModel {
    
    private FirebaseRepository repository;
    private MutableLiveData<Student> student;
    private MutableLiveData<List<Course>> courses;

    public DashboardViewModel() {
        repository = new FirebaseRepository();
        student = new MutableLiveData<>();
        courses = new MutableLiveData<>();
    }

    public MutableLiveData<Student> getStudent() {
        return student;
    }

    public MutableLiveData<List<Course>> getCourses() {
        return courses;
    }

    public void loadStudentData() {
        repository.loadStudentData(student);
    }

    public void loadCourses() {
        repository.loadCourses(courses);
    }
}
```