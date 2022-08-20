
CREATE TABLE "AnswerItem"(
    "AnswerItemID"      serial         PRIMARY KEY,
    "Type"              varchar(20)    DEFAULT '' NOT NULL,
    "ItemNumber"        int            DEFAULT -1 NOT NULL,
    "AnswerNumber"      varchar(50)    DEFAULT -1 NOT NULL,
    "Answer"            text           DEFAULT '' NOT NULL,
    "Memo"              text           DEFAULT '' NOT NULL,
    "ContentID"         int            DEFAULT -1 NOT NULL,
    "Allow"             varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"      int            NOT NULL 
) 
;


CREATE TABLE "Class"(
    "ClassID"           serial         PRIMARY KEY,
    "Name"              varchar(50)    DEFAULT '' NOT NULL,
    "Info"              text           DEFAULT '' NOT NULL,
    "Allow"             varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"      int            NOT NULL 
) 
;


CREATE TABLE "Content"(
    "ContentID"         serial          PRIMARY KEY, 
    "FileSize"          int             DEFAULT -1 NOT NULL,
    "Name"              varchar(50)     DEFAULT '' NOT NULL,
    "DocURL"            varchar(200)    DEFAULT '' NOT NULL,
    "Info"              text            DEFAULT '' NOT NULL,
    "State"             varchar(20)     DEFAULT '' NOT NULL,
    "Type"              varchar(20)     DEFAULT '' NOT NULL,
    "HasAnswerItem"     varchar(20)     DEFAULT '' NOT NULL,
    "Allow"             varchar(20)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)      DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)      DEFAULT '' NOT NULL,
    "CreateBy"          int             NOT NULL,
    "LastModifyDate"    varchar(8)      DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)      DEFAULT '' NOT NULL,
    "LastModifyBy"      int             NOT NULL 
 
) 
;


CREATE TABLE "Course"(
    "CourseID"          serial         PRIMARY KEY, 
    "CourseName"        varchar(50)    DEFAULT '' NOT NULL,
    "Teacher"           int            NOT NULL,
    "StartDate"         varchar(8)     DEFAULT '' NOT NULL,
    "EndDate"           varchar(8)     DEFAULT '' NOT NULL,
    "Info"              text           DEFAULT '' NOT NULL,
    "State"             varchar(20)    DEFAULT '' NOT NULL,
    "Allow"             varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"      int            NOT NULL 
) 
;


CREATE TABLE "CourseStudent"(
    "CourseStudentID"    serial         PRIMARY KEY, 
    "Student"            int            NOT NULL,
    "CourseID"           int            NOT NULL,
    "Allow"              varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"         varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"         varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"           int            NOT NULL,
    "LastModifyDate"     varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"     varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"       int            NOT NULL 
) 
;


CREATE TABLE "ExaminationScore"(
    "ExaminationID"     serial         PRIMARY KEY, 
    "ProjectID"         int            NOT NULL,
    "Student"           int            NOT NULL,
    "Score"             int            DEFAULT -1 NOT NULL,
    "Allow"             varchar(20)    DEFAULT ''NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT ''NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT ''NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT ''NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT ''NOT NULL,
    "LastModifyBy"      int            NOT NULL
) 
;


CREATE TABLE "HomeWork"(
    "HomeWorkID"        serial         PRIMARY KEY, 
    "Student"           int            NOT NULL,
    "ProjectID"         int            NOT NULL,
    "Allow"             varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"      int            NOT NULL 
) 
;


CREATE TABLE "HomeWorkContent"(
    "HomeWorkContentID"    serial          PRIMARY KEY, 
    "DocURL"               varchar(200)    DEFAULT '' NOT NULL,
    "State"                varchar(20)     DEFAULT '' NOT NULL,
    "SubmitDate"           varchar(8)      DEFAULT '' NOT NULL,
    "SubmitTime"           varchar(6)      DEFAULT '' NOT NULL,
    "PassDate"             varchar(8)      DEFAULT '' NOT NULL,
    "PassTime"             varchar(6)      DEFAULT '' NOT NULL,
    "HomeWorkID"           int             NOT NULL,
    "Allow"                varchar(20)     DEFAULT '' NOT NULL,
    "CreateDate"           varchar(8)      DEFAULT '' NOT NULL,
    "CreateTime"           varchar(6)      DEFAULT '' NOT NULL,
    "CreateBy"             int             NOT NULL,
    "LastModifyDate"       varchar(8)      DEFAULT '' NOT NULL,
    "LastModifyTime"       varchar(6)      DEFAULT '' NOT NULL,
    "LastModifyBy"         int             NOT NULL 
) 
;


CREATE TABLE "Project"(
    "ProjectID"         serial         PRIMARY KEY, 
    "Name"              varchar(50)    DEFAULT '' NOT NULL,
    "CourseID"          int            NOT NULL,
    "Info"              text           DEFAULT '' NOT NULL,
    "State"             varchar(20)    DEFAULT '' NOT NULL,
    "Type"              varchar(20)    DEFAULT '' NOT NULL,
    "PublishResult"     varchar(20)    DEFAULT '' NOT NULL,
    "StartDate"         varchar(8)     DEFAULT '' NOT NULL,
    "EndDate"           varchar(8)     DEFAULT '' NOT NULL,
    "Allow"             varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"      int            NOT NULL 
) 
;


CREATE TABLE "ProjectContent"(
    "ProjectContentID"    serial         PRIMARY KEY, 
    "ContentID"           int            DEFAULT -1 NOT NULL,
    "ProjectID"           int            NOT NULL,
    "Allow"               varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"          varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"          varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"            int            NOT NULL,
    "LastModifyDate"      varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"      varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"        int            NOT NULL 
)  
;


CREATE TABLE "SchoolResourse"(
    "SchoolResourseID"    serial          PRIMARY KEY, 
    "Name"                varchar(50)     DEFAULT '' NOT NULL,
    "DocURL"              varchar(200)    DEFAULT '' NOT NULL,
    "SubjectID"           int             NOT NULL,
    "StartDate"           varchar(8)      DEFAULT '' NOT NULL,
    "EndDate"             varchar(8)      DEFAULT '' NOT NULL,
    "Allow"               varchar(20)     DEFAULT '' NOT NULL,
    "CreateDate"          varchar(8)      DEFAULT '' NOT NULL,
    "CreateTime"          varchar(6)      DEFAULT '' NOT NULL,
    "CreateBy"            int             NOT NULL,
    "LastModifyDate"      varchar(8)      DEFAULT '' NOT NULL,
    "LastModifyTime"      varchar(6)      DEFAULT '' NOT NULL,
    "LastModifyBy"        int             NOT NULL 
) 
;

CREATE TABLE "ScoreStatistic"(
    "ScoreStatisticID"    serial         PRIMARY KEY, 
    "Student"             int            NOT NULL,
    "AnswerItemID"        int            NOT NULL,
    "ProjectContentID"    int            NOT NULL,
    "Statistic"           varchar(50)    DEFAULT '' NOT NULL,
    "RightAnswer"         int            DEFAULT -1 NOT NULL,
    "WrongAnswer"         int            DEFAULT -1 NOT NULL,
    "Allow"               varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"          varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"          varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"            int            NOT NULL,
    "LastModifyDate"      varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"      varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"        int            NOT NULL 
) 
;


CREATE TABLE "Subject"(
    "SubjectID"         serial         PRIMARY KEY, 
    "Name"              varchar(50)    DEFAULT '' NOT NULL,
    "Allow"             varchar(20)    DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)     DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)     DEFAULT '' NOT NULL,
    "CreateBy"          int            NOT NULL,
    "LastModifyDate"    varchar(8)     DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)     DEFAULT '' NOT NULL,
    "LastModifyBy"      int            NOT NULL 
) 
;


CREATE TABLE "TestResultItem"(
    "TestResultItemID"    serial           PRIMARY KEY, 
    "Student"             int              NOT NULL,
    "IsRight"               varchar(20)    DEFAULT '' NOT NULL,
    "AnswerMark"          varchar(20)      DEFAULT '' NOT NULL,
    "ExaminationID"       int              NOT NULL,
    "Answer"              text             DEFAULT '' NOT NULL,
    "AnswerItemID"        int              NOT NULL,
    "Allow"               varchar(20)      DEFAULT '' NOT NULL,
    "CreateDate"          varchar(8)       DEFAULT '' NOT NULL,
    "CreateTime"          varchar(6)       DEFAULT '' NOT NULL,
    "CreateBy"            int              NOT NULL,
    "LastModifyDate"      varchar(8)       DEFAULT '' NOT NULL,
    "LastModifyTime"      varchar(6)       DEFAULT '' NOT NULL,
    "LastModifyBy"        int              NOT NULL  
) 
;


CREATE TABLE "Users"(
    "UserID"            serial            PRIMARY KEY, 
    "LoginName"         varchar(50)       DEFAULT '' NOT NULL,
    "Password"          varchar(10)       DEFAULT '' NOT NULL,
    "Name"              varchar(50)       DEFAULT '' NOT NULL,
    "Gender"            varchar(20)       DEFAULT '' NOT NULL,
    "Birthday"          varchar(8)        DEFAULT '' NOT NULL,
    "Tel"               varchar(50)       DEFAULT '' NOT NULL,
    "Email"             varchar(200)      DEFAULT '' NOT NULL,
    "Address"           varchar(200)      DEFAULT '' NOT NULL,
    "ClassID"           int               DEFAULT -1 NOT NULL,
    "UserType"          varchar(20)       DEFAULT '' NOT NULL,
    "State"             varchar(20)       DEFAULT '' NOT NULL,
    "Allow"             varchar(20)       DEFAULT '' NOT NULL,
    "CreateDate"        varchar(8)        DEFAULT '' NOT NULL,
    "CreateTime"        varchar(6)        DEFAULT '' NOT NULL,
    "CreateBy"          int               DEFAULT -1 NOT NULL,
    "LastModifyDate"    varchar(8)        DEFAULT '' NOT NULL,
    "LastModifyTime"    varchar(6)        DEFAULT '' NOT NULL,
    "LastModifyBy"      int               DEFAULT -1 NOT NULL 
) 
;

CREATE TABLE "StudentExamContent"(
    "StudentExamContentID"	serial            PRIMARY KEY, 
    "UserID"			int		  NOT NULL,
    "ProjectContentID"		int               NOT NULL,
    "Allow"			varchar(20)       DEFAULT '' NOT NULL,
    "CreateDate"		varchar(8)        DEFAULT '' NOT NULL,
    "CreateTime"		varchar(6)        DEFAULT '' NOT NULL,
    "CreateBy"			int               DEFAULT -1 NOT NULL,
    "LastModifyDate"		varchar(8)        DEFAULT '' NOT NULL,
    "LastModifyTime"		varchar(6)        DEFAULT '' NOT NULL,
    "LastModifyBy"		int               DEFAULT -1 NOT NULL 
)
;
ALTER TABLE "StudentExamContent" ADD CONSTRAINT "StudentExamContent_UserID_Users"
    FOREIGN KEY ("UserID")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "StudentExamContent" ADD CONSTRAINT "StudentExamContent_ProjectContentID_ProjectContent"
    FOREIGN KEY ("ProjectContentID")
    REFERENCES "ProjectContent"("ProjectContentID")
;

ALTER TABLE "StudentExamContent" ADD CONSTRAINT "StudentExamContent_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "StudentExamContent" ADD CONSTRAINT "StudentExamContent_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")


ALTER TABLE "AnswerItem" ADD CONSTRAINT "AnswerItem_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "AnswerItem" ADD CONSTRAINT "AnswerItem_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "AnswerItem" ADD CONSTRAINT "AnswerItem_ContentID_Content"  
    FOREIGN KEY ("ContentID")
    REFERENCES "Content"("ContentID")
;



ALTER TABLE "Class" ADD CONSTRAINT "Class_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "Class" ADD CONSTRAINT "Class_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;



ALTER TABLE "Content" ADD CONSTRAINT "Content_CreateBy_Users"
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "Content" ADD CONSTRAINT "Content_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;



ALTER TABLE "Course" ADD CONSTRAINT "Course_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "Course" ADD CONSTRAINT "Course_CreateBy_Users"
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "Course" ADD CONSTRAINT "Course_Teacher_Users"
    FOREIGN KEY ("Teacher")
    REFERENCES "Users"("UserID")
;



ALTER TABLE "CourseStudent" ADD CONSTRAINT "CourseStudent_Student_Users"
    FOREIGN KEY ("Student")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "CourseStudent" ADD CONSTRAINT "CourseStudent_CourseID_Course"
    FOREIGN KEY ("CourseID")
    REFERENCES "Course"("CourseID")
;

ALTER TABLE "CourseStudent" ADD CONSTRAINT "CourseStudent_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "CourseStudent" ADD CONSTRAINT "CourseStudent_CreateBy_Users"
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;


ALTER TABLE "ExaminationScore" ADD CONSTRAINT "ExaminationScore_ProjectID_Project"
    FOREIGN KEY ("ProjectID")
    REFERENCES "Project"("ProjectID")
;

ALTER TABLE "ExaminationScore" ADD CONSTRAINT "ExaminationScore_Student_Users" 
    FOREIGN KEY ("Student")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "ExaminationScore" ADD CONSTRAINT "ExaminationScore_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "ExaminationScore" ADD CONSTRAINT "ExaminationScore_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;


ALTER TABLE "HomeWork" ADD CONSTRAINT "HomeWork_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "HomeWork" ADD CONSTRAINT "HomeWork_ProjectID_Project"
    FOREIGN KEY ("ProjectID")
    REFERENCES "Project"("ProjectID")
;

ALTER TABLE "HomeWork" ADD CONSTRAINT "HomeWork_CreateBy_Users"
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "HomeWork" ADD CONSTRAINT "HomeWork_Student_Users"
    FOREIGN KEY ("Student")
    REFERENCES "Users"("UserID")
;


ALTER TABLE "HomeWorkContent" ADD CONSTRAINT "HomeWorkContent_HomeWorkID_HomeWork"
    FOREIGN KEY ("HomeWorkID")
    REFERENCES "HomeWork"("HomeWorkID")
;

ALTER TABLE "HomeWorkContent" ADD CONSTRAINT "HomeWorkContent_LastModifyBy_Users"
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "HomeWorkContent" ADD CONSTRAINT "HomeWorkContent_CreateBy_Users"
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;



ALTER TABLE "Project" ADD CONSTRAINT "Project_CourseID_Course"
    FOREIGN KEY ("CourseID")
    REFERENCES "Course"("CourseID")
;

ALTER TABLE "Project" ADD CONSTRAINT "Project_CreateBy_Users"
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "Project" ADD CONSTRAINT "Project_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;



ALTER TABLE "ProjectContent" ADD CONSTRAINT "ProjectContent_ContentID_Content" 
    FOREIGN KEY ("ContentID")
    REFERENCES "Content"("ContentID")
;

ALTER TABLE "ProjectContent" ADD CONSTRAINT "ProjectContent_ProjectID_Project" 
    FOREIGN KEY ("ProjectID")
    REFERENCES "Project"("ProjectID")
;

ALTER TABLE "ProjectContent" ADD CONSTRAINT "ProjectContent_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "ProjectContent" ADD CONSTRAINT "ProjectContent_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;


