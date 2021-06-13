package com.bilinskiosika.organizer.domain.repository;

import com.bilinskiosika.organizer.domain.entity.Task;
import com.bilinskiosika.organizer.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, CrudRepository<Task, Long> {

    List<Task> findTaskByUser(User user);

    Optional<Task> findByUser(User user);

    Optional<Task> findTaskByIdTask(long idTask);

    @Query("select t from Task t where t.startTask > ?1 and t.endTask < ?2")
    List<Task> findAllTasksByDate(Date first, Date second);
}
