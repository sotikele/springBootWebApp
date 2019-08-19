package com.example.check.controllers;
import com.eurodyn.qlack.fuse.lexicon.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class TransController {



    @Autowired
    KeyService keyService;


    @RequestMapping("/key/{locale}")
    public Map<String, String> getData(@PathVariable String locale) {


        return  keyService.getTranslationsForLocale(locale);


    }


}
