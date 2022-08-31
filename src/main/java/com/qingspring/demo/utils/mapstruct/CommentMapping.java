package com.qingspring.demo.utils.mapstruct;


import com.qingspring.demo.entity.Comments;
import com.qingspring.demo.entity.Vo.CommentVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface CommentMapping {
    CommentMapping INSTANCE = Mappers.getMapper(CommentMapping.class);

    CommentVo commentToVo(Comments comments);

    List<CommentVo> commentToVoList(List<Comments> comments);


}
