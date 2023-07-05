package org.test.common.model.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private String key;
    private int page;
    private int pageSize;
    private int pageStart;

    public int getPageStart() {
        return (page - 1) * pageSize;
    }
}
