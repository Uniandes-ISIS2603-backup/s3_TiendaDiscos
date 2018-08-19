delete from BookEntity;
delete from EditorialEntity;

insert into EditorialEntity (id, name) values (100,'Oveja Negra');
insert into EditorialEntity (id, name) values (200,'Siruela');

insert into BookEntity (id, name, isbn, image, description, publishDate, editorial_id) values (100, 'The Lord of the Rings', '930330149-8', 'https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg', 'Supplement R Tympanic Membrane with Synth Sub, Via Opening', '8/20/1996',100);
insert into BookEntity (id, name, isbn, image, description, publishDate, editorial_id) values (200, 'Harry Potter and the Sorcerer´s Stone', '507119915-7', 'http://m.cdn.blog.hu/ko/kockagyar/image/harry_potter_poster/harry_potter_1.jpg', 'Occlusion of Right Femoral Artery, Percutaneous Approach', '2/19/2014',100);
insert into BookEntity (id, name, isbn, image, description, publishDate, editorial_id) values (300, 'A Game of Thrones', '279453624-9', 'http://t1.gstatic.com/images?q=tbn:ANd9GcQEV8WgR73kXg3mpoFrUiOHaX9eUxe5K7Z4sN-u-ORABH8nwIm4', 'Removal of Spacer from T-lum Jt, Perc Approach', '4/7/1998',100);
insert into BookEntity (id, name, isbn, image, description, publishDate, editorial_id) values (400, 'The Winds of Winter', '744706866-7', 'http://www.darkmediaonline.com/wp-content/uploads/2013/01/WindsofWinter.jpg', 'Reposition Left Femoral Shaft, Perc Endo Approach', '10/10/1998',200);
insert into BookEntity (id, name, isbn, image, description, publishDate, editorial_id) values (500, 'The Slow Regard of Silent Things', '260760424-9', 'http://www.patrickrothfuss.com/images/page/cover-slow-regard_277.jpg', 'Supplement Lower Artery with Autol Sub, Perc Approach', '5/9/2013',200);
insert into BookEntity (id, name, isbn, image, description, publishDate, editorial_id) values (600, 'Harry Potter and the Philosopher´s Stone', '260760424-9', 'https://katemacdonalddotnet.files.wordpress.com/2015/11/potter-1-4.jpg', 'disse accumsan tortor quis turp Perc Approach', '5/9/2013',200);

