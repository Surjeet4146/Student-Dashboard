```java
package com.studentdashboard.repositories;

import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studentdashboard.models.Course;
import com.studentdashboard.models.Student;
import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository {
    
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public FirebaseRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void login(String email, String password, MutableLiveData<Boolean> result) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    result.setValue(task.isSuccessful());
                });
    }

    public void register(String email, String password, String name, String studentId, MutableLiveData<Boolean> result) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Student student = new Student(user.getUid(), name, email, studentId);
                            mDatabase.child("students").child(user.getUid()).setValue(student)
                                    .addOnCompleteListener(dbTask -> {
                                        result.setValue(dbTask.isSuccessful());
                                    });
                        }
                    } else {
                        result.setValue(false);
                    }
                });
    }

    public void loadStudentData(MutableLiveData<Student> student) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mDatabase.child("students").child(user.getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Student studentData = dataSnapshot.getValue(Student.class);
                            student.setValue(studentData);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle error
                        }
                    });
        }
    }

    public void loadCourses(MutableLiveData<List<Course>> courses) {
        mDatabase.child("courses")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Course> courseList = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Course course = snapshot.getValue(Course.class);
                            courseList.add(course);
                        }
                        courses.setValue(courseList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
    }
}
```