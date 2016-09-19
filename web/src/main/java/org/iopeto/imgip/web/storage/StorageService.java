package org.iopeto.imgip.web.storage;

import org.iopeto.imgip.web.model.Dump;
import org.iopeto.imgip.web.model.Img;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface StorageService {
    Img getImg(String name);

    void createImg(String name, String mimeType, InputStream is) throws IOException;

    List<Img> getAllImgs();

    void createdDump(String dump, String imgName);

    List<Dump> getAllDumps();

    List<Dump> pullAllDumps();
}
