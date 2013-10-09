package com.timgroup.saros4intellij;

import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.project.Project;
import com.timgroup.saros4intellij.proxy.Navigator;
import com.timgroup.saros4intellij.proxy.Position;
import com.timgroup.saros4intellij.proxy.client.HttpClientNavigator;

public class OurFileEditorManagerListener extends FileEditorManagerAdapter {

    private final Navigator navigator = new HttpClientNavigator("10.108.13.37", 7373);
    private final Project project;

    public OurFileEditorManagerListener(Project project) {

        this.project = project;
    }

//    @Override
//    public void fileOpened(FileEditorManager source, VirtualFile file) {
//        Writer.write("file opened: " + file.getCanonicalPath());
//    }

    @Override
    public void selectionChanged(FileEditorManagerEvent event) {
        if (event.getNewFile() != null && event.getNewFile().getCanonicalPath() != null) {
            Writer.write("selection changed: " + event.getNewFile().getCanonicalPath());
            String filePath = projectRelativeFilePath(event);

            Writer.write("new file project relative path: " + filePath);
            navigator.goTo(filePath, new Position(17));
        }


    }

    private String projectRelativeFilePath(FileEditorManagerEvent event) {
        return event.getNewFile().getCanonicalPath().replace(project.getBasePath(), "");

    }
}
