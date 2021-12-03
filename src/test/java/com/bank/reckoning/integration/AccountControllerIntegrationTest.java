package com.bank.reckoning.integration;

import com.bank.reckoning.controller.AccountController;
import com.bank.reckoning.dto.AccountViewDto;
import com.bank.reckoning.integration.initializer.ContainerDB;
import com.bank.reckoning.integration.initializer.TestPostgresContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({
        @Sql("/db/sql/usersInsert.sql"),
        @Sql("/db/sql/accountsInsert.sql"),
        @Sql(scripts = "/db/sql/cleanDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@AutoConfigureMockMvc
@Ignore
class AccountControllerIntegrationTest extends ContainerDB {

    private static final String UPDATE_ACCOUNT_ENDPOINT = "/accounts/update";

    @Autowired
    private AccountController accountController;
    @Autowired
    private MockMvc mockMvc;
    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(1);
    }

    @Test
    void whenUpdateAccount_thenReturnAccountViewDto() throws InterruptedException, ExecutionException {
        CountDownLatch latch = new CountDownLatch(5);

        Callable<AccountViewDto> testRequest = () -> {

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(UPDATE_ACCOUNT_ENDPOINT)
                    .param("operationType", "REPLENISHMENT")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"accountId\": \"1\", \"amount\": \"1.12\"}"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();

            latch.countDown();

            return new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), AccountViewDto.class);

        };

            Future<AccountViewDto> submit = executorService.submit(testRequest);
            Future<AccountViewDto> submit2 = executorService.submit(testRequest);
            Future<AccountViewDto> submit3 = executorService.submit(testRequest);
            Future<AccountViewDto> submit4 = executorService.submit(testRequest);
            Future<AccountViewDto> submit5 = executorService.submit(testRequest);

            latch.await();


            if (submit.isDone())
                Assertions.assertEquals("101.12", submit.get().getAmount());

            if (submit2.isDone())
                Assertions.assertEquals("102.24", submit2.get().getAmount());

            if (submit3.isDone())
                Assertions.assertEquals("103.36", submit3.get().getAmount());

            if (submit4.isDone())
                Assertions.assertEquals("104.48", submit4.get().getAmount());

            if (submit5.isDone())
                Assertions.assertEquals("105.60", submit5.get().getAmount());
    }
}