package com.timgroup.saros4intellij;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class OurPluginProjectComponent implements ProjectComponent {
    private final Project myProject;
    private final FileEditorManager myFileEditorManager;

    /** Invoked by reflection */
    public OurPluginProjectComponent(Project project, FileEditorManager fileEditorManager) {
        myProject = project;
        //myFileEditorManager = FileEditorManager.getInstance(myProject);
        myFileEditorManager = fileEditorManager;
        //Writer.write("constructor called");
//        MyFileEditorManagerListener listener = new MyFileEditorManagerListener();

    }

    @NotNull
    public String getComponentName() {
        return "OurPluginProjectComponent";
    }

    public void initComponent() {
        Writer.write("set project: " + myProject.getName());
        myFileEditorManager.addFileEditorManagerListener(new OurFileEditorManagerListener(), myProject);
        Writer.write("-- project component init completed");
    }

    public void disposeComponent() {
        Writer.write("-- app component disposed");
    }

    @Override
    public void projectOpened() {
        Writer.write("opened project: " + myProject.getName());
    }

    @Override
    public void projectClosed() {
        Writer.write("closed project: " + myProject.getName());
    }
}
