package com.example.TaskManager.task.repo;

import com.example.TaskManager.task.entity.Task;
import com.example.TaskManager.task.entity.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value="SELECT u.email as email,t.task_id as taskId, t.name as taskName, t.created_at as createdAt, t.updated_at as updatedAt\n" +
            "FROM tbl_task t\n" +
            "INNER JOIN\n" +
            "tbl_user u\n" +
            "ON t.fk_user_id = u.user_id", nativeQuery = true)
    List<TaskProjection> getAllTasksWithUserInfo();
//    Projection is used when multiple tables are inner joined

    @Query(value="SELECT t from Task t where t.assignedUserId = ?1")
    List<Task>fetchTaskByUserId(int userId);

    @Query(value="SELECT t from Task t where t.taskId = ?1")
    Task fetchTaskByTaskId(int taskId);
}
