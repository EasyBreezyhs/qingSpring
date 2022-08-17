package com.qingspring.demo.utils.mapstruct;

import com.qingspring.demo.entity.Filesdb;
import com.qingspring.demo.entity.Vo.FilesdbVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper
public interface FilesMapping {
    FilesMapping INSTANCE = Mappers.getMapper(FilesMapping.class);

    @Mappings({
            @Mapping(target = "name", source = "name")
            // 多个属性不对应可以用 "，" 隔开编写多个@Mapping
            // ,@Mapping(target = "uname", source = "sname")
    })
    FilesdbVo filesdbToVo(Filesdb filesdb);

    List<FilesdbVo> filesdbToVoList(List<Filesdb> filesdbs);

}
