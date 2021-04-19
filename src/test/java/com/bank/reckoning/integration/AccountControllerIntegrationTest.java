package com.bank.reckoning.integration;

import com.bank.reckoning.controller.AccountController;
import com.bank.reckoning.integration.initializer.ContainerDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({
        @Sql(scripts = "/db/sql/usersInsert.sql"),
        @Sql(scripts = "/db/sql/accountsInsert.sql"),
        @Sql(scripts = "/db/sql/cleanDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@AutoConfigureMockMvc
class AccountControllerIntegrationTest extends ContainerDB {

    private static final String UPDATE_ACCOUNT_ENDPOINT = "/accounts/update";

    @Autowired
    private AccountController accountController;
    @Autowired
    private MockMvc mockMvc;
    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newCachedThreadPool();
    }

    @Test
    void whenUpdateAccount_thenReturnAccountViewDto() throws Exception {
        CountDownLatch latch = new CountDownLatch(5);

        Runnable testRequest = () -> {
            try {
                mockMvc.perform(MockMvcRequestBuilders.post(UPDATE_ACCOUNT_ENDPOINT)
                        .param("operationType", "REPLENISHMENT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountId\": \"1\", \"amount\": \"1.12\"}"))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful());
            } catch (Exception e) {
                e.printStackTrace();
            }
            latch.countDown();
        };

        for(int i = 0; i < 5; i++)
         executorService.submit(testRequest);


        latch.await();
    }

    @AfterEach
    void tearDown() {
//        executorService.shutdown();
    }
}