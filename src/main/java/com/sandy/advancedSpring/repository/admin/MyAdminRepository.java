package com.sandy.advancedSpring.repository.admin;

import com.sandy.advancedSpring.domain.admin.MyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAdminRepository extends JpaRepository<MyAdmin, Long> {

}
