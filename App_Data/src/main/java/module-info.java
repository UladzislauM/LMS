open module lms.data {
    requires lms.platform;

    requires spring.data.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires lombok;

    exports academy.belhard.lms.data.entity;
    exports academy.belhard.lms.data.repository;
}
