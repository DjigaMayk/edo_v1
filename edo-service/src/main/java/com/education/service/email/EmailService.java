package com.education.service.email;

import com.education.model.dto.AppealDto;
import com.education.model.dto.ResolutionDto;

public interface EmailService {
    void sendNotificationOnAppeal(AppealDto appealDto);
    void sendNotificationOnResolution(ResolutionDto resolutionDto);
}
