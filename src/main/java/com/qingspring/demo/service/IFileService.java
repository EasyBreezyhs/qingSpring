package com.qingspring.demo.service;

import com.qingspring.demo.entity.Filesdb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingspring.demo.entity.Vo.FilesdbVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-28
 */
public interface IFileService extends IService<Filesdb> {

    String fileUpload(MultipartFile file) throws IOException;

    Boolean download(String fileUuid, HttpServletResponse response) throws IOException;

    List<FilesdbVo> findAllInFront();
}
