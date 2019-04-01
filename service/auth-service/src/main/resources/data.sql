INSERT INTO authority  VALUES(1,'ROLE_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_USER');

INSERT INTO users VALUES(1,'admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2');
INSERT INTO users VALUES(2,'user','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2');

INSERT INTO users_authorities VALUES(1,1);
INSERT INTO users_authorities VALUES(2,2);

INSERT INTO oauth_client_details(client_id, scope, authorized_grant_types) VALUES('browser', 'ui', 'refresh_token,password');
