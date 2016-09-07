# framgia_task1
Project demo framgia
#Yêu cầu: #
+ DB : Tao DB có 2 bảng quan hệ 1-N. User và Order. User là bên 1, Order là bên nhiều. DB dùng postgres 9.4
+ FW : Tạo một dự án dùng Sping MVC + Hibernate.
+ FUNC :
   + UserList: Là list các user
      + Tìm kiếm theo mọi trường trong DB.
       + Add new,Detail, edit, delete user.
      + Popup cho phép thêm, edit, view detail.
      + Delete thì bật lên popup confirm có xóa hay ko.
   + Order list thì cũng tương tự.
  
#Thực hiện #
+ ##Database##

   users      | 
------------- |
Int:id        |
Sring:name    | 

   orders     | 
------------- |
Int:id        |
Sring:name    | 
Int:price     | 
Int:userId    | 
Hai bảng có quan hệ 1-n
+ ##Code##
+  [Users Class](https://github.com/phamnoone/framgia_task1/blob/master/src/java/model/users/Users.java)
   [Users hibernate mapping config ](https://github.com/phamnoone/framgia_task1/blob/master/src/java/model/users/Users.hbm.xml)
+  [Orders Class](https://github.com/phamnoone/framgia_task1/blob/master/src/java/model/orders/Orders.java)
+   [Orders hibernate mapping config ](https://github.com/phamnoone/framgia_task1/blob/master/src/java/model/orders/Orders.hbm.xml)
+  [Hibernate config file ](https://github.com/phamnoone/framgia_task1/blob/master/src/java/config/hibernate.cfg.xml)
+  [user view controller file ](https://github.com/phamnoone/framgia_task1/blob/master/src/java/controller/HomeController.java)  
