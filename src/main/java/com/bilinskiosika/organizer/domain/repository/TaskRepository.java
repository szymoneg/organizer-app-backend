package com.bilinskiosika.organizer.domain.repository;

import com.bilinskiosika.organizer.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, CrudRepository<Task, Long> {
}
