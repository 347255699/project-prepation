package org.mendora.demo;

import cn.hutool.core.util.StrUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mendora.util.PathUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SqlFileLoader {
    /**
     * Have to start with '/'
     */
    @NonNull
    private String relativePath;

    private Stream<String> load() throws Exception {
        final File file = new File(PathUtil.root(this.getClass()) + relativePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.lines();
    }

    public void parse() throws Exception {
        Tokenizer tokenizer = new Tokenizer();
        List<String> lines = load().filter(StrUtil::isNotEmpty)
                .filter(line -> !line.startsWith("--"))
                .map(String::toUpperCase)
                .map(String::trim)
                .collect(Collectors.toList());

        for (String line : lines) {
            try {
                tokenizer.process(line);
            } catch (SQLSyntaxErrorException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            new SqlFileLoader("/schema.sql").parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
