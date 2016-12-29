package cz.vhromada.catalog.rest;

import javax.naming.NamingException;
import javax.sql.DataSource;

import cz.vhromada.catalog.CatalogConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jndi.JndiTemplate;

/**
 * A class represents Spring configuration.
 *
 * @author Vladimir Hromada
 */
@Configuration
@Import(CatalogConfiguration.class)
public class RestConfiguration {

    @Bean
    public DataSource dataSource() throws NamingException {
        return new JndiTemplate().lookup("jdbc/catalog", DataSource.class);
    }

}
