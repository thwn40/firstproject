package com.sjboard.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class BoardSearchRequestDTO {


private String type;
private String keyword;


}
