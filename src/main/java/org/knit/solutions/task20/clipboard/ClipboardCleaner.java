package org.knit.solutions.task20.clipboard;

import org.springframework.stereotype.Service;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ClipboardCleaner {

    private final ClipboardService clipboardService;

    public ClipboardCleaner(ClipboardService clipboardService) {
        this.clipboardService = clipboardService;
    }

    public void scheduleClipboardClear(long delayMillis) {
        Executors.newSingleThreadScheduledExecutor()
                .schedule(clipboardService::clearClipboard, delayMillis, TimeUnit.MILLISECONDS);
    }
}
