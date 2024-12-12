package com.ran.ams.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Data
@Builder
public class WebDataResponse<T> {
    private int code;
    private String status;
    private T data;
}
