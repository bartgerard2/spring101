package be.continuum.slice.controller;

import be.continuum.slice.model.ConsumableProduct;
import be.continuum.slice.model.Product;
import be.continuum.slice.service.ProductService;
import be.continuum.slice.value.Category;
import be.continuum.slice.value.ProductName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ProductRestController
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Before
    public void setUp() {
        final Category candy = Category.of("candy");

        when(productService.findAll()).thenReturn(Arrays.asList(
                ConsumableProduct.builder()
                        .name(ProductName.of("mars"))
                        .category(candy)
                        .build(),
                ConsumableProduct.builder()
                        .name(ProductName.of("bounty"))
                        .category(candy)
                        .build()
        ));

        when(productService.save(Mockito.any(Product.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test
    public void retrieveAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()) // prints header, response and request
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name.name", is("mars")))
                .andExpect(jsonPath("$[0].category.name", is("candy")))
                .andExpect(jsonPath("$[1].name.name", is("bounty")))
                .andExpect(jsonPath("$[1].category.name", is("candy")));
    }

    @Test
    public void createConsumableProduct() throws Exception {
        mockMvc.perform(
                post("/products/consumables")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":{\"name\":\"twix\"}}")
        )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("name.name", is("twix")))
                .andExpect(jsonPath("type", is("CONSUMABLE")));
    }

    @Test
    public void createNonConsumableProduct() throws Exception {
        mockMvc.perform(
                post("/products/non-consumables")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":{\"name\":\"kitchen-knife\"}}")
        )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("name.name", is("kitchen-knife")))
                .andExpect(jsonPath("type", is("NON_CONSUMABLE")));
    }

}
