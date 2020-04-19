package com.example.TaskManager.task.repo;

import com.example.TaskManager.task.entity.TaskAnswer;
import com.example.TaskManager.task.entity.TaskAnswerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAnswerRepository extends JpaRepository<TaskAnswer, Integer> {
    @Query(value = "SELECT\n" +
            "u.email as email, t.name as taskName, ta.answer as answer, ta.created_at as createdAt, ta.updated_at as updatedAt\n" +
            "from\n" +
            "tbl_task_answer ta\n" +
            "inner join tbl_task t\n" +
            "on ta.fk_task_id = t.task_id\n" +
            "inner join tbl_user u\n" +
            "on t.fk_user_id = u.user_id\n" +
            "where ta.fk_task_id = ?", nativeQuery = true)
    TaskAnswerProjection selectTaskAnswerWithAdditionalInfo(int taskId);

    TaskAnswer findByTaskId(int taskId);

}
