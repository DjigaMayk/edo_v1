package com.education.feign.feign_file_pool;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "edo-repository", path = "/api/repository/file_pool")
public interface FilePoolFeignClient {

    @GetMapping("/listUuidFilesFiveYearsInArchive")
    List<UUID> getListUuidFilesArchivedMoreFiveYearsAgo();

    @DeleteMapping("/deleteByUuid")
    Optional<UUID> deleteByUuid(@RequestBody UUID uuid);
}
