package ru.lissenok88.psychological.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SupportServletTest {

    private SupportServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;


    @BeforeEach
    public void setUp() throws IOException {
        SupportServlet.phrasesList.addAll(Util.getListPhrases());
        servlet = new SupportServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        responseWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(responseWriter);

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void doGet() throws IOException {
        servlet.doGet(request, response);
        String phrase = responseWriter.toString();
        servlet.doGet(request, response);
        assertNotEquals(phrase, responseWriter.toString());
    }

    @Test
    void doPost() throws IOException {
        String word = "Всё хорошо!";
        when(request.getParameter("phrase")).thenReturn(word);
        servlet.doPost(request, response);
        assertTrue(SupportServlet.phrasesList.contains(word));
    }
}