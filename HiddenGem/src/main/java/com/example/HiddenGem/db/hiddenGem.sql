SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS BoardC;
DROP TABLE IF EXISTS BoardF;
DROP TABLE IF EXISTS Menu;
DROP TABLE IF EXISTS Reply;
DROP TABLE IF EXISTS Users;




/* Create Tables */

CREATE TABLE BoardC
(
	cid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	title varchar(256) NOT NULL,
	content varchar(4000),
	modTime datetime DEFAULT CURRENT_TIMESTAMP,
	isDeleted int DEFAULT 0,
	viewCount int DEFAULT 0,
	likeCount int DEFAULT 0,
	uname varchar(16),
	PRIMARY KEY (cid)
);


CREATE TABLE BoardF
(
	fid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	title varchar(40) NOT NULL,
	foodCategory varchar(12),
	opening int,
	location varchar(20),
	tel varchar(15),
	info varchar(50),
	modTime datetime DEFAULT CURRENT_TIMESTAMP,
	isDeleted int DEFAULT 0,
	viewCount int DEFAULT 0,
	likeCount int DEFAULT 0,
	replyCount int DEFAULT 0,
	mid int NOT NULL,
	rid int NOT NULL,
	PRIMARY KEY (fid)
);


CREATE TABLE Menu
(
	mid int NOT NULL AUTO_INCREMENT,
	food varchar(10),
	price int DEFAULT 0,
	foodTitle varchar(16),
	PRIMARY KEY (mid)
);


CREATE TABLE Reply
(
	rid int NOT NULL AUTO_INCREMENT,
	comment varchar(256) NOT NULL,
	regTime datetime DEFAULT CURRENT_TIMESTAMP,
	uid varchar(12) NOT NULL,
	isMine int DEFAULT 0,
	PRIMARY KEY (rid)
);


CREATE TABLE Users
(
	uid varchar(12) NOT NULL,
	pwd char(50) NOT NULL,
	uname varchar(15) NOT NULL,
	email varchar(30),
	regDate date DEFAULT (CURRENT_DATE),
	isDeleted int DEFAULT 0,
	profile varchar(40),
	access int DEFAULT 0,
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE BoardF
	ADD FOREIGN KEY (mid)
	REFERENCES Menu (mid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BoardF
	ADD FOREIGN KEY (rid)
	REFERENCES Reply (rid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BoardC
	ADD FOREIGN KEY (uid)
	REFERENCES Users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BoardF
	ADD FOREIGN KEY (uid)
	REFERENCES Users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Reply
	ADD FOREIGN KEY (uid)
	REFERENCES Users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



