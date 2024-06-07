package id.ac.astra.polytechnic.internak.ui.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notificationList;

    public NotificationAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.textNotificationTitle.setText(notification.getTitle());
        holder.textNotificationDesc.setText(notification.getDescription());
        if (notification.isRead()) {
            holder.iconNotification.setImageResource(R.drawable.ic_mail_read);
        } else {
            holder.iconNotification.setImageResource(R.drawable.ic_mail_unread);
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView iconNotification;
        TextView textNotificationTitle;
        TextView textNotificationDesc;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            iconNotification = itemView.findViewById(R.id.icon_notification);
            textNotificationTitle = itemView.findViewById(R.id.text_notification_title);
            textNotificationDesc = itemView.findViewById(R.id.text_notification_desc);
        }
    }
}
