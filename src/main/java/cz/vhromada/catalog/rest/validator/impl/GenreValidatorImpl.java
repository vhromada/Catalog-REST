package cz.vhromada.catalog.rest.validator.impl;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.rest.entity.Result;
import cz.vhromada.catalog.rest.validator.GenreValidator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents implementation of validator for genre.
 *
 * @author Vladimir Hromada
 */
@Component("restGenreValidator")
public class GenreValidatorImpl implements GenreValidator {

    @Override
    public Result<Void> validateNewGenre(final Genre genre) {
        final Result<Void> result = validateGenre(genre);
        if (genre.getId() != null) {
            result.addErrorMessage("ID_NOT_NULL", "ID must be null.");
        }

        return result;
    }

    @Override
    public Result<Void> validateExistingGenre(final Genre genre) {
        final Result<Void> result = validateGenre(genre);
        if (genre.getId() == null) {
            result.addErrorMessage("ID_NULL", "ID mustn't be null.");
        }

        return result;
    }

    @Override
    public Result<Void> validateGenreWithId(final Genre genre) {
        if (genre == null) {
            return Result.of("GENRE_NULL", "Genre mustn't be null.");
        }

        if (genre.getId() == null) {
            return Result.of("ID_NULL", "ID mustn't be null.");
        }

        return new Result<>();
    }

    /**
     * Validates genre.
     *
     * @param genre validating genre
     * @return result if validation errors
     */
    private static Result<Void> validateGenre(final Genre genre) {
        if (genre == null) {
            return Result.of("GENRE_NULL", "Genre mustn't be null.");
        }

        final Result<Void> result = new Result<>();
        if (genre.getName() == null) {
            result.addErrorMessage("NAME NULL", "Name mustn't be null");
        }
        if (StringUtils.isEmpty(genre.getName()) || StringUtils.isEmpty(genre.getName().trim())) {
            result.addErrorMessage("NAME EMPTY", "Name mustn't be empty string.");
        }

        return result;
    }

}
