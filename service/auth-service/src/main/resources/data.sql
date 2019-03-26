INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_PRODUCT_ADMIN');

INSERT INTO users VALUES(1,'admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2');

INSERT INTO users_authorities VALUES(1,1);

INSERT INTO oauth_client_details(client_id, scope, authorized_grant_types) VALUES('browser', 'ui', 'refresh_token,password');