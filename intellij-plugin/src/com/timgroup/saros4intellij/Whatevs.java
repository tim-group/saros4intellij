package com.timgroup.saros4intellij;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import org.jetbrains.annotations.NotNull;

public class Whatevs implements ApplicationComponent {
    public Whatevs() {
    }

    public void initComponent() {
        Writer.write("app component loaded");
        FileEditorManager.getInstance().addFileEditorManagerListener();
    }

    public void disposeComponent() {
        Writer.write("app component disposed");
    }

    @NotNull
    public String getComponentName() {
        return "Whatevs";
    }
}
