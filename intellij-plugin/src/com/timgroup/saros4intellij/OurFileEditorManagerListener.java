package com.timgroup.saros4intellij;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.vfs.VirtualFile;

public class OurFileEditorManagerListener extends FileEditorManagerAdapter {
    @Override
    public void fileOpened(FileEditorManager source, VirtualFile file) {
        Writer.write("file opened " + file.getCanonicalPath());
    }
}
