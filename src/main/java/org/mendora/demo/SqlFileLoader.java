package org.mendora.demo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mendora.util.PathUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@RequiredArgsConstructor
public class SqlFileLoader {
    /**
     * Have to start with '/'
     */
    @NonNull
    private String relativePath;

    public String read() throws Exception {
        final File file = new File(PathUtil.root(this.getClass()) + relativePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        bufferedReader.lines()
                .forEach(line -> sb.append(line).append("\n"));
        return sb.toString();
    }

    public static void main(String[] args) {
        SqlFileLoader sqlFileLoader = new SqlFileLoader("/schema.sql");
        try {
            System.out.println(sqlFileLoader.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
