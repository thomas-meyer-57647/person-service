package de.innologic.personservice.web.logging;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class CorrelationIdFilterTest {

    private final CorrelationIdFilter filter = new CorrelationIdFilter();

    @AfterEach
    void cleanup() {
        MDC.clear();
    }

    @Test
    void shouldReuseCorrelationIdFromHeader() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader(CorrelationIdFilter.HEADER, "trace-id-123");
        AtomicBoolean chainCalled = new AtomicBoolean();

        filter.doFilterInternal(request, response, (req, res) -> {
            assertThat(MDC.get(CorrelationIdFilter.MDC_KEY)).isEqualTo("trace-id-123");
            chainCalled.set(true);
        });

        assertThat(chainCalled.get()).isTrue();
        assertThat(response.getHeader(CorrelationIdFilter.HEADER)).isEqualTo("trace-id-123");
        assertThat(MDC.get(CorrelationIdFilter.MDC_KEY)).isNull();
    }

    @Test
    void shouldGenerateNewCorrelationIdWhenMissing() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AtomicBoolean chainCalled = new AtomicBoolean();

        filter.doFilterInternal(request, response, (req, res) -> {
            String correlationInContext = MDC.get(CorrelationIdFilter.MDC_KEY);
            assertThat(correlationInContext).isNotNull();
            chainCalled.set(true);
        });

        assertThat(chainCalled.get()).isTrue();
        String headerValue = response.getHeader(CorrelationIdFilter.HEADER);
        assertThat(headerValue).isNotNull().isNotBlank();
        assertThat(MDC.get(CorrelationIdFilter.MDC_KEY)).isNull();
    }
}
