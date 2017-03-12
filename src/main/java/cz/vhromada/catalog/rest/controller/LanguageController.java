package cz.vhromada.catalog.rest.controller;

import java.util.Arrays;
import java.util.List;

import cz.vhromada.catalog.common.Language;
import cz.vhromada.result.Result;

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
public class LanguageController {

    /**
     * Returns list of languages.
     *
     * @return result with list of languages
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Language>> getLanguages() {
        return Result.of(Arrays.asList(Language.values()));
    }

    /**
     * Returns list of subtitles.
     *
     * @return result with list of subtitles
     */
    @GetMapping("/subtitles")
    public Result<List<Language>> getSubtitles() {
        return Result.of(Arrays.asList(Language.CZ, Language.EN));
    }

}
