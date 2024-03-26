package com.education.util.Mapper.impl;

import com.education.entity.Employee;
import com.education.entity.Notification;
import com.education.entity.Report;
import com.education.entity.Resolution;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.NotificationDto;
import com.education.model.dto.ReportDto;
import com.education.model.dto.ResolutionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = DeadlineMapper.class)
public interface ResolutionMapper extends Mappable<Resolution, ResolutionDto> {

	//создано для ухода от бесконечного цикла при маппинге
	@Override
	@Mapping(target = "question.resolutions", ignore = true)
	@Mapping(target = "signer.notification", qualifiedByName = "ignoreNotificationEmployee")
	@Mapping(target = "creator.notification", qualifiedByName = "ignoreNotificationEmployee")
	@Mapping(target = "curator.notification", qualifiedByName = "ignoreNotificationEmployee")
	@Mapping(target = "executors", qualifiedByName = "ignoreNotificationInExecutorsList")
	@Mapping(target = "reports", qualifiedByName = "reportsToDto")
	ResolutionDto toDto(Resolution resolution);

	@Named("ignoreNotificationInExecutorsList")
	default List<EmployeeDto> ignoreNotificationInExecutorsList(List<Employee> executors) {
		if (executors != null) {
			return executors.stream()
					.map(this::mapExecutorWithoutNotificationEmployee)
					.collect(Collectors.toList());
		}
		return null;
	}

	@Named("ignoreEmployeeNotification")
	default EmployeeDto mapExecutorWithoutNotificationEmployee(Employee executor) {
		var employeeDto = new EmployeeDto();
		// Use the default mapper to map all fields except the notification field
		BeanUtils.copyProperties(executor, employeeDto, "notification");
		var notificationList = executor.getNotification();
		if (notificationList != null) {
			employeeDto.setNotification(ignoreNotificationEmployee(notificationList));
		}
		return employeeDto;
	}

	@Named("ignoreNotificationEmployee")
	default List<NotificationDto> ignoreNotificationEmployee(List<Notification> notificationList) {
		if (notificationList != null) {
			List<NotificationDto> notificationDtoList = new ArrayList<>();
			for (Notification notification : notificationList) {
				NotificationDto notificationDto = new NotificationDto();

				notificationDto.setId(notification.getId());
				notificationDto.setEnumNotification(notification.getEnumNotification());
				notificationDto.setEmployee(null);

				notificationDtoList.add(notificationDto);
			}
			return notificationDtoList;
		}
		return null;
	}

    @Named("reportToDto")
    default ReportDto reportToDto(Report report) {
        var reportDto = new ReportDto();
        if (report != null) {
            BeanUtils.copyProperties(report, reportDto, "resolution.reports");
            return reportDto;
        }
        return null;
    }

	@Named("reportsToDto")
	default List<ReportDto> reportsToDto(List<Report> reports) {
		if (!reports.isEmpty()) {
            return reports
                    .stream()
                    .map(this::reportToDto).collect(Collectors.toList());
		}
		return null;
	}

}
