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
+ Database

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

+ Model Users, Orders


