package com.nanor.auction.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanor.TestLifecycleLogger;
import com.nanor.auction.application.internal.commandservices.CreateAuction;
import com.nanor.auction.domain.commands.NewAuctionRequest;
import com.nanor.auction.domain.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuctionController.class)
class AuctionControllerTest implements TestLifecycleLogger {

    @MockBean
    private CreateAuction createAuction;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create auction")
    void createAuction() throws Exception {

        //setup
        TestAuctionResource auctionResource = new TestAuctionResource("2019-11-01T17:00:59Z",
                "2019-11-21T17:00:59Z",
                new Item(UUID.randomUUID(), "BOSE"),
                10);
        given(createAuction.create(any(NewAuctionRequest.class))).willReturn(1l);

        //execute
        this.mockMvc.perform(post("/auction")
                .content(asJsonString(auctionResource))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        //verify
        then(createAuction).should(times(1)).create(any(NewAuctionRequest.class));
        then(createAuction).shouldHaveNoMoreInteractions();
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}