<<<<<<< HEAD
INSERT INTO users(email, password, first_name, last_name, patronymic_name, contact_preferences, social_media)
VALUES ('ivan99@gmail.com', 'jfhd86', 'Ivan', 'Ivanov', 'Ivanovich', 'TELEGRAM', '@Ivan'),
       ('petr48@gmail.com', 'fdfbbfd67sdb', 'Petr', 'Petrov', 'Petrovich', 'CELLPHONE', '+375291234567'),
       ('sidor@gmail.com', 'klkjjk', 'Sidor', 'Sidorov', 'Sidorovich', 'INSTAGRAM', '#Sidor');
=======
INSERT INTO users(name, last_name, email, password, role) 
VALUES ('Yauheni', 'Hlaholeu', 'jek94@gmail.com', '12qwaszx', 'ADMIN'),
    ('Uladzislau', 'Solovev', 'sol44@yandex.by', 'qazxsw21', 'USER'),
    ('Haliana', 'Sidoric', 'galina_sid@gmail.com', 'sid93LL', 'USER'),
    ('Lana', 'Dimidova', 'dlana@mail.ru', 'vfAz1234', 'MANAGER'),
    ('Andrey', 'Aksenov', 'AKsin@Gmail.com','12345678OOp', 'USER'),
    ('Nazar', 'Vahtongov', 'vagan@mail.ru', '333eeeddfd', 'USER'),
    ('Tatyana', 'Minikova', 'tMin@tut.by', 'trewrg', 'USER');
    
INSERT INTO courses (title, description, price, start_date, trainer) 
VALUES ('course_test_1', 'description_test_1', 111, '2000-01-01', 'test_name_1'),
	('course_test_2', 'description_test_2', 121, '2000-01-02', 'test_name_2'),
	('course_test_3', 'description_test_3', 112, '2000-01-03', 'test_name_3'),
	('course_test_4', 'description_test_4', 211, '2000-01-04', 'test_name_4'),
	('course_test_5', 'description_test_5', 131, '2000-01-05', 'test_name_5'),
	('course_test_6', 'description_test_6', 113, '2000-01-06', 'test_name_6');

INSERT INTO requests (courses_id, users_id, status) 
VALUES ((SELECT id FROM courses WHERE title = 'course_test_1'), (SELECT id FROM users WHERE name = 'Yauheni' AND last_name ='Hlaholeu'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_2'), (SELECT id FROM users WHERE name = 'Uladzislau' AND last_name ='Solovev'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_3'), (SELECT id FROM users WHERE name = 'Lana' AND last_name ='Dimidova'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_4'), (SELECT id FROM users WHERE name = 'Andrey' AND last_name ='Aksenov'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_5'), (SELECT id FROM users WHERE name = 'Haliana' AND last_name ='Sidoric'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_6'), (SELECT id FROM users WHERE name = 'Nazar' AND last_name ='Vahtongov'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_6'), (SELECT id FROM users WHERE name = 'Tatyana' AND last_name ='Minikova'), 'IN_PROCESSING');

INSERT INTO lessons (courses_id, title) 
VALUES ((SELECT id FROM courses WHERE title = 'course_test_1'), 'test_lesson_title_1'),
((SELECT id FROM courses WHERE title = 'course_test_1'), 'test_lesson_title_2'),
((SELECT id FROM courses WHERE title = 'course_test_2'), 'test_lesson_title_3'),
((SELECT id FROM courses WHERE title = 'course_test_3'), 'test_lesson_title_4'),
((SELECT id FROM courses WHERE title = 'course_test_4'), 'test_lesson_title_5');
>>>>>>> LMS-2: Add SQL scheme & data
