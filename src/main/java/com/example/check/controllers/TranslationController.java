package com.example.check.controllers;

import com.eurodyn.qlack.fuse.lexicon.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class TranslationController {


    @Autowired
    KeyService keyService;


    @RequestMapping("/key")
    public Map<String, String> getData(@RequestParam String lang) {


        return keyService.getTranslationsForLocale(lang);


    }

}
