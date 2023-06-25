CREATE TABLE teacher (
                         teacher_id  INTEGER PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
);

CREATE TABLE student (
                         student_id  INTEGER PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
);

CREATE TABLE teacher_student (
                                 id PRIMARY KEY,
                                 student_id INTEGER REFERENCES student(student_id)
                                 teacher_id INTEGER REFERENCES teacher(teacher_id),

);

INSERT INTO teacher (first_name,last_name, email) VALUES
                                               ('weidong', 'wang','weidong@gmail.com'),
                                               ('qingqiu', 'zhang','qingqiu@yahoo.com'),
                                               ('mengku', 'chen','mengku@gmail.com'),
                                               ('jingqian', 'cheng','jingqian@163.com'),
                                               ('danqiu', 'fu','danqiu@outlook.com');

INSERT INTO student (first_name,last_name, email) VALUES
                                               ('ted', 'wang','ted@gmail.com'),
                                               ('alisa', 'chou','alisa@yahoo.com'),
                                               ('alex', 'hu','alex@139.com'),
                                               ('bob', 'li','bob@136.com'),
                                               ('jack', 'wu','jack@qq.com');


INSERT INTO teacher_student (teacher_id, student_id) VALUES
                                                         (1, 1),
                                                         (1, 2),
                                                         (1, 5),
                                                         (2, 2),
                                                         (2, 3),
                                                         (2, 4),
                                                         (3, 1),
                                                         (3, 2),
                                                         (3, 3),
                                                         (4, 1),
                                                         (4, 4),
                                                         (5, 5),
                                                         (5, 4),
                                                         (5, 3);