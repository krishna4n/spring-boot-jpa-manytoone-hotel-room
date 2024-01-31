create table if not exists hotel(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), location VARCHAR(255), rating VARCHAR(255));
create table if not exists room(id INT PRIMARY KEY AUTO_INCREMENT, roomNumber VARCHAR(255),type VARCHAR(255),
 price VARCHAR(255), hotelid INT, FOREIGN KEY(hotelid) references hotel(id));
