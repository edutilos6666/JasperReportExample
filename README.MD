This project requires local running mysql server with username=root, password =root
<br>
**In mysql client cli do followings**
<br>
<code>
<pre>
#create database test2
create schemas test2;

#create table Person
create table if not exists Person (
    id bigint not null primary key,
    name varchar(50) not null,
    age int not null,
    wage double not null,
    active boolean not null);

#populate table Person
insert into Person VALUES 
(1, 'foo', 10, 100.0, true),
(2, 'bar', 20, 200.0, false),
(3, 'bim', 30, 300.0, true),
(4, 'pako', 40, 400.0, false),
(5, 'deko', 50, 500.0, true),
(6, 'leo', 60, 600.0, false),
(7, 'messi', 70, 700.0, true);
</pre>
</code>