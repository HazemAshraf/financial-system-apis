package com.bank.system.controller;

import com.bank.system.dto.request.CreateTransferTransaction;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.exception.general.RestExceptionHandler;
import com.bank.system.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.bank.system.mockData.TransactionsMock.transactionListResponse;
import static com.bank.system.mockData.TransferModelMock.transferModel;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    private static String BASE_URL = "http://localhost";

    MockMvc mocMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Mock
    TransactionService transactionServiceImpl;

    @InjectMocks
    TransactionController transactionController;

    @BeforeEach
    public void init() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mocMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new RestExceptionHandler()).build();
    }

    @Test
    void WhenGetBankAccountTransactions_With_Status404_NotFoundAccountId() throws Exception {
        when(transactionServiceImpl.getTransactions(anyLong(), anyInt(), anyInt())).thenThrow(NotFoundException.class);
        mocMvc.perform(get(BASE_URL + "/transaction?accountId=11111&page=1&pageSize=1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void WhenGetBankAccountTransactions_Successfully_With_Status200() throws Exception {
        when(transactionServiceImpl.getTransactions(anyLong(), anyInt(), anyInt())).thenReturn(transactionListResponse());
        mocMvc.perform(get(BASE_URL + "/transaction?accountId=11111&page=1&pageSize=1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void WhenTransferMoney_Successfully_With_Status200() throws Exception {
        String json = mapper.writeValueAsString(transferModel());
        doNothing().when(transactionServiceImpl).transferTransaction(isA(CreateTransferTransaction.class));
        mocMvc.perform(post(BASE_URL + "/transaction").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
