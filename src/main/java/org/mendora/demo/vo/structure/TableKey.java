package org.mendora.demo.vo.structure;

public enum TableKey {
    PRIMARY_KEY("private key", "PRI"),
    UNIQUE_KEY("unique key", "UNI");

    private String name;
    private String val;

    TableKey(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName(){
        return this.name;
    }

    public String getVal(){
        return this.val;
    }
}
