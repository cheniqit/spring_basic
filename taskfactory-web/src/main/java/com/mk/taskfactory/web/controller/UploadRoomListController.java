package com.mk.taskfactory.web.controller;


import com.dianping.cat.Cat;
import com.mk.taskfactory.api.OnSaleFallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadRoomListController {

    @Autowired
    private OnSaleFallbackService onSaleFallbackService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void importBrandSort(MultipartFile file) throws Exception {

        if (file == null) {
            return;
        }

        String name = file.getOriginalFilename();// 获取上传文件名,包括路径
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return;
        }

        InputStream in = file.getInputStream();

        return;
    }

}
