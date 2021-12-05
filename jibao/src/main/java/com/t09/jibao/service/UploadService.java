package com.t09.jibao.service;
import com.t09.jibao.domain.Upload;

public interface UploadService {
    Upload save(Upload upload);

    Upload findById(Long id);

    Upload findByGid(Long gid);
}
