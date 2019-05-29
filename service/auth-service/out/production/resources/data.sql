INSERT INTO authority  VALUES(1,'ROLE_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_USER');

INSERT INTO users(username, password, enabled) VALUES('admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', true);
INSERT INTO users(username, password, enabled) VALUES('user','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', true);

INSERT INTO users_authorities VALUES(1,1);
INSERT INTO users_authorities VALUES(2,2);

INSERT INTO oauth_client_details(client_id, scope, authorized_grant_types) VALUES('browser', 'ui', 'refresh_token,password');
INSERT INTO oauth_client_details(client_id, client_secret, scope, authorized_grant_types) VALUES('account-service', '$2a$12$okW.BmhwmU9kjTTIeY9ZqOE74oNdoDBynNjV6/.DfOPTDu92/NsNG', 'server', 'refresh_token,client_credentials');
