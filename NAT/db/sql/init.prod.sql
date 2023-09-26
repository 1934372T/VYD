ALTER USER 'root'@'localhost' IDENTIFIED BY 'secret', `root`@`localhost` PASSWORD EXPIRE NEVER;
GRANT ALL ON nat.* TO 's3-prod'@'%';