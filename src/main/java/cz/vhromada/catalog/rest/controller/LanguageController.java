package cz.vhromada.catalog.rest.controller;

import java.util.Arrays;

import cz.vhromada.catalog.commons.Language;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vladimir.hromada
 */
@RestController("languageController")
@RequestMapping("/languages")
@CrossOrigin
public class LanguageController extends AbstractCatalogController {

    /**
     * Returns list of languages.
     *
     * @return list of languages
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getLanguages() {
        return getDataResponseEntity(Language.values());
    }

    /**
     * Returns list of subtitles.
     *
     * @return list of subtitles
     */
    @RequestMapping(value = { "subtitles" }, method = RequestMethod.GET)
    public ResponseEntity<String> getSubtitles() {
        return getDataResponseEntity(Arrays.asList(Language.CZ, Language.EN));
    }

}
