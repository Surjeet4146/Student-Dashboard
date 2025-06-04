```java
package com.studentdashboard.models;

public class Course {
    private String id;
    private String name;
    private String instructor;
    private String schedule;
    private int credits;

    public Course() {
        // Required empty constructor for Firebase
    }

    public Course(String id, String name, String instructor, String schedule, int credits) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.schedule = schedule;
        this.credits = credits;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
```