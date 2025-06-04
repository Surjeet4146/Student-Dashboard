```java
package com.studentdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.studentdashboard.viewmodels.DashboardViewModel;
import com.studentdashboard.adapters.CourseAdapter;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvWelcome, tvStudentId;
    private RecyclerView rvCourses;
    private DashboardViewModel dashboardViewModel;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        
        setSupportActionBar(findViewById(R.id.toolbar));
        
        initViews();
        setupViewModel();
        setupRecyclerView();
        loadData();
    }

    private void initViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        tvStudentId = findViewById(R.id.tvStudentId);
        rvCourses = findViewById(R.id.rvCourses);
    }

    private void setupViewModel() {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        
        dashboardViewModel.getStudent().observe(this, student -> {
            if (student != null) {
                tvWelcome.setText("Welcome, " + student.getName());
                tvStudentId.setText("Student ID: " + student.getStudentId());
            }
        });

        dashboardViewModel.getCourses().observe(this, courses -> {
            if (courses != null) {
                courseAdapter.updateCourses(courses);
            }
        });
    }

    private void setupRecyclerView() {
        courseAdapter = new CourseAdapter();
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseAdapter);
    }

    private void loadData() {
        dashboardViewModel.loadStudentData();
        dashboardViewModel.loadCourses();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
```