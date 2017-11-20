DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

  INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2017-11-15 10:00:00', 'Завтрак', '500', '100000'),
  ('2017-11-15 13:00:00', 'Обед', '1500', '100000'),
  ('2017-11-15 20:00:00', 'Ужин', '700', '100000'),
  ('2017-11-16 10:00:00', 'Завтрак', '400', '100000'),
  ('2017-11-16 13:00:00', 'Обед', '1300', '100000'),
  ('2017-11-16 20:00:00', 'Ужин', '400', '100000'),
  ('2017-11-15 10:00:00', 'АдминЗавтрак', '500', '100001'),
  ('2017-11-15 13:00:00', 'АдминОбед', '2500', '100001');

