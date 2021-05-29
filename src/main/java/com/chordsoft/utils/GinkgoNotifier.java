package com.chordsoft.utils;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

public class GinkgoNotifier {
    private static final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Ginkgo Action Notification", NotificationDisplayType.TOOL_WINDOW, true);

    public static void notify(@Nullable Project project, String content) {
        NOTIFICATION_GROUP.createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }
}
