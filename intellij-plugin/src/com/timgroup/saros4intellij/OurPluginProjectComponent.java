package com.timgroup.saros4intellij;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.timgroup.saros4intellij.proxy.*;
import com.timgroup.saros4intellij.proxy.server.RestService;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OurPluginProjectComponent implements ProjectComponent {
    private final Project myProject;
    private final FileEditorManager myFileEditorManager;

    private static final int INTELLIJ_PORT = 7374;

    private final Executor sarosServerExecutor = Executors.newSingleThreadExecutor();

    /** Invoked by reflection */
    public OurPluginProjectComponent(Project project, FileEditorManager fileEditorManager) {
        myProject = project;
        myFileEditorManager = fileEditorManager;
    }

    @NotNull
    public String getComponentName() {
        return "OurPluginProjectComponent";
    }

    public void initComponent() {
        Writer.write("set project: " + myProject.getName());
        myFileEditorManager.addFileEditorManagerListener(new OurFileEditorManagerListener(myProject), myProject);
        Writer.write("-- project component init completed");
    }

    public void disposeComponent() {
        Writer.write("-- app component disposed");
    }

    @Override
    public void projectOpened() {
        Writer.write("opened project: " + myProject.getName());
        sarosServerExecutor.execute(new Runnable() {
            @Override
            public void run() {
                new RestService(INTELLIJ_PORT, new SysoutNavigator(), new SysoutEditor()).start();
            }
        });
    }

    @Override
    public void projectClosed() {
        Writer.write("closed project: " + myProject.getName());
    }
}
