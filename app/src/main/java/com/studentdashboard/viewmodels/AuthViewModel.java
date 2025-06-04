```java
package com.studentdashboard.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.studentdashboard.repositories.FirebaseRepository;

public class AuthViewModel extends ViewModel {
    
    private FirebaseRepository repository;
    private MutableLiveData<Boolean> authResult;

    public AuthViewModel() {
        repository = new FirebaseRepository();
        authResult = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getAuthResult() {
        return authResult;
    }

    public void login(String email, String password) {
        repository.login(email, password, authResult);
    }

    public void register(String email, String password, String name, String studentId) {
        repository.register(email, password, name, studentId, authResult);
    }
}
```