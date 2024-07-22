package com.devstack.lms.feanix.dto.paginate;

import com.devstack.lms.feanix.dto.response.ResponseProductDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseProductPaginateDto {
    private long count;
    private List<ResponseProductDto> dataList;
}
