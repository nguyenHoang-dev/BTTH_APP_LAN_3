package com.example.appointments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appointments.db.DatabaseHelper;
import com.example.appointments.models.Appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppointmentDetailActivity extends AppCompatActivity {

    private ImageView btnBackToolbar;
    private TextView tvDetailTitle, tvDetailLocation, tvDetailTime, tvDetailReminderStatus;
    private Switch switchDetailReminder;
    private Button btnBack, btnDelete;
    private DatabaseHelper dbHelper;
    private int appointmentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        dbHelper = new DatabaseHelper(this);

        btnBackToolbar = findViewById(R.id.btnBackToolbar);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailLocation = findViewById(R.id.tvDetailLocation);
        tvDetailTime = findViewById(R.id.tvDetailTime);
        tvDetailReminderStatus = findViewById(R.id.tvDetailReminderStatus);
        switchDetailReminder = findViewById(R.id.switchDetailReminder);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        appointmentId = getIntent().getIntExtra("id", -1);
        if (appointmentId != -1) {
            loadData(appointmentId);
        }

        btnBackToolbar.setOnClickListener(v -> finish());
        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            dbHelper.deleteAppointment(appointmentId);
            finish();
        });
    }

    private void loadData(int id) {
        Appointment a = dbHelper.getAppointmentById(id);
        if (a != null) {
            tvDetailTitle.setText(a.getTitle());
            tvDetailLocation.setText(a.getLocation());

            try {
                SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date dateObj = dbDateFormat.parse(a.getDate());
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String displayDate = displayDateFormat.format(dateObj);
                tvDetailTime.setText(displayDate + " · " + a.getTime());
            } catch (ParseException e) {
                tvDetailTime.setText(a.getDate() + " · " + a.getTime());
            }

            boolean isReminderOn = a.getReminder() == 1;
            
            // Remove listener before setting state to avoid double triggering
            switchDetailReminder.setOnCheckedChangeListener(null);
            switchDetailReminder.setChecked(isReminderOn);
            switchDetailReminder.setOnCheckedChangeListener((buttonView, isChecked) -> {
                dbHelper.updateReminder(appointmentId, isChecked);
                updateReminderStatusText(isChecked);
            });
            
            updateReminderStatusText(isReminderOn);
        }
    }

    private void updateReminderStatusText(boolean isOn) {
        if (isOn) {
            tvDetailReminderStatus.setText("Đang bật");
            tvDetailReminderStatus.setTextColor(getResources().getColor(R.color.green_dark));
        } else {
            tvDetailReminderStatus.setText("Đang tắt");
            tvDetailReminderStatus.setTextColor(getResources().getColor(R.color.gray));
        }
    }
}
