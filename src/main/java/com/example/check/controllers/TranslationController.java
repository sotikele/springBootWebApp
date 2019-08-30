package com.example.check.controllers;
import com.eurodyn.qlack.fuse.lexicon.criteria.KeySearchCriteria;
import com.eurodyn.qlack.fuse.lexicon.dto.KeyDTO;
import com.eurodyn.qlack.fuse.lexicon.repository.KeyRepository;
import com.eurodyn.qlack.fuse.lexicon.service.KeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class TranslationController {


    @Autowired
    KeyService keyService;

    @Autowired
    KeyRepository keyRepository;


    @RequestMapping("/translations")
    public Map<String, String> getTranslations(@RequestParam String lang) {

        return keyService.getTranslationsForLocale(lang);


    }



    @RequestMapping("/keys/{page}/{size}")
    public List<KeyDTO> getPagesForAllKeys(@PathVariable String page, @PathVariable String size ) {

        KeySearchCriteria criteria = new KeySearchCriteria();
        criteria.setPageable(PageRequest.of(Integer.parseInt(page),Integer.parseInt(size)));

      return   keyService.findKeys(criteria,true);
    }

//    @RequestMapping("/totalKeys")
////    public Map<String,Long> getNumberOfKeys() {
////        KeySearchCriteria criteria = new KeySearchCriteria();
////        criteria.setKeyName("login");
////
////        Map<String,Long> total=new HashMap<>();
////        total.put("total",  keyService.findTotalKeys(criteria));
////        return  total;
////
////    }


    @RequestMapping("/totalKeys")
   public Long getNumberOfKeys() {

        return  keyRepository.count();

    }


    @PostMapping("/key/update")
    public void updateTranslationsForKey(@RequestBody KeyDTO keyDTO) {

        keyService.updateTranslationsForKey(keyDTO.getId(),keyDTO.getTranslations());
    }



}
