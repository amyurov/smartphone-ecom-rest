package com.amyurov.smartphoneecom.response;

import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ContentResponse {

    private List<?> content;
}
