package org.mendora.demo.vo.keyword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Statement {
    /**
     * Statement in vo statement
     */
    USE("(USE) .+", "USE"),
    CREATE_TABLE("(CREATE TABLE) .+", "CREATE TABLE"),
    CREATE_DATABASE("(CREATE DATABASE) .+", "CREATE DATABASE"),
    ALTER_TABLE("(ALTER TABLE) .+", "ALTER TABLE");

    private String regex;

    private String val;

    Statement(String regex, String val) {
        this.regex = regex;
        this.val = val;
    }

    public static Statement valOf(String val) {
        for (Statement keyword : values()) {
            if (keyword.getVal().equals(val)) {
                return keyword;
            }
        }
        return null;
    }

    public String getregex() {
        return this.regex;
    }

    public String getVal() {
        return this.val;
    }

    public static int count() {
        return values().length;
    }

    public static Matcher matches(String str) {
        final StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < values().length; i++) {
            if (i == values().length - 1) {
                regexBuilder.append(values()[i].regex);
            } else {
                regexBuilder.append(values()[i].regex).append("|");
            }
        }
        final String finalRegex = regexBuilder.toString();
        return Pattern.compile(finalRegex).matcher(str);
    }
}
