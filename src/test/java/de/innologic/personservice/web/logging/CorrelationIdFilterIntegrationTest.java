package de.innologic.personservice.web.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CorrelationIdFilterIntegrationTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PingController(), new MdcEchoController())
                .addFilters(new CorrelationIdFilter())
                .build();
    }

    @Test
    void shouldGenerateCorrelationIdWhenMissing() throws Exception {
        mockMvc.perform(get("/person/ping"))
                .andExpect(status().isOk())
                .andExpect(header().exists(CorrelationIdFilter.HEADER));
    }

    @Test
    void shouldExposeGeneratedCorrelationIdInControllerMdc() throws Exception {
        MvcResult result = mockMvc.perform(get("/person/mdc"))
                .andExpect(status().isOk())
                .andExpect(header().exists(CorrelationIdFilter.HEADER))
                .andReturn();

        String headerValue = result.getResponse().getHeader(CorrelationIdFilter.HEADER);
        assertThat(result.getResponse().getContentAsString()).isEqualTo(headerValue);
    }

    @Test
    void shouldPreserveProvidedCorrelationId() throws Exception {
        String incoming = "external-trace-456";
        mockMvc.perform(get("/person/ping").header(CorrelationIdFilter.HEADER, incoming))
                .andExpect(status().isOk())
                .andExpect(header().string(CorrelationIdFilter.HEADER, incoming));
    }

    @Test
    void shouldExposeProvidedCorrelationIdInControllerMdc() throws Exception {
        String incoming = "external-trace-456";
        MvcResult result = mockMvc.perform(get("/person/mdc").header(CorrelationIdFilter.HEADER, incoming))
                .andExpect(status().isOk())
                .andExpect(header().string(CorrelationIdFilter.HEADER, incoming))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(incoming);
    }

    @RestController
    static class PingController {

        @GetMapping("/person/ping")
        String ping() {
            return "pong";
        }
    }

    @RestController
    static class MdcEchoController {

        @GetMapping("/person/mdc")
        String echoCorrelationId() {
            return MDC.get(CorrelationIdFilter.MDC_KEY);
        }
    }
}
