package com.app.methodlevel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {


    
    @PreAuthorize("hasRole('PADMIN')")
    public String createProject() {
        return "PADMIN can createProject";
    }

    @PreAuthorize("hasAnyRole('PADMIN', 'PROJECT_MANAGER')")
    public String updateProject() {
      
        return "PADMIN or PROJECT_MANAGER can updateProject";
    }

}
