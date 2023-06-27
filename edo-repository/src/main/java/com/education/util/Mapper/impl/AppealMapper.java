package com.education.util.Mapper.impl;

import com.education.entity.*;
import com.education.model.dto.*;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AppealMapper extends Mappable<Appeal, AppealDto> {

	//создано для ухода от бесконечного цикла при маппинге
	@Override
	@Mapping(target = "question", qualifiedByName = "ignoreResolutionsInQuestionList")
	@Mapping(target = "creator.notification", qualifiedByName = "ignoreNotificationEmployee")
	@Mapping(target = "addressee", qualifiedByName = "ignoreNotificationInEmployeeList")
	@Mapping(target = "signer", qualifiedByName = "ignoreNotificationInEmployeeList")
	AppealDto toDto(Appeal appeal);

	@Named("ignoreResolutionsInQuestionList")
	default List<QuestionDto> ignoreResolutionsInQuestionList(List<Question> questionList) {
		List<QuestionDto> questionDtoList = new ArrayList<>();
		if (questionList != null) {
			for (Question question : questionList) {
				var questionDto = new QuestionDto();
				BeanUtils.copyProperties(question, questionDto, "resolutions");
				questionDtoList.add(questionDto);
			}
			return questionDtoList;
		}
		return null;
	}

	@Named("ignoreNotificationInEmployeeList")
	default List<EmployeeDto> ignoreNotificationInEmployeeList(List<Employee> employers) {
		if (employers != null) {
			return employers.stream()
					.map(this::excludeNotificationFromEmployeeList)
					.collect(Collectors.toList());
		}
		return null;
	}

	default EmployeeDto excludeNotificationFromEmployeeList(Employee employee) {
		var employeeDto = new EmployeeDto();
		// Use the default mapper to map all fields except the notification field
		BeanUtils.copyProperties(employee, employeeDto, "notification");
		var notificationList = employee.getNotification();
		if (notificationList != null) {
			employeeDto.setNotification(ignoreEmployeeNotification(notificationList));
		}
		return employeeDto;
	}

	@Named("ignoreNotificationEmployee")
	default List<NotificationDto> ignoreEmployeeNotification(List<Notification> notificationList) {
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
}
