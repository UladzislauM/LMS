INSERT INTO users(email, password, first_name, last_name, patronymic_name, contact_preferences, role, social_media)
VALUES ('ivan99@gmail.com', 'jfhd86', 'Ivan', 'Ivanov', 'Ivanovich', 'TELEGRAM', 'TRAINER', '@Ivan'),
       ('petr48@gmail.com', 'fdfbbfd67sdb', 'Petr', 'Petrov', 'Petrovich', 'CELLPHONE', 'MANAGER', '+375291234567'),
       ('sidor@gmail.com', 'klkjjk', 'Sidor', 'Sidorov', 'Sidorovich', 'INSTAGRAM', 'MANAGER', '#Sidor'),
       ('jek94@gmail.com', '12qwaszx', 'Yauheni', 'Hlaholeu', 'Yayhenivich', 'TELEGRAM', 'STUDENT', '+375291234567'),
       ('sol44@yandex.by', 'qazxsw21', 'Uladzislau', 'Solovev', 'Alexandrovich', 'TELEGRAM', 'STUDENT', '#Solovey'),
       ('galina_sid@gmail.com', 'sid93LL', 'Haliana', 'Sidoric', 'Sergeevna', 'INSTAGRAM', 'STUDENT', '@Sid'),
       ('dlana@mail.ru', 'vfAz1234', 'Lana', 'Dimidova', 'Antonovna', 'TELEGRAM', 'STUDENT', '+375291234567'),
       ('AKsin@Gmail.com', '12345678OOp', 'Andrey', 'Aksenov', 'Olegovich', 'TELEGRAM', 'STUDENT', '#Android'),
       ('vagan@mail.ru', '333eeeddfd', 'Nazar', 'Vahtongov', 'Stefanovich', 'INSTAGRAM', 'STUDENT', '@Nazar'),
       ('tMin@tut.by', 'trewrg', 'Tatyana', 'Minikova', 'Mironovna', 'VIBER', 'STUDENT', '@Hleb');

INSERT INTO file_links (link)
VALUES ('link_1'),
       ('link_2'),
       ('link_3'),
       ('link_4'),
       ('link_5');

INSERT INTO courses (title, description, price, trainer_id, start_date)
VALUES ('course_1', 'description_1', 1, 3, '01-01-2022'),
       ('course_2', 'description_2', 22, 2, '02-02-2022'),
       ('course_3', 'description_3', 333, 1, '03-03-2022'),
       ('course_4', 'description_4', 4444, 5, '04-04-2022'),
       ('course_5', 'description_5', 55555, 4, '05-05-2022');

INSERT INTO lessons (course_id, title, start_time, description, content, home_task)
VALUES (5, 'lesson_1', '2005-10-15 01:02:03', 'description_1', 'content_1', 'hometask1'),
       (2, 'lesson_4', '2006-09-12 10:11:12', 'description_4', 'content_4', 'hometask1'),
       (4, 'lesson_2', '2004-10-19 04:05:06', 'description_2', 'content_2', 'hometask1'),
       (1, 'lesson_3', '2005-12-22 07:08:09', 'description_3', 'content_3', 'hometask1'),
       (3, 'lesson_5', '2006-08-14 13:14:15', 'description_5', 'content_5', 'hometask1');

INSERT INTO homeworks (student_id, lesson_id, comment, mark, filelink_id)
VALUES (4, 1, 'comment_1', 4, 5),
       (2, 2, 'comment_2', 4, 4),
       (3, 3, 'comment_3', 5, 3),
       (1, 4, 'comment_4', 3, 2),
       (5, 5, 'comment_5', 3, 1);

INSERT INTO requests (course_id, user_id, status)
VALUES ((SELECT id FROM courses WHERE title = 'course_1'), (SELECT id FROM users WHERE email = 'jek94@gmail.com'), 'PROCESSING'),
       ((SELECT id FROM courses WHERE title = 'course_2'), (SELECT id FROM users WHERE email = 'sol44@yandex.by'), 'PROCESSING'),
       ((SELECT id FROM courses WHERE title = 'course_3'), (SELECT id FROM users WHERE email = 'galina_sid@gmail.com'), 'PROCESSING'),
       ((SELECT id FROM courses WHERE title = 'course_4'), (SELECT id FROM users WHERE email = 'dlana@mail.ru'), 'PROCESSING'),
       ((SELECT id FROM courses WHERE title = 'course_5'), (SELECT id FROM users WHERE email = 'AKsin@Gmail.com'), 'PROCESSING'),
       ((SELECT id FROM courses WHERE title = 'course_4'), (SELECT id FROM users WHERE email = 'vagan@mail.ru'), 'PROCESSING'),
       ((SELECT id FROM courses WHERE title = 'course_3'), (SELECT id FROM users WHERE email = 'tMin@tut.by'), 'PROCESSING');