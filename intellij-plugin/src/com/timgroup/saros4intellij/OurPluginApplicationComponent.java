package com.timgroup.saros4intellij;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.ex.ActionManagerEx;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class OurPluginApplicationComponent implements ApplicationComponent {
    private final Set<Project> projectSet;
    private final AnActionListener myAnActionListener;

    public OurPluginApplicationComponent() {
        projectSet = new HashSet<Project>();

        myAnActionListener = new AnActionListener.Adapter() {
            @Override
            public void beforeActionPerformed(AnAction action, DataContext dataContext, AnActionEvent event) {
                // Add a listener to the action associated with this project, if not already done
                Project project = PlatformDataKeys.PROJECT.getData(dataContext);
                synchronized (this) {
                    if (project != null && !projectSet.contains(project)) {
                        projectSet.add(project);
                        FileEditorManager myFileEditorManager = FileEditorManager.getInstance(project);
                        myFileEditorManager.addFileEditorManagerListener(new OurFileEditorManagerListener());
                    }
                }
            }
        };
    }

    public void initComponent() {
        ActionManagerEx.getInstanceEx().addAnActionListener(myAnActionListener);
        Writer.write("-- app component init completed");
    }

    public void disposeComponent() {
        ActionManagerEx.getInstanceEx().removeAnActionListener(myAnActionListener);
        Writer.write("-- app component disposed");
    }

    @NotNull
    public String getComponentName() {
        return "OurPluginApplicationComponent";
    }
}
