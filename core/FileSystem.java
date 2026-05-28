package core;

import java.util.List;
import entity.*;
import permissions.Permission;

public class FileSystem {

    private static FileSystem instance;
    private Directory root;

    private FileSystem() {
        this.root = new Directory("root", new Permission(true, true, true, true, true));
    }

    public static FileSystem getInstance() {
        if(instance == null)
            instance = new FileSystem();
        return instance;
    }

    public void mkdir(String path, Permission permission) {

        int lastIndex = path.lastIndexOf("/");
        if(lastIndex <= 0)
            throw new RuntimeException("Invalid path");

        String directoryName = path.substring(lastIndex + 1);

        Directory parent = PathResolver.resolveParent(root, path);

        Directory newDirectory = FileSystemFactory.createDirectory(directoryName, permission);
        
        parent.addChild(newDirectory);
    }

    public void touch(String path, Permission permission, String content, String fileType) {
        int lastIndex = path.lastIndexOf("/");
        if(lastIndex <= 0)
            throw new RuntimeException("Invalid path");

        String fileName = path.substring(lastIndex + 1);

        Directory parent = PathResolver.resolveParent(root, path);

        File newFile = FileSystemFactory.createFile(fileName, permission, content, fileType);

        parent.addChild(newFile);    
    }

    public void rm(String path) {

    }

    public String cat(String path) {
        return "";
    }

    public void write(String path, String content) {

    }

    public List<FileSystemEntity> ls(String path) {
        return null;
    }

    public void undo(String path) {

    }

    public void redo(String path) {
        
    }
}