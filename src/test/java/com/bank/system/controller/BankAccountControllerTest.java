package com.bank.system.controller;

import com.bank.system.dto.request.CreateBankAccount;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.exception.general.RestExceptionHandler;
import com.bank.system.service.impl.BankAccountServiceImpl;
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

import static com.bank.system.mockData.AccountMock.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BankAccountControllerTest {

    private static String BASE_URL = "http://localhost";
    MockMvc mocMvc;
    ObjectMapper mapper = new ObjectMapper();
    @Mock
    BankAccountServiceImpl bankAccountService;
    @InjectMocks
    BankAccountController bankAccountController;

    @BeforeEach
    public void init() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mocMvc = MockMvcBuilders.standaloneSetup(bankAccountController)
                .setControllerAdvice(new RestExceptionHandler()).build();
    }

    @Test
    void WhenCreateBankAccount_Successfully_With_Status201() throws Exception {
        String json = mapper.writeValueAsString(bankAccountRequest());
        when(bankAccountService.createBankAccount(isA(CreateBankAccount.class))).thenReturn(1234123L);
        mocMvc.perform(post(BASE_URL + "/bank-account").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(header().string("accountId", containsString("1234123")))
                .andExpect(header().string("Location", containsString("http://localhost/bank-account/1234123")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void WhenCreateBankAccount_With_Status400_AccountTypeIsNull() throws Exception {
        String json = mapper.writeValueAsString(bankAccountRequestWithNullAccountType());
        mocMvc.perform(post(BASE_URL + "/bank-account").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void WhenCreateBankAccount_With_Status400_Invalid_AccountNumber() throws Exception {
        String json = mapper.writeValueAsString(bankAccountRequestWithIncorrectAccountNumber());
        mocMvc.perform(post(BASE_URL + "/bank-account").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void WhenGetBankAccount_ThenStatus404_NotFoundAccountId() throws Exception {
        when(bankAccountService.getBankAccount(anyLong())).thenThrow(NotFoundException.class);
        mocMvc.perform(get(BASE_URL + "/bank-account/11111").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void WhenGetBankAccount_With_Status200_SuccessAccountId() throws Exception {
        when(bankAccountService.getBankAccount(anyLong())).thenReturn(bankAccountResponse());
        mocMvc.perform(get(BASE_URL + "/bank-account/1111").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
