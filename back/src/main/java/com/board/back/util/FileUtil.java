package com.board.back.util;

import com.board.back.dto.BoardFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileOrgNm) {
        return fileDir + fileOrgNm;
    }

    public String getFullPath(String fileOrgNm, String subDir) {
        return fileDir + subDir + "/" + fileOrgNm;
    }

    public List<BoardFileDto> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles == null) {
            return null;
        }
        List<BoardFileDto> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(saveFile(multipartFile, null));
            }
        }
        return storeFileResult;
    }

    /**
     * 서브 디렉토리 경로가 필요한 경우
     */
    public List<BoardFileDto> saveFiles(List<MultipartFile> multipartFiles, String subDir) throws IOException {
        if (multipartFiles == null) {
            return null;
        }
        List<BoardFileDto> saveFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                saveFileResult.add(saveFile(multipartFile, subDir));
            }
        }
        return saveFileResult;
    }

    public BoardFileDto saveFile(MultipartFile file, String subDir) throws IOException {

        String fileOrgNm = file.getOriginalFilename();
        if (fileOrgNm == null) {
            return null;
        }
        //서버에 저장하는 파일명 UUID
        String fileSaveNm = createSaveFileName(fileOrgNm);

        String fullPath = subDir == null ? getFullPath(fileSaveNm) : getFullPath(fileSaveNm, subDir);
        file.transferTo(new File(fullPath));

        return new BoardFileDto(fileOrgNm, fileSaveNm, fileDir + subDir);
    }

    public void deleteFile(String fileSavePath, String fileSaveNm){
        File deleteFile = new File(fileSavePath + "/" + fileSaveNm);
        if (deleteFile.exists()) {
            if (deleteFile.delete()) {
                log.info("file delete : {}", fileSavePath + "/" + fileSaveNm);
            }
        }
    }

    private String createSaveFileName(String fileOrgNm) {
        // ex) UUID.png
        int pos = fileOrgNm.lastIndexOf(".");
        return UUID.randomUUID() + "." + fileOrgNm.substring(pos + 1);
    }


}
