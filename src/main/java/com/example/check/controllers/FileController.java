package com.example.check.controllers;

import com.eurodyn.qlack.fuse.fileupload.rest.FileUploadRestTemplate;
import com.eurodyn.qlack.fuse.fileupload.service.impl.FileUploadImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
public class FileController extends FileUploadRestTemplate {

    @Autowired
    FileUploadImpl fileUpload;
    /**
     * Checks if a chunk has already been uploaded
     *
     * @param flowIdentifier The file id
     * @param chunkNumber The chunk order number
     * @return ResponseEntity with http status 200 (in case of success), 204 (if chunk does not
     * exist)
     */
    @RequestMapping(path = "/upload",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity checkChunk(
            @RequestParam("flowChunkNumber") Long chunkNumber,
            @RequestParam("flowIdentifier") String flowIdentifier) {
        return super.checkChunk(flowIdentifier, chunkNumber);
    }

    /**
     * Uploads a chunk of the to be uploaded file.
     * @param body The {@link MultipartHttpServletRequest} body
     * @return ResponseEntity with http status 200 (in case of success), 500 (in case of error)
     */
    @RequestMapping(path = "/upload",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE,
            consumes = {"multipart/form-data"})
    public ResponseEntity upload(MultipartHttpServletRequest body) {
        return super.upload(body);
    }



}

