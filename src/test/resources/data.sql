INSERT INTO `user` (
    `id`,
    `dni_number`,
    `email`,
    `first_name`,
    `last_name`,
    `password`,
    `phone`,
    `birth_date`,
    `token_password`
)
VALUES
    (
        '1',
        '123',
        'email@some.com',
        'Name',
        'Perez',
        '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S',
        '1234567890',
        '1995-03-22',
        NULL
    );


INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_USER', 'ROLE_USER');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
