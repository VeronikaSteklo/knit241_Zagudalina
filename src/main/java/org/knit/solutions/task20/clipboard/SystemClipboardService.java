package org.knit.solutions.task20.clipboard;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.*;


@Service
public class SystemClipboardService implements ClipboardService {
    private String lastCopied;

    @Override
    public void copyToClipboard(String content) {
        StringSelection selection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        lastCopied = content;
    }

    @Override
    public void clearClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String current = (String) clipboard.getData(DataFlavor.stringFlavor);
            if (current != null && current.equals(lastCopied)) {
                clipboard.setContents(new StringSelection(""), null);
                lastCopied = null;
                System.out.println("Буфер обмена очищен.");
            } else {
                System.out.println("Буфер обмена был изменён вручную — не очищаем.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
