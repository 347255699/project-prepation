package org.mendora.demo;

import org.mendora.demo.vo.keyword.Statement;

import java.sql.SQLSyntaxErrorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private static final String STATEMENT_END_REGEX = ".*;$";
    private Pattern endPattern;

    private CreateTableStatementParser createTableStatementParser;

    /**
     * into what statement now
     */
    private Statement whatStatement;

    public Tokenizer() {
        this.endPattern = Pattern.compile(STATEMENT_END_REGEX);
    }

    public void process(String line) throws SQLSyntaxErrorException {
        final Matcher startMather = Statement.matches(line);
        if (startMather.matches()) {
            String statementKeyword = null;
            for (int i = 1; i <= Statement.count(); i++) {
                if (startMather.group(i) != null) {
                    statementKeyword = startMather.group(i);
                    break;
                }
            }
            // find statement type
            whatStatement = Statement.valOf(statementKeyword);
        } else {
            if (whatStatement == null) {
                throw new SQLSyntaxErrorException("line");
            }
        }

        if (whatStatement == Statement.CREATE_TABLE) {
            createTableStatementParser.parse(line);
        }

        final Matcher endMather = endPattern.matcher(line);
        if (endMather.find()) {
            // last statement
            whatStatement = null;
        }
    }
}
