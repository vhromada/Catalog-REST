package cz.vhromada.catalog.rest.controller;

import java.util.Arrays;
import java.util.List;

import cz.vhromada.catalog.common.Language;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for language.
 *
 * @author Vladimir Hromada
 */
@RestController("languageController")
@RequestMapping("/languages")
@CrossOrigin
public class LanguageController {

    /**
     * Returns list of languages.
     *
     * @return list of languages
     */
    @GetMapping({ "", "/", "/list" })
    public List<Language> getLanguages() {
        return Arrays.asList(Language.values());
    }

    /**
     * Returns list of subtitles.
     *
     * @return list of subtitles
     */
    @GetMapping("/subtitles")
    public List<Language> getSubtitles() {
        return Arrays.asList(Language.CZ, Language.EN);
    }

}
