```java
package com.studentdashboard.models;

public class Student {
    private String uid;
    private String name;
    private String email;
    private String studentId;

    public Student() {
        // Required empty constructor for Firebase
    }

    public Student(String uid, String name, String email, String studentId) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.studentId = studentId;
    }

    // Getters and Setters
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}
```