# Phần mềm quản lý Thư viện
## Các đối tượng sử dụng:
- Độc giả
- Thủ kho
- Thu thư
- Quản lý
## Biểu đồ Use Case tổng quát
![image](https://user-images.githubusercontent.com/69187962/174274599-2ccddf54-f5bb-48dd-bd27-5ba77d24564f.png)

## Yêu cầu hệ thống
- Giúp độc giả tra cứu sách trên các máy tính trạm.
- Cung cấp cho thủ thư các thông tin về các đầu sách một độc giả đang mượn và hạn phải trả; và các cuốn sách còn đang được mượn.
- Thống kê doanh thu: nhập sách, thanh lý sách
- Hỗ trợ thủ thư cập nhật thông tin sách, xác nhận cho mượn sách và nhận lại sách khi độc giả trả sách.
- Hỗ trợ quản lý các thông tin về độc giả dựa trên thẻ thư viện, thông tin thẻ mượn.
- Hỗ trợ quản lý các khâu nhập sách, nhập CSVT, cũng như là thanh lý sách
- Quản lý được vị trí sách trong thư viện
- Hỗ trợ chức năng quản trị chung hệ thống (admin) trong đó người quản trị chung có thể thay đổi thông tin hoặc thêm bớt các nhân viên.

- Các yêu cầu phi chức năng:
  + Độc giả có thể tra cứu thông tin sách và đăng ký mượn trước thông qua ứng dụng của thư viện. Tuy nhiên việc mượn và trả sách phải thực hiện trực tiếp trên Thư viện. Thủ thư sử dụng hệ thống đề cập nhật và quản lý quá trình mượn trả sách.
  + Thông tin thống kê phải đảm bảo tính chính xác, khách quan. Các hình thức phạt với các độc giả quá hạn sẽ được lưu lại và thông báo cho độc giả biết.

## Các công nghệ ứng dụng
- Java swing:
  -	Java Swing là một bộ công cụ lập trình giao diện trong Java cung cấp vô số thành phần dùng để lập trình ứng dụng desktop được phát hành bởi Sun Microsystems.
  -	Thư viện Swing được xây dựng dựa trên Java Abstract Widget Toolkit (AWT) cũng là một bộ công cụ lập trình giao diện cũ được phát triển trước đây. Chúng ta có thể sử dụng các thành phần giao diện như Button, TextBox etc mà không cần phải tạo lại chúng

- Java JDBC: là một java API được sử dụng để kết nối và thực hiện truy vấn với cơ sở dữ liệu. JDBC API sử dụng trình điều khiển jdbc để kết nối với cơ sở dữ liệu.
- Microsoft SQL Server:
  –	Có kiến trúc dữ liệu chặt chẽ và đảm bảo tính nhất quán cao.
  –	Hệ thống giao dịch ít, lượng truy cập ít. (Mượn sách, hay mượn trước ít không giao dịch liên tục)
  –	SQL server có tốc độ đọc nhanh (sử dụng các chức năng tìm kiếm, kiểm tra nhiều)

## Lược đồ phân rã hệ thống con
![image](https://user-images.githubusercontent.com/69187962/174274280-53391608-f506-4593-831c-3842022be473.png)
