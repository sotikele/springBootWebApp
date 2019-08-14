package com.example.check.services;

import com.eurodyn.qlack.fuse.lexicon.repository.ApplicationRepository;
import com.eurodyn.qlack.fuse.lexicon.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;

public class QlackService {

    @Autowired
    private LanguageService languageService;

    @Autowired
    ApplicationRepository applicationRepository;

}
