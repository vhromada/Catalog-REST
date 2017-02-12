package cz.vhromada.catalog.rest.validator;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.rest.entity.Result;

/**
 * An interface represents validator for genre.
 *
 * @author Vladimir Hromada
 */
public interface GenreValidator {

    /**
     * Validates new genre.
     *
     * @param genre validating genre
     * @return result if validation errors
     */
    Result<Void> validateNewGenre(Genre genre);

    /**
     * Validates existing genre.
     *
     * @param genre validating genre
     * @return result if validation errors
     */
    Result<Void> validateExistingGenre(Genre genre);

    /**
     * Validates genre with ID.
     *
     * @param genre validating genre
     * @return result if validation errors
     */
    Result<Void> validateGenreWithId(Genre genre);

}
