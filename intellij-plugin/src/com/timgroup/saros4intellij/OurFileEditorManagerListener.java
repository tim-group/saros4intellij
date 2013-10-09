package com.timgroup.saros4intellij;

import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;

public class OurFileEditorManagerListener extends FileEditorManagerAdapter {

//    @Override
//    public void fileOpened(FileEditorManager source, VirtualFile file) {
//        Writer.write("file opened: " + file.getCanonicalPath());
//    }

    @Override
    public void selectionChanged(FileEditorManagerEvent event) {
        if (event.getNewFile() != null && event.getNewFile().getCanonicalPath() != null)
            Writer.write("selection changed: " + event.getNewFile().getCanonicalPath());
    }
}
