package cz.vhromada.catalog.rest;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * A class represents CatalogInitializer.
 *
 * @author Vladimir Hromada
 */
public class CatalogInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/*" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ RestConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ RestConfiguration.class };
    }

}
