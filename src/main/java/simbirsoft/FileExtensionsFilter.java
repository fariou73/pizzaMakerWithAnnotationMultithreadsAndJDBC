package simbirsoft;

import java.io.*;

public class FileExtensionsFilter implements FilenameFilter {
    private final String endWith;

    public FileExtensionsFilter(String str) {
        endWith = str;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(endWith);
    }
}
