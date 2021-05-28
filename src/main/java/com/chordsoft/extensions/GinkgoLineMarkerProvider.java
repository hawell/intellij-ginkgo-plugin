package com.chordsoft.extensions;

import com.goide.psi.GoReferenceExpression;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.NotNull;


public class GinkgoLineMarkerProvider implements LineMarkerProvider {
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        Class<? extends PsiElement> e = element.getClass();
        Class<? extends PsiElement> p = element.getParent().getClass();
        if (element instanceof LeafPsiElement && element.getParent() instanceof GoReferenceExpression) {
            if (element.textMatches("Describe")) {
                return new LineMarkerInfo<>(element, element.getTextRange(), AllIcons.Actions.RunAll, null, null, GutterIconRenderer.Alignment.CENTER);
            } else if (element.textMatches("It")) {
                return new LineMarkerInfo<>(element, element.getTextRange(), AllIcons.Actions.Execute, null, null, GutterIconRenderer.Alignment.CENTER);
            }
        }
        return null;
    }
}
