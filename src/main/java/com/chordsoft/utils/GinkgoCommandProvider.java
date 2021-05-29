package com.chordsoft.utils;

import com.goide.project.DefaultGoRootsProvider;
import com.goide.project.GoRootsProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class GinkgoCommandProvider {
    static final GoRootsProvider goRootProvider = new DefaultGoRootsProvider();

    private static String GetGinkgoPath(@Nullable Project project) {
        @NotNull Collection<VirtualFile> binPaths = goRootProvider.getGoPathBinRoots(project, null);
        for (VirtualFile virtualFile: binPaths) {
            @Nullable VirtualFile f = virtualFile.findFileByRelativePath("ginkgo");
            if (f != null) {
                return f.getPath();
            }
        }
        return "ginkgo";
    }

    public static ArrayList<String> BootStrap(@Nullable Project project) {
        ArrayList<String> command = new ArrayList<>();
        command.add(GetGinkgoPath(project));
        command.add("bootstrap");
        return command;
    }

    public static ArrayList<String> Generate(@Nullable Project project, String fileName) {
        ArrayList<String> command = new ArrayList<>();
        command.add(GetGinkgoPath(project));
        command.add("generate");
        command.add(fileName);
        return command;
    }
}
