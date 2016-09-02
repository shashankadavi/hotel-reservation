#datapopulation.sql
#
# 64 Rooms
# 16 floors
# 4 types
#
insert into roomType (typeId, name, description, numBeds, bedTypes, amenities, price, view) values 
("1","Single","Great room for anyone who wants a luxury stay at an affordable price.","1","Queen","Standard","100","Parking lot"),
("2","Double","Great room for a group that wants a luxury stay at an affordable price.","2","Queen","Standard","150","Waterfront"),
("3","Executive","Room for those that want to stay in style and comfort.","1","King","Executive","250","Vegas Strip"),
("4","Presidential","Room for anyone who is ready to live like a monarch.","2","California King","Executive++","500","Vegas");
insert into room (roomNum,roomType) values 
("201","1"), ("202","1"), ("203","1"), ("204","1"), ("205","1"), ("206","1"), 
("301","1"), ("302","1"), ("303","1"), ("304","1"), ("305","1"), ("306","1"),
("401","1"), ("402","1"), ("403","1"), ("404","1"), ("405","1"), ("406","1"),
("501","1"), ("502","1"), ("503","1"), ("504","1"), ("505","1"), ("506","1"),
("207","2"), ("208","2"), ("209","2"), ("210","2"), ("211","2"), ("212","2"),
("307","2"), ("308","2"), ("309","2"), ("310","2"), ("311","2"), ("312","2"),
("407","2"), ("408","2"), ("409","2"), ("410","2"), ("411","2"), ("412","2"),
("507","2"), ("508","2"), ("509","2"), ("510","2"), ("511","2"), ("512","2"),
("601","3"), ("602","3"), ("603","3"), ("604","3"),
("701","3"), ("702","3"), 
("803","3"), ("804","3"),
 
("900","4"), 
("1000","4"), 
("1100","4"),
("1200","4"), 
("1300","4"), 
("1400","4"), 
("1500","4"),
("1600","4");