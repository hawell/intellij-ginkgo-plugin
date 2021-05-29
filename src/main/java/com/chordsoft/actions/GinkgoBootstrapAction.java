package com.chordsoft.actions;

import com.chordsoft.utils.GinkgoCommandProvider;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ScriptRunnerUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GinkgoBootstrapAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        @NotNull DataContext dataContext = e.getDataContext();
        @Nullable PsiDirectory psiDirectory = (PsiDirectory) dataContext.getData(LangDataKeys.PSI_ELEMENT);
        assert psiDirectory != null;
        String directoryPath = (psiDirectory).getVirtualFile().getPath();
        GeneralCommandLine commandLine = new GeneralCommandLine(GinkgoCommandProvider.BootStrap(e.getProject()))
            .withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.SYSTEM)
            .withWorkDirectory(directoryPath);
        String output = "";
        try {
            output = ScriptRunnerUtil.getProcessOutput(commandLine);
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        @NotNull DataContext dataContext = e.getDataContext();
        @Nullable PsiElement psiElement = dataContext.getData(LangDataKeys.PSI_ELEMENT);
        e.getPresentation().setEnabledAndVisible(psiElement instanceof PsiDirectory);
    }
}
