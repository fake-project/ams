package com.ran.ams.response;

import lombok.Builder;
import lombok.Data;

/**
*  @author Riyan Amanda
*  {@code @linkedin} <a href='https://id.linkedin.com/in/riyan-amanda/in'>...</a>'
*  @since Mon Dec 09 2024
 **/

@Data
@Builder
public class WebResponse {

    private Integer code;
    private String status;
    private String message;
}
