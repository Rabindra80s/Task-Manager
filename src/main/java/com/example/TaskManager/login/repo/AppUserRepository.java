package com.example.TaskManager.login.repo;

import com.example.TaskManager.login.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);


//    @Query(value="select * from tbl_user a where a.role='USER'", nativeQuery = true) //--> mySQL Workbench
    @Query(value="select appUser from AppUser appUser where appUser.role='USER'") //--> Hibernate

    List<AppUser> fetchAllUser();
}
