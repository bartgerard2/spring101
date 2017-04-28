package be.continuum.slice.respository;

import be.continuum.slice.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * CustomCustomerRepositoryImpl
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private static final String FIND_BY_EMAIL = "SELECT c.username, c.email, c.first_name, c.last_name FROM CUSTOMER c WHERE c.email LIKE :email";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> findByEmail(final String email) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);

        return jdbcTemplate.query(
                FIND_BY_EMAIL,
                parameters,
                (rs, i) -> Customer.builder()
                                   .username(rs.getString("username"))
                                   .email(rs.getString("email"))
                                   .firstName(rs.getString("first_name"))
                                   .lastName(rs.getString("last_name"))
                                   .build()
        );
    }

}
