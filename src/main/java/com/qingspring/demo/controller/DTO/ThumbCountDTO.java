package com.qingspring.demo.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>qingspring</h3>
 * <p>Thumb</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 15:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThumbCountDTO {

    private String id;

    private String commentId;

    private Integer count;


}
