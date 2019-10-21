package org.mendora.demo.vo.structure;

import lombok.Data;

@Data
public class TableField {
    private static final String keyword = "CREATE TABLE";

    private String field;

    private String type;

    private int length;

    private boolean allowNull;

    private TableKey key;

    private String _default;

    private String extra = "None";

    private String comment;
}
