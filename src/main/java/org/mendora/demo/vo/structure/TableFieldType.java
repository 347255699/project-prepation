package org.mendora.demo.vo.structure;

import java.util.regex.Pattern;

public enum TableFieldType {
    /**
     * TableField data type for column in create statement of vo
     */
    TINYINT("TINYINT\\([1-9]\\d*\\)|TINYINT", "4"),
    INT("INT\\([1-9]\\d*\\)|INT|INTEGER\\([1-9]\\d*\\)|INTEGER", "11"),
    BIGINT("BIGINT\\([1-9]\\d*\\)|BIGINT", "20"),
    DECIMAL("DECIMAL\\([1-9]\\d*,\\s?\\d*\\)", ""),
    CHAR("CHAR\\([1-9]\\d*\\)", ""),
    VARCHAR("VARCHAR\\([1-9]\\d*\\)", ""),
    TEXT("TEXT", "");

    private String regex;

    private String length;

    TableFieldType(String regex, String length) {
        this.regex = regex;
        this.length = length;
    }

    public String getLength(){
        return this.length;
    }

    public boolean matches(String str) {
        return Pattern.compile(this.regex).matcher(str).matches();
    }

    public static String regexAll(){
        final StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < values().length; i++) {
            if (i == values().length - 1) {
                regexBuilder.append(values()[i].regex);
            } else {
                regexBuilder.append(values()[i].regex).append("|");
            }
        }
        return regexBuilder.toString();
    }

    public static boolean matchesAll(String str) {

        return Pattern.compile(regexAll()).matcher(str).matches();
    }

    public static void main(String[] args) {
        String data = "INT(0)";
        System.out.println("result: " + TableFieldType.matchesAll(data));
    }
}
