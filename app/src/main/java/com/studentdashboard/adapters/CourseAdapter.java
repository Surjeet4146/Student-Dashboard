```java
package com.studentdashboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.studentdashboard.R;
import com.studentdashboard.models.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    
    private List<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void updateCourses(List<Course> newCourses) {
        this.courses = newCourses;
        notifyDataSetChanged();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCourseName, tvInstructor, tvSchedule, tvCredits;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvInstructor = itemView.findViewById(R.id.tvInstructor);
            tvSchedule = itemView.findViewById(R.id.tvSchedule);
            tvCredits = itemView.findViewById(R.id.tvCredits);
        }

        public void bind(Course course) {
            tvCourseName.setText(course.getName());
            tvInstructor.setText("Instructor: " + course.getInstructor());
            tvSchedule.setText("Schedule: " + course.getSchedule());
            tvCredits.setText("Credits: " + course.getCredits());
        }
    }
}
```