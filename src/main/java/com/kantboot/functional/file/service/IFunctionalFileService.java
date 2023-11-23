package com.kantboot.functional.file.service;

import com.kantboot.functional.file.domain.entity.FunctionalFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理的Service接口
 * 用于管理文件的上传、下载、删除等
 * @author 方某方
 */
public interface IFunctionalFileService {

    /**
     * 上传文件(需要指定文件组编码)
     * @param file 文件
     * @param groupCode 文件组编码
     * @return 文件
     */
    FunctionalFile upload(MultipartFile file, String groupCode);

    /**
     * 根据id查询文件
     * @param id 文件id
     * @return 文件
     */
    FunctionalFile getById(Long id);

    /**
     * 查看文件
     * @param id 文件id
     * @return 文件
     */
    ResponseEntity<FileSystemResource> visit(Long id);

}
