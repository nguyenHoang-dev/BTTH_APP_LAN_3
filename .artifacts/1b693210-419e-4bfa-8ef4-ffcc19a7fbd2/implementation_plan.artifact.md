# Goal Description
Triển khai ứng dụng Quản lý lịch hẹn theo yêu cầu chi tiết từ file đính kèm. Ứng dụng giúp người dùng đăng nhập, xem danh sách lịch hẹn (có thống kê), thêm lịch hẹn mới, và xem chi tiết/xóa/đổi trạng thái nhắc nhở lịch hẹn. Ứng dụng dùng Java, XML thuần túy và SQLiteOpenHelper để lưu trữ, không dùng framework bên ngoài nào khác. Vì project đang rỗng, tôi sẽ thiết lập một cấu trúc project Android cơ bản (các file gradle, manifest) và triển khai toàn bộ mã nguồn.

## Proposed Changes

### Project Setup
Tạo các tệp tin cấu hình Gradle cơ bản cho project để có thể build được:
#### [NEW] [settings.gradle](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/settings.gradle)
#### [NEW] [build.gradle](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/build.gradle)
#### [NEW] [gradle.properties](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/gradle.properties)
#### [NEW] [app/build.gradle](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/build.gradle)
#### [NEW] [AndroidManifest.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/AndroidManifest.xml)

### Resources (Styles, Colors, Drawables, Layouts)
Thiết lập UI resource theo yêu cầu:
#### [NEW] [colors.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/values/colors.xml)
#### [NEW] [themes.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/values/themes.xml)
#### [NEW] [strings.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/values/strings.xml)
#### [NEW] Drawables (các file custom background bo góc, badge)
#### [NEW] [activity_login.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/layout/activity_login.xml)
#### [NEW] [activity_main.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/layout/activity_main.xml)
#### [NEW] [item_appointment.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/layout/item_appointment.xml)
#### [NEW] [activity_add_appointment.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/layout/activity_add_appointment.xml)
#### [NEW] [activity_appointment_detail.xml](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/res/layout/activity_appointment_detail.xml)

### Database Layer
Cài đặt SQLiteOpenHelper như yêu cầu:
#### [NEW] [Appointment.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/models/Appointment.java)
#### [NEW] [DatabaseHelper.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/db/DatabaseHelper.java)

### UI Components (Activities & Adapters)
Viết các Activity bằng Java:
#### [NEW] [LoginActivity.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/LoginActivity.java)
#### [NEW] [AppointmentAdapter.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/AppointmentAdapter.java)
#### [NEW] [MainActivity.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/MainActivity.java)
#### [NEW] [AddAppointmentActivity.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/AddAppointmentActivity.java)
#### [NEW] [AppointmentDetailActivity.java](file:///C:/Users/Admin/Desktop/BTTH_APP_TH_LAN_3/app/src/main/java/com/example/appointments/AppointmentDetailActivity.java)

## Verification Plan
1. Chạy app (nếu có device), hoặc compile check.
2. Đăng nhập với admin/123456.
3. Xem danh sách hiển thị đúng 3 cuộc hẹn mẫu, thứ tự sắp xếp và tổng kết đúng.
4. Thêm cuộc hẹn mới: validate không để trống, ngày/giờ phải ở tương lai.
5. Xem chi tiết: hiện đúng ID truyền qua Intent.
6. Thay đổi nhắc nhở (Bật/tắt) ở chi tiết sẽ update DB tức thì.
7. Xóa lịch hẹn cập nhật danh sách lập tức khi back về.
