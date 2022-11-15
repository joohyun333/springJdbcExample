CREATE TABLE Employee(
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  branch VARCHAR(20),
  CONSTRAINT Employee_PK PRIMARY KEY(id)
);

insert into Employee(name, branch) values("this is client_B", "B1");