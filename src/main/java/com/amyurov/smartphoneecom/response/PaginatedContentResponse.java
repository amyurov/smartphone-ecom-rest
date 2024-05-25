package com.amyurov.smartphoneecom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginatedContentResponse {

    MetaData metaData;
    List<?> content;

    public static PaginatedContentResponse of(Page<?> page) {
        var metadata = new MetaData(page.getNumber(), page.getSize(), page.getTotalPages(), page.getSort().toString());
        return new PaginatedContentResponse(metadata, page.getContent());
    }

    private record MetaData(int page, int size, int totalPages, String sort) {

    }
}
