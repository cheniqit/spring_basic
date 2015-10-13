package com.mk.taskfactory.api;

import java.io.InputStream;
import java.util.Map;

public interface UploadRoomListService {
    public Map<Integer, String> readExcelContent(InputStream is);
}
