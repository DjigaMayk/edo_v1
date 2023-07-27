package com.education.util.Mapper.impl;

import com.education.entity.Notification;
import com.education.model.dto.NotificationDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EmployeeMapper.class)
public interface NotificationMapper extends Mappable<Notification, NotificationDto> {
    @Override
    @Mapping(target = "employee", ignore = true)
    NotificationDto toDto(Notification notification);

    @Override
    @Mapping(target = "employees.notification", ignore = true)
    Notification toEntity(NotificationDto notificationDto);
}
