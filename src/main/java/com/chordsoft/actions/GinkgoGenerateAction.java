package com.chordsoft.actions;

import com.goide.GoFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GinkgoGenerateAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
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
