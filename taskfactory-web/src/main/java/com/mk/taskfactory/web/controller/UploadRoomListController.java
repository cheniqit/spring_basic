package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.UploadRoomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller
public class UploadRoomListController {

    @Autowired
    private UploadRoomListService uploadRoomListService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void importRoomList(@RequestParam("file") MultipartFile file) throws Exception {

        if (file == null) {
            return;
        }

        String name = file.getOriginalFilename();// 获取上传文件名,包括路径
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return;
        }

        InputStream in = file.getInputStream();

        String returnStr = this.uploadRoomListService.saveRoomSaleConfigList(in);

        return;
    }

}
