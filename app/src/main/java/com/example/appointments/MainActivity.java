package com.example.appointments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appointments.db.DatabaseHelper;
import com.example.appointments.models.Appointment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotalCount, tvReminderCount;
    private RecyclerView recyclerView;
    private Button btnAdd;
    private DatabaseHelper dbHelper;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        tvTotalCount = findViewById(R.id.tvTotalCount);
        tvReminderCount = findViewById(R.id.tvReminderCount);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAppointmentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        // Cập nhật thống kê
        int total = dbHelper.countAll();
        int reminders = dbHelper.countReminders();
        tvTotalCount.setText(String.valueOf(total));
        tvReminderCount.setText(String.valueOf(reminders));

        // Cập nhật danh sách
        List<Appointment> list = dbHelper.getAllAppointments();
        adapter = new AppointmentAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}
