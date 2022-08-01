package com.qingspring.demo.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.entity.Filesdb;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.mapper.FileMapper;
import com.qingspring.demo.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-28
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Filesdb> implements IFileService {

    @Autowired
    private FileMapper fileMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;


    @Override
    public String fileUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        String type = FileUtil.extName(originalFilename);
        String md5 = SecureUtil.md5(file.getInputStream());

//        大于3mb抛出异常
        if (size>3*1024*1024){
            throw new ServiceException(ResponseEnum.FILE_EXCEED);
        }
//          不是图片抛出异常
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png"));
        if (!allowSuffix.contains(type)){
            throw  new ServiceException(ResponseEnum.FILE_ISPIC);
        }

//        判断文件是否存在 存在直接返回 该数据的url
        Filesdb dbFile = getFileByMD5(md5);
        if (dbFile!=null){
            dbFile.setId(null);
            if (!dbFile.getName().equals(originalFilename)){
                dbFile.setName(originalFilename);
                fileMapper.insert(dbFile);
            }
            return dbFile.getUrl();
        }
//    疑问 是否判断 数据库中没有 而磁盘有的情况


//      创建目录
        File uploadParentFile = new File(fileUploadPath);
        if (!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
//      生成uuid，给上传文件命名
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT+type;
        File uploadFile = new File(fileUploadPath+fileUuid);
//      转储图片
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(ResponseEnum.UPLOAD_FAIL);
        }

        String url = getUrl()+"/file/"+fileUuid;


//        存入数据库
        Filesdb saveFilesdb = new Filesdb();
        saveFilesdb.setName(originalFilename);
        saveFilesdb.setSize(size/1024);
        saveFilesdb.setType(type);
        saveFilesdb.setUuid(fileUuid);
        saveFilesdb.setUrl(url);
        saveFilesdb.setMd5(md5);

        fileMapper.insert(saveFilesdb);

        return url;
    }

    @Override
    public Boolean download(String fileUuid, HttpServletResponse response) throws IOException {
        File uploadFile = new File(fileUploadPath+fileUuid);

            ServletOutputStream os = response.getOutputStream();
            //设置输出格式
            response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileUuid,"UTF-8"));
            response.setContentType("application/octet-stream");

            //输出字节流
            os.write(FileUtil.readBytes(uploadFile));
            os.flush();
            os.close();
        return true;
    }

    @Value("${server.port}")
    private int serverPort;

    private String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert address != null;
        return "http://"+address.getHostAddress() +":"+this.serverPort;
    }


    private Filesdb getFileByMD5(String md5){
        QueryWrapper<Filesdb> queryWrapper = new QueryWrapper<Filesdb>();
        queryWrapper.eq("md5",md5);

        return getOne(queryWrapper);
    }


}
