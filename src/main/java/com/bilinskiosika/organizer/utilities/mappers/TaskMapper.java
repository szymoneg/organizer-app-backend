package com.bilinskiosika.organizer.utilities.mappers;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TaskMapper implements Converter<Optional<Task>, TaskDto> {

    final UserRepository userRepository;

    public TaskMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Task> convert(TaskDto from) {
        return Optional.ofNullable(Task.builder()
                .user(userRepository.findByIdUser(from.getIdUser()).get())
                .titleTask(from.getTitleTask())
                .descriptionTask(from.getDescriptionTask())
                .startTask(Timestamp.valueOf(from.getStartTask()))
                .endTask(Timestamp.valueOf(from.getEndTask()))
                .tags(from.getTags())
                .color(from.getColor())
                .notificationTask(Timestamp.valueOf(from.getNotificationTask()))
                .build());
    }
}
