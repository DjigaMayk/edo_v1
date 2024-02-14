package com.education.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.education.model.constant.RabbitConstant.*;

@Service
@RequiredArgsConstructor
public class FileStorageClearOldArchivedFileScheduler {

    private final RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledClearFileStorageOfArchived() {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_SCHEDULER_FILE_STORAGE_CLEAR, "start clean");
    }

}
