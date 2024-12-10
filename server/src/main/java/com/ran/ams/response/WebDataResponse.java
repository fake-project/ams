package com.ran.ams.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebDataResponse {
    private int code;
    private String status;
    private Object data;
}
