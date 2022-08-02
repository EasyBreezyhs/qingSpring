package com.qingspring.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.utils.JWT.LoginToken;
import com.qingspring.demo.utils.JWT.PassToken;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.qingspring.demo.service.IFileService;
import com.qingspring.demo.entity.Filesdb;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-28
 */
@RestController
@RequestMapping("/file")
@LoginToken
public class FileController {


    @Resource
    private IFileService fileService;


    @PostMapping("/upload")
    private Result upload(@RequestParam MultipartFile file){

        String url = null;
        try {
            url = fileService.fileUpload(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(ResponseEnum.ERROR);
        }

        return Result.success(url);

    }


    @GetMapping("/download/{fileUuid}")
    public Result download(@PathVariable String fileUuid, HttpServletResponse response){
        boolean downloadFlag = false;
        try {
            downloadFlag = fileService.download(fileUuid,response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(ResponseEnum.ERROR);
        }
        return Result.success(downloadFlag);
    }





    //查询所有
    @GetMapping
    public Result findAll(){
        return Result.success(fileService.list());
    }

//    通过id查询
//    @GetMapping("/{fileUnid}")
//    public String findOne(@PathVariable String fileUnid){
//        QueryWrapper<Filesdb> fq = new QueryWrapper<>();
//        fq.eq("uuid",fileUnid);
//        Filesdb one = fileService.getOne(fq);
//
//        return one.getUrl();
//    }

//    @PostMapping("/update")
//    public Result update(@RequestBody Filesdb files) {
//        fileService.getBaseMapper().updateById(files);
//        flushRedis(Constants.FILES_KEY);
//        return Result.success();
//    }



    //      逻辑删除
    //通过id删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        Filesdb filesdb = fileService.getBaseMapper().selectById(id);
        filesdb.setIsDelete(true);
        fileService.getBaseMapper().updateById(filesdb);
        return Result.success();
        }

    //批量删除
    @PostMapping("/del/Batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        QueryWrapper<Filesdb> fq = new QueryWrapper<>();
        fq.in("id",ids);
        List<Filesdb> filesdbList = fileService.getBaseMapper().selectList(fq);
        for (Filesdb filesdb : filesdbList) {
            filesdb.setIsDelete(true);
            fileService.getBaseMapper().updateById(filesdb);
        }
        return Result.success();
    }

    //分页
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String filename){

        Page<Filesdb> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Filesdb> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_delete",false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(filename)){
            queryWrapper.like("name",filename);
        }

        return Result.success(fileService.page(page,queryWrapper));
    }






}

