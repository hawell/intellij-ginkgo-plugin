package com.chordsoft.actions;

import com.chordsoft.utils.GinkgoCommandProvider;
import com.chordsoft.utils.GinkgoNotifier;
import com.goide.GoFileType;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ScriptRunnerUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GinkgoGenerateAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        @NotNull DataContext dataContext = e.getDataContext();
        @Nullable PsiFile psiFile = (PsiFile) dataContext.getData(LangDataKeys.PSI_ELEMENT);
        assert psiFile != null;
        assert psiFile.getParent() != null;
        GeneralCommandLine commandLine = new GeneralCommandLine(GinkgoCommandProvider.Generate(e.getProject(), psiFile.getName()))
                .withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.SYSTEM)
                .withWorkDirectory(psiFile.getParent().getVirtualFile().getPath());
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            String output = "";
            try {
                output = ScriptRunnerUtil.getProcessOutput(commandLine);
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
            GinkgoNotifier.notify(e.getProject(), output);
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        @NotNull DataContext dataContext = e.getDataContext();
        @Nullable PsiElement psiElement = dataContext.getData(LangDataKeys.PSI_ELEMENT);
        if (psiElement instanceof PsiFile) {
            PsiFile psiFile = (PsiFile)psiElement;
            @NotNull FileType fileType = psiFile.getFileType();
            e.getPresentation().setEnabledAndVisible(fileType instanceof GoFileType && !psiFile.getName().contains("_test"));
        } else {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }
}
