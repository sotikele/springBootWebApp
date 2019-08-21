package com.example.check.controllers;
import com.eurodyn.qlack.fuse.lexicon.criteria.KeySearchCriteria;
import com.eurodyn.qlack.fuse.lexicon.dto.KeyDTO;
import com.eurodyn.qlack.fuse.lexicon.service.KeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;



@RestController
public class TranslationController {


    @Autowired
    KeyService keyService;


    @RequestMapping("/translations")
    public Map<String, String> getData(@RequestParam String lang) {

        return keyService.getTranslationsForLocale(lang);


    }

//    @RequestMapping("/keys/{pageNumber}")
//    public Map<String, String> getAllKeys(@PathVariable String pageNumber ) {
//         if(pageNumber.matches("1"))
//
//        return keyService.getTranslationsForLocale("en");
//         else if(pageNumber.matches("2"))
//             return keyService.getTranslationsForLocale("gr");
//         else
//             return null;
//
//
//    }

    @RequestMapping("/keys/{page}/{size}")
    public List<KeyDTO> getAllKeys(@PathVariable String page, @PathVariable String size ) {

        KeySearchCriteria criteria = new KeySearchCriteria();
        criteria.setPageable(PageRequest.of(Integer.parseInt(page),Integer.parseInt(size)));

      return   keyService.findKeys(criteria,true);
    }



}
