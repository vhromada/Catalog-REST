package cz.vhromada.catalog.rest.controller;

import java.util.Arrays;
import java.util.List;

import cz.vhromada.common.Language;
import cz.vhromada.validation.result.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for language.
 *
 * @author Vladimir Hromada
 */
@RestController("languageController")
@RequestMapping("/catalog/languages")
public class LanguageController extends AbstractCatalogController {

    /**
     * Returns list of languages.
     *
     * @return result with list of languages
     */
    @GetMapping
    public ResponseEntity<Result<List<Language>>> getLanguages() {
        return processResult(Result.of(Arrays.asList(Language.values())));
    }

    /**
     * Returns list of subtitles.
     *
     * @return result with list of subtitles
     */
    @GetMapping("/subtitles")
    public ResponseEntity<Result<List<Language>>> getSubtitles() {
        return processResult(Result.of(Arrays.asList(Language.CZ, Language.EN)));
    }

}
