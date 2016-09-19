package org.iopeto.imgip.web.storage;

import org.apache.commons.io.IOUtils;
import org.iopeto.imgip.web.model.Dump;
import org.iopeto.imgip.web.model.Img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class StorageServiceImpl implements StorageService {
    private final ConcurrentMap<String, Img> images;
    private final List<Dump> dumps;
    private final DateFormat dateFormat;

    @Autowired
    public StorageServiceImpl(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        this.dumps = Collections.synchronizedList(new ArrayList<>());
        this.images = new ConcurrentHashMap<>();
    }

    @Override
    public Img getImg(String name){
        return images.get(name);
    }

    @Override
    public void createImg(String name, String mimeType, InputStream is) throws IOException {
        images.put(name, new Img(name, mimeType, IOUtils.toByteArray(is)));
    }

    @Override
    public List<Img> getAllImgs(){
        final List<Img> images = new ArrayList<>();
        this.images.values().forEach(img -> images.add(img));
        return images;
    }

    @Override
    public void createdDump(String dump, String imgName){
        dumps.add(new Dump(dump,imgName, dateFormat.format(Calendar.getInstance().getTime())));
    }

    @Override
    public List<Dump> getAllDumps(){
        return new ArrayList<>(dumps);
    }

    @Override
    public List<Dump> pullAllDumps(){
        List<Dump> copy = null;
        synchronized(dumps) {
            copy = new ArrayList<>(dumps);
            dumps.clear();
        }
        return copy;
    }
}
