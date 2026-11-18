package com.example.appointments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appointments.db.DatabaseHelper;
import com.example.appointments.models.Appointment;

import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText etTitle, etLocation;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Switch switchReminder;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        dbHelper = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBack);
        etTitle = findViewById(R.id.etTitle);
        etLocation = findViewById(R.id.etLocation);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        switchReminder = findViewById(R.id.switchReminder);
        btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> saveAppointment());
    }

    private void saveAppointment() {
        String title = etTitle.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Không được để trống");
            return;
        }
        if (location.isEmpty()) {
            etLocation.setError("Không được để trống");
            return;
        }

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar selectedCal = Calendar.getInstance();
        selectedCal.set(year, month, day, hour, minute, 0);

        if (selectedCal.getTimeInMillis() <= System.currentTimeMillis()) {
            Toast.makeText(this, "Thời gian hẹn phải ở tương lai", Toast.LENGTH_SHORT).show();
            return;
        }

        String dateStr = String.format("%04d-%02d-%02d", year, month + 1, day);
        String timeStr = String.format("%02d:%02d", hour, minute);

        int reminder = switchReminder.isChecked() ? 1 : 0;

        Appointment a = new Appointment();
        a.setTitle(title);
        a.setLocation(location);
        a.setDate(dateStr);
        a.setTime(timeStr);
        a.setReminder(reminder);

        long id = dbHelper.insertAppointment(a);
        if (id > 0) {
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
