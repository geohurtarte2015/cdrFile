CREATE OR REPLACE TRIGGER trigger_cdr_after_insert
AFTER INSERT
   ON TB_FILE_CDR_MERKA
   FOR EACH ROW

DECLARE 
VALID_STATUS varchar2(50);

BEGIN

   VALID_STATUS := :new.STATUS_CDR;   
   
  IF  VALID_STATUS='READ' THEN

   -- Insert record into audit table
   INSERT INTO TB_CDR_MERKA
   ( ID,     
     SEQUENCE_NUMBER,
     NAME_FILE,
     CDR_DATE_CREATE,
     CDR_DATE_PROCESSED,
     DESTINATION_NUMBER,
     SUBSCRIBER_ID,
     ID_FILE,
     STATUS_CDR     
    )
   VALUES
   ( SEQ_CDR_MERKA.nextval,
     :new.SEQUENCE_NUMBER,
     :new.NAME_FILE,
     :new.CDR_DATE_CREATE,
     :new.CDR_DATE_PROCESSED,
     :new.DESTINATION_NUMBER,
     :new.SUBSCRIBER_ID, 
     :new.ID,
     :new.STATUS_CDR     
      );
   END IF;   
      

END;

delete from TB_FILE_CDR_MERKA;
delete from TB_CDR_MERKA;
delete from TB_FILE;

drop table TB_FILE_CDR_MERKA;
drop table TB_CDR_MERKA;
drop table TB_FILE;



CREATE TABLE TB_FILE
(
ID number primary key not null,
NAME_FILE varchar2(150) null,
DATE_READ varchar2(150) null
);


CREATE TABLE TB_FILE_CDR_MERKA
(
ID number primary key not null,
SEQUENCE_NUMBER varchar2(150) null,
NAME_FILE varchar2(150) null,
CDR_DATE_CREATE varchar2(150) null,
CDR_DATE_PROCESSED varchar2(150) null,
DESTINATION_NUMBER varchar2(150) null,
SUBSCRIBER_ID varchar2(150) null,
STATUS_CDR varchar2(50) null
);


CREATE TABLE TB_CDR_MERKA
(
ID number primary key not null,
SEQUENCE_NUMBER varchar2(150) null,
NAME_FILE varchar2(150) null,
CDR_DATE_CREATE varchar2(150) null,
CDR_DATE_PROCESSED varchar2(150) null,
DESTINATION_NUMBER varchar2(150) null,
SUBSCRIBER_ID varchar2(150) null,
ID_FILE varchar2(150) null,
STATUS_CDR varchar2(50) null
);

drop sequence SEQ_FILE_CDR_MERKA;
drop sequence SEQ_CDR_MERKA;
drop sequence SEQ_FILE;

create sequence SEQ_FILE_CDR_MERKA minvalue 1 start with 1 increment by 1 cache 10; 

create sequence SEQ_FILE minvalue 1 start with 1 increment by 1 cache 10; 

create sequence SEQ_CDR_MERKA minvalue 1 start with 1 increment by 1 cache 10; 

INSERT INTO TB_FILE (ID,NAME_FILE,DATE_READ)
VALUES (SEQ_FILE.nextval,'file.txt','22/06/2016');

INSERT INTO TB_FILE_CDR_MERKA (ID,SEQUENCE_NUMBER,NAME_FILE, CDR_DATE_CREATE, CDR_DATE_PROCESSED,DESTINATION_NUMBER,SUBSCRIBER_ID,STATUS_CDR)
VALUES (SEQ_FILE_CDR_MERKA.nextval,'4564','name.txt', '14/12/2016', '16/02/2016','555444','54856308','READ');

select * from TB_FILE_CDR_MERKA;
select * from TB_CDR_MERKA;
select * from TB_FILE;



