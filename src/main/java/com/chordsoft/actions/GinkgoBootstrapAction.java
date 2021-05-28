package com.chordsoft.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GinkgoBootstrapAction extends AnAction {
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
        e.getPresentation().setEnabledAndVisible(psiElement instanceof PsiDirectory);
    }
}
