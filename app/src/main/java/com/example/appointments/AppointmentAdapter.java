package com.example.appointments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appointments.models.Appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> list;

    public AppointmentAdapter(Context context, List<Appointment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment a = list.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvLocation.setText("Địa điểm: " + a.getLocation());

        // Format Date Time
        try {
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date dateObj = dbDateFormat.parse(a.getDate());
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String displayDate = displayDateFormat.format(dateObj);
            holder.tvDateTime.setText(displayDate + " · " + a.getTime());
        } catch (ParseException e) {
            holder.tvDateTime.setText(a.getDate() + " · " + a.getTime());
        }

        if (a.getReminder() == 1) {
            holder.tvReminderBadge.setText("Có nhắc");
            holder.tvReminderBadge.setBackgroundResource(R.drawable.bg_badge_reminder_on);
            holder.tvReminderBadge.setTextColor(context.getResources().getColor(R.color.orange));
        } else {
            holder.tvReminderBadge.setText("Không nhắc");
            holder.tvReminderBadge.setBackgroundResource(R.drawable.bg_badge_reminder_off);
            holder.tvReminderBadge.setTextColor(context.getResources().getColor(R.color.gray));
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AppointmentDetailActivity.class);
            intent.putExtra("id", a.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLocation, tvDateTime, tvReminderBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvReminderBadge = itemView.findViewById(R.id.tvReminderBadge);
        }
    }
}