ALTER TABLE "SchoolResourse" ADD CONSTRAINT "SchoolResourse_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "SchoolResourse" ADD CONSTRAINT "SchoolResourseCreateBy_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "SchoolResourse" ADD CONSTRAINT "SchoolResourse_SubjectID_Subject" 
    FOREIGN KEY ("SubjectID")
    REFERENCES "Subject"("SubjectID")
;



ALTER TABLE "ScoreStatistic" ADD CONSTRAINT "ScoreStatistic_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "ScoreStatistic" ADD CONSTRAINT "ScoreStatistic_Student_Users" 
    FOREIGN KEY ("Student")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "ScoreStatistic" ADD CONSTRAINT "ScoreStatistic_AnswerItemID_AnswerItem" 
    FOREIGN KEY ("AnswerItemID")
    REFERENCES "AnswerItem"("AnswerItemID")
;

ALTER TABLE "ScoreStatistic" ADD CONSTRAINT "ScoreStatistic_ProjectContentID_ProjectContent" 
    FOREIGN KEY ("ProjectContentID")
    REFERENCES "ProjectContent"("ProjectContentID")
;

ALTER TABLE "ScoreStatistic" ADD CONSTRAINT "ScoreStatistic_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;


ALTER TABLE "Subject" ADD CONSTRAINT "Subject_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "Subject" ADD CONSTRAINT "Subject_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;



ALTER TABLE "TestResultItem" ADD CONSTRAINT "TestResultItem_AnswerItemID_AnswerItem" 
    FOREIGN KEY ("AnswerItemID")
    REFERENCES "AnswerItem"("AnswerItemID")
;

ALTER TABLE "TestResultItem" ADD CONSTRAINT "TestResultItem_LastModifyBy_Users" 
    FOREIGN KEY ("LastModifyBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "TestResultItem" ADD CONSTRAINT "TestResultItem_CreateBy_Users" 
    FOREIGN KEY ("CreateBy")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "TestResultItem" ADD CONSTRAINT "TestResultItem_Student_Users" 
    FOREIGN KEY ("Student")
    REFERENCES "Users"("UserID")
;

ALTER TABLE "TestResultItem" ADD CONSTRAINT "TestResultItem_ExaminationID_ExaminationScore" 
    FOREIGN KEY ("ExaminationID")
    REFERENCES "ExaminationScore"("ExaminationID")
;


INSERT INTO "Users" 
("LoginName", "Password", "Name", "Gender", "Birthday", 
"Tel", "Email", "Address", "ClassID", "UserType", "State", 
"Allow", "CreateDate", "CreateTime", "CreateBy", "LastModifyDate","LastModifyTime", "LastModifyBy") 
VALUES 
('admin', 'admin', '系统管理员', '男', '20010914',
'010-65660182', 'dc@angelengineers.com.cn', '北京朝阳区东环南路2号 招商局大厦18F', '-1', '管理员', '正式',
'允许', '20010914', '111000', '-1', '20010914', '111000', '-1')
;

CREATE USER "eschool" WITH PASSWORD 'eschool' CREATEDB CREATEUSER 
;

GRANT ALL ON "AnswerItem" TO "eschool"
;
GRANT ALL ON "Class" TO "eschool"
;
GRANT ALL ON "Content" TO "eschool"
;
GRANT ALL ON "Course" TO "eschool"
;
GRANT ALL ON "CourseStudent" TO "eschool"
;
GRANT ALL ON "ExaminationScore" TO "eschool"
;
GRANT ALL ON "HomeWork" TO "eschool"
;
GRANT ALL ON "HomeWorkContent" TO "eschool"
;
GRANT ALL ON "Project" TO "eschool"
;
GRANT ALL ON "ProjectContent" TO "eschool"
;
GRANT ALL ON "SchoolResourse" TO "eschool"
;
GRANT ALL ON "ScoreStatistic" TO "eschool"
;
GRANT ALL ON "Subject" TO "eschool"
;
GRANT ALL ON "TestResultItem" TO "eschool"
;
GRANT ALL ON "Users" TO "eschool"
;
 
