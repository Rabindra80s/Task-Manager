package com.example.TaskManager.login.repo;

import com.example.TaskManager.login.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);

    //    @Query(value="SELECT * FROM tbl_user a WHERE a.role='USER'", nativeQuery = true) //mySQL
    @Query(value="SELECT appUser FROM AppUser appUser WHERE appUser.role='USER'") //hibernate
    List<AppUser> fetchAllUser();
}
