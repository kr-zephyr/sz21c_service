CREATE TABLE LGG_MST_LOGRAWDATA (
  ID INT NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  SITE_TYPE VARCHAR(8) NOT NULL COMMENT '사이트 유형(BLTIS:티스토리, BLDEV:개발블로그, SIPRF:프로필)',
  TYPE VARCHAR(8) NOT NULL COMMENT '로그 유형(ONLOAD:페이지로드, DOCEND:문서끝)',
  URL VARCHAR(255) NOT NULL COMMENT '방문 url',
  URL_TITLE VARCHAR(255) COMMENT '방문 페이지 제목',
  REFERRER_URL VARCHAR(1024) COMMENT '방문 경로',
  REFERRER_IP VARCHAR(64) COMMENT '방문자 식별키',
  LOCALE VARCHAR(8) COMMENT '국가정보',
  USER_AGENT VARCHAR(255) COMMENT '접속자 user-agent',
  CREATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '로그 등록일시',
  PRIMARY KEY (ID)
);