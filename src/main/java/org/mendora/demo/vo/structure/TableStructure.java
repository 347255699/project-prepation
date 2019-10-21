package org.mendora.demo.vo.structure;

import lombok.Data;

import java.util.Map;

@Data
public class TableStructure {
    private String name;

    private String bracketLeft;

    private String bracketRight;

    private Map<String, TableField> fields;

    private Map<String, TableIndex> indexList;
}
