package com.education.model.constant;

public class RabbitConstant {
    public static final String EXCHANGE = "edo.direct";
    public static final String SCHEDULER_QUEUE = "schedulerQueue";
    public static final String ADDRESS_CREATE_SERVICE = "address.create.service";
    public static final String ADDRESS_CREATE_DB_QUEUE = "address.create.DB";
    public static final String ADDRESS_CREATE_SERVICE_QUEUE = "address.create.service";
    public static final String ADDRESS_CREATE_EMAIL_QUEUE = "email.create.service";
    public static final String ADDRESS_APPEAL_IS_READ = "appeal.read.service";
    public static final String RESOLUTION_NOTIFICATION_QUEUE = "email.createResolution.service";
    public static final String ROUTING_KEY_SCHEDULER = "schedulerRoutingKey";
    public static final String FILE_STORAGE_QUEUE = "fileStorageQueue";
    public static final String ROUTING_KEY_SCHEDULER_FILE_STORAGE_CLEAR = "schedulerFileStorageClear";
}
