-- 用户表
CREATE TABLE users(
id INT(32) PRIMARY KEY  AUTO_INCREMENT,
email VARCHAR(32) UNIQUE NOT NULL,
username VARCHAR(32),
PASSWORD VARCHAR(255),
phoneNum VARCHAR(20),
STATUS INT
);
INSERT INTO users VALUES(2,'security@security.com','security','$2a$10$jj36gqUKsto7kirff5sd2OyaiYGAqcdj/pSn.zdBHdM1EJ94r9qCq','qwsa242526',1);

-- 角色表
CREATE TABLE role(
id INT(32) PRIMARY KEY  AUTO_INCREMENT,
roleName VARCHAR(50),
roleDesc VARCHAR(50)
);

INSERT INTO role VALUE(1,"ADMIN","可以用任何权限");
INSERT INTO role VALUE(2,"USER","有部分权限");


-- 资源表
CREATE TABLE permission(
id INT(32) PRIMARY KEY  AUTO_INCREMENT,
permissionName VARCHAR(50) ,
url VARCHAR(50)
);
INSERT INTO permission VALUES(1,'user findAll','/user/findAll');

-- 用户角色表(中间表！)
-- 用户角色关联表
CREATE TABLE users_role(
userId INT(32),
roleId INT(32),
PRIMARY KEY(userId,roleId),
FOREIGN KEY (userId) REFERENCES users(id),
FOREIGN KEY (roleId) REFERENCES role(id)
);

-- 自己关联数据
INSERT INTO users_role VALUES(2,1);
INSERT INTO users_role VALUES(2,2);

-- 角色权限关联表
CREATE TABLE role_permission(
permissionId INT(32),
roleId INT(32),
PRIMARY KEY(permissionId,roleId),
FOREIGN KEY (permissionId) REFERENCES permission(id),
FOREIGN KEY (roleId) REFERENCES role(id)
);

-- 自己关联数据
INSERT INTO role_permission VALUES(1,1);
INSERT INTO role_permission VALUES(1,2);


