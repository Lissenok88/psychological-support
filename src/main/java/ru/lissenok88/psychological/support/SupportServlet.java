package ru.lissenok88.psychological.support;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupportServlet extends HttpServlet {
    public static List<String> phrasesList = new ArrayList<>();

    @Override
    public void init() {
        phrasesList.addAll(Util.getListPhrases());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random random = new Random();
        String phrase = phrasesList.get(random.nextInt(phrasesList.size()));
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(phrase);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        phrasesList.add(request.getParameter("phrase"));
    }
}
