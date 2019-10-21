package org.mendora.demo;

import com.sun.tools.javac.util.List;
import org.mendora.demo.vo.structure.TableField;
import org.mendora.demo.vo.structure.TableFieldType;
import org.mendora.demo.vo.structure.TableStructure;

import java.sql.SQLSyntaxErrorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTableStatementParser {

    private TableStructure tableStructure;

    private TableField tableField;

    private List<TableStructure> tableStuctures;

    private String[] regexs;

    private int lineNumber;

    public CreateTableStatementParser() {
        regexs = new String[]{
                "CREATE",
                "TABLE",
                "'.+'|^\\d+(?=[a-zA-Z_$]+)[a-zA-Z_$0-9]*|^[a-zA-Z_$]+[a-zA-Z_$0-9]*",
                "([A-Z_$0-9]*)\\s?\\(\\s?([A-Z_$0-9]*)",
                TableFieldType.regexAll(),
                "ID\\s+INT\\s+NOT\\s+NULL\\s+AUTO_INCREMENT",
                "COMMENT\\s*'.*'",
                "PRIMARY\\s+KEY(ID)",
                "UNIQUE\\s+KEY\\s(.*)\\((.*)\\s,\\s(.*)\\s\\)",
                "^\\)ENGINE=INNODB\\sDEFAULT\\sCHARSET=UTF8MB4\\s*;\\s"
        };
    }

    public void parseToken(String token) {
//        if(StrUtil.isEmpty(token)){
//            return;
//        }
//        final Matcher matcher = patterns[parseUnit].matcher(token);
//        if (startStatementMatcher.find()) {
//            if (parseUnit != 0) {
//                throw new SQLSyntaxErrorException(line);
//            }
//            tableStructure = new TableStructure();
//            parseUnit++;
//            parse(startStatementMatcher.group(1));
//        }
//
//        final Matcher tableNameMatcher = patterns[1].matcher(line);
//        if (tableNameMatcher.find()) {
//            if (parseUnit != 1) {
//                throw new SQLSyntaxErrorException(line);
//            }
//            parseUnit++;
//            final String tableName = tableNameMatcher.group(1) != null ? tableNameMatcher.group(1) : tableNameMatcher.group(3);
//            tableStructure.setName(tableName);
//            parse(tableNameMatcher.group(2) != null ? tableNameMatcher.group(2) : tableNameMatcher.group(4));
//        }
//
//        final Matcher startBodyMatcher = patterns[2].matcher(line);
//        if (startBodyMatcher.find()) {
//            if (parseUnit != 2) {
//                throw new SQLSyntaxErrorException(line);
//            }
//            parseUnit++;
//            tableStructure.setBracketLeft("(");
//            parse(startBodyMatcher.group());
//        }
    }

    public void parse(String line) throws SQLSyntaxErrorException {
        lineNumber++;
        for (String token : line.split("\\s")) {
            parseToken(token);
        }
    }

    public static void main(String[] args) {
        String data = "a123";
        Matcher matcher = Pattern.compile("^\\d+(?=\\D+).*|^\\D+.*").matcher(data);
        System.out.println(matcher.matches());
    }
}
