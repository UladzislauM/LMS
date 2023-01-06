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

INSERT INTO courses (title)
VALUES ('course_test_1'),
	('course_test_2'),
	('course_test_3'),
	('course_test_4'),
	('course_test_5'),
	('course_test_6');

INSERT INTO requests (courses_id, users_id, status)
VALUES ((SELECT id FROM courses WHERE title = 'course_test_1'), (SELECT id FROM users WHERE email='jek94@gmail.com'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_2'), (SELECT id FROM users WHERE email='sol44@yandex.by'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_3'), (SELECT id FROM users WHERE email='galina_sid@gmail.com'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_4'), (SELECT id FROM users WHERE email='dlana@mail.ru'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_5'), (SELECT id FROM users WHERE email='AKsin@Gmail.com'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_6'), (SELECT id FROM users WHERE email='vagan@mail.ru'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_6'), (SELECT id FROM users WHERE email='tMin@tut.by'), 'IN_PROCESSING');