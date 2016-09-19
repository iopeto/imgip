package org.iopeto.imgip.web.controller;

import org.iopeto.imgip.web.dump.DumpService;
import org.iopeto.imgip.web.model.Img;
import org.iopeto.imgip.web.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class ImgController {

    private static final ResponseEntity NOT_FOUND = ResponseEntity.notFound().build();
    private static final Logger LOGGER = LoggerFactory.getLogger(ImgController.class);

    private final StorageService storageService;
    private final DumpService dumpService;

    @Autowired
    public ImgController(StorageService storageService, DumpService dumpService) {
        this.storageService = storageService;
        this.dumpService = dumpService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.createImg(file.getOriginalFilename(), file.getContentType(), file.getInputStream());
        return "redirect:/";
    }

    @RequestMapping(value = "/img/**", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> img(HttpServletRequest request){
        ResponseEntity entity = NOT_FOUND;
        String dump = dumpService.dumpRequest(request);
        String path = "not_found";
        try {
            path = null != request.getRequestURI() ? UriUtils.decode(request.getRequestURI().replace("/img/", ""), "utf-8") : "";
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(),e);
        }

        storageService.createdDump(dump, path);

        Img image = storageService.getImg(path);
        if (null != image){
            entity = ResponseEntity.ok()
                    .contentLength(image.getBytes().length)
                    .contentType(MediaType.parseMediaType(image.getMimeType()))
                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
        }

        return entity;
    }

}
