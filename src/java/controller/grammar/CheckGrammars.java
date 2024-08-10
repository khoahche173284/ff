package controller.grammar;

import entity.Essay;
import entity.Users;
import entity.library;
import org.languagetool.JLanguageTool;
import org.languagetool.rules.RuleMatch;
import org.languagetool.language.AmericanEnglish;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.DAOEssay;
import model.DAOLibrary;
import org.languagetool.rules.Rule;
import org.languagetool.rules.spelling.SpellingCheckRule;

/**
 *
 * @author Quang
 */
public class CheckGrammars extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // lấy content và id bai viet
        String text = request.getParameter("content"); 
        String essayIdstr= request.getParameter("essayId");
        essayIdstr =essayIdstr.trim();
        int essayId = Integer.parseInt(essayIdstr);

        // request thông tin bài viết để reload 
        DAOEssay essayDAO = new DAOEssay();
        Essay newEssay = essayDAO.getEssayById(essayId);
        request.setAttribute("contentE", newEssay.getContent());
        request.setAttribute("CategoryE", newEssay.getCategoriesId());
        request.setAttribute("essayId", newEssay.getId());
        String originalText = text;

       if (text == null || text.trim().isEmpty()) {
        request.setAttribute("emptyy", "Please input something.");
        request.setAttribute("contentE", "");
        
        
    }else{ 
           
             JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
           //Bao 
             
           
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("acc");
            if(user != null){
            int uid = user.getId();
            DAOLibrary DL = new DAOLibrary();
            for (Rule rule : langTool.getAllRules()) {
                ArrayList<library> Liblist = DL.listbyid(uid); 
                 ArrayList<library> Liblist2 = DL.listbyid(uid); 
                for (library li : Liblist) { 
                    
                    List<String> wordsToIgnore = Arrays.asList(li.getKeyword().toLowerCase());
                   
                    langTool.addRule(rule);
                    if (rule instanceof SpellingCheckRule) {
                        ((SpellingCheckRule) rule).addIgnoreTokens(wordsToIgnore);
                    }
                } 
                
                 for (library li : Liblist2) { 
                    
                    List<String> wordsToIgnore = Arrays.asList(li.getKeyword().toUpperCase());
                   
                    langTool.addRule(rule);
                    if (rule instanceof SpellingCheckRule) {
                        ((SpellingCheckRule) rule).addIgnoreTokens(wordsToIgnore);
                    }
                } 
                
                
            } 
            
            
            
            String keyw = request.getParameter("word");
            String defi = request.getParameter("defi");
            if (keyw != null) {
                String[] textlist = text.split(" ");
                text = "";
                for (String str : textlist) {
                    if (str.equalsIgnoreCase(keyw)) {
                        str = "";
                        str = str.concat(defi);
                    }
                    text = text.concat(str);
                    text = text.concat(" ");
                }
            }
            ArrayList<library> Liblist = DL.listbyid(uid);
            ArrayList<library> suggestlist = new ArrayList<>();
            String[] listS = text.split(" ");
            for (String s : listS) {
                for (library li : Liblist) {
                    if (s.equalsIgnoreCase(li.getKeyword()) && !s.equalsIgnoreCase(keyw)) {
                        suggestlist.add(li);
                    }
                }

            }
            request.setAttribute("suggestlib", suggestlist);
            }
           
           
           
           //Bao 
           
           
           
           
           
      

        // check
        List<RuleMatch> matches = langTool.check(text);

        // html
        StringBuilder resultHtml = new StringBuilder();
        List<Integer> startPosList = new ArrayList<>();
        List<Integer> endPosList = new ArrayList<>();
        int pos = 0;
        for (RuleMatch match : matches) {
            resultHtml.append(text, pos, match.getFromPos());
            resultHtml.append("<span style='color: red;'>");
            resultHtml.append(text, match.getFromPos(), match.getToPos());
            resultHtml.append("</span>");
            pos = match.getToPos();
            
            // vị trí sugesstion
            startPosList.add(match.getFromPos());
            endPosList.add(match.getToPos());
        }
        resultHtml.append(text.substring(pos));

      
        StringBuilder suggestionsHtml = new StringBuilder();
        if (matches.isEmpty()) {
                suggestionsHtml.append("<h5 style='color: red; text-align: center;'>No spelling or grammar errors found!</h5>");
                suggestionsHtml.append("<h3 style='color: green; text-align: center; margin-top: 20px;'>Your text is perfect!</h3>");
            } else {
        for (int i = 0; i < matches.size(); i++) {
            RuleMatch match = matches.get(i);

            suggestionsHtml.append("From position  ").append(startPosList.get(i)).append(" to ").append(endPosList.get(i)).append(": ");
            suggestionsHtml.append(match.getMessage()).append("<br/>");
            suggestionsHtml.append("Suggested correction(s):  ");

            String firstSuggestion = match.getSuggestedReplacements().get(0);
            suggestionsHtml.append("<span class='suggestion' style='color: green;' data-frompos='")
                    .append(startPosList.get(i)).append("' data-topos='").append(endPosList.get(i))
                    .append("'>").append(firstSuggestion).append("</span>");

            suggestionsHtml.append(" <button type='button' class='accept-btn' onclick='accept(this)'>accept</button>");

            suggestionsHtml.append("<br/><br/>");
        }
        
        }// luu attribute de sang jsp lay
        request.setAttribute("originalText", originalText);
        request.setAttribute("contentE", text);
        request.setAttribute("result", resultHtml.toString());
        request.setAttribute("suggestions", suggestionsHtml.toString());

    }} catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("result", "Exception: " + e.getMessage());
        request.setAttribute("suggestions", "");
        request.setAttribute("contentE", "lol");
    }
    request.getRequestDispatcher("edittext.jsp").forward(request, response);
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}