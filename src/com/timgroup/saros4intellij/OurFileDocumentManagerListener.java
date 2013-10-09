package com.timgroup.saros4intellij;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManagerAdapter;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class OurFileDocumentManagerListener extends FileDocumentManagerAdapter {
    @Override
    public void fileContentLoaded(@NotNull VirtualFile file, @NotNull Document document) {
        Writer.write("file opened " + file.getCanonicalPath());
    }

    static {
        Writer.write("i got loaded!!");
    }
}
