INSERT INTO feed (feed_id,category,title,content,user_id,view_count,pick_count,image)
VALUES (1,'CAREER','피드1 제목입니다.','피드1 내용입니다',1,0,0,'http://server/feeds/images/1.jpg');

INSERT INTO feed_mindset (mindset_id, content, feed_id)
VALUES (1, '피드1의 마인드셋1', 1);

INSERT INTO feed_mindset (mindset_id, content, feed_id)
VALUES (2, '피드1의 마인드셋2', 1);

INSERT INTO feed (feed_id,category,title,content,user_id,view_count,pick_count,image)
VALUES (2,'CAREER','피드2 제목입니다.','피드2 내용입니다',1,0,0,'http://server/feeds/images/2.jpg');