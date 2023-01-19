open module lms.service {
    requires lms.platform;
    requires lms.data;

    requires spring.context;
    requires spring.orm;
    requires spring.tx;
    requires spring.core;
    requires jakarta.transaction;
    requires jakarta.annotation;
    requires jakarta.cdi;
    requires com.fasterxml.jackson.annotation;
    requires lombok;
    requires org.mapstruct;
    requires spring.data.commons;

    exports academy.belhard.lms.service;
    exports academy.belhard.lms.service.dto;
    exports academy.belhard.lms.service.dto.course;
    exports academy.belhard.lms.service.dto.request;
    exports academy.belhard.lms.service.dto.user;
    exports academy.belhard.lms.service.exception;
}
