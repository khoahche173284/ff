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
import model.DAOLoginHistory;
import org.languagetool.rules.Rule;
import org.languagetool.rules.spelling.SpellingCheckRule;

/**
 *
 * @author Quang
 */
public class CheckGrammar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String categorySTR = request.getParameter("category");
            
            categorySTR = categorySTR.trim();

            String text = request.getParameter("content");
             if (text == null || text.trim().isEmpty()) {
        request.setAttribute("emptyy", "Please input something.");
        request.setAttribute("content", "");
    }else{
            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

//            //Bao 
            int categoryId = 0;
            if (!categorySTR.equals("0")) {
                categoryId = Integer.parseInt(categorySTR);
            }
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("acc");
            if(user != null){
            int uid = user.getId();
            DAOLibrary DL = new DAOLibrary();
            for (Rule rule : langTool.getAllRules()) {
                ArrayList<library> Liblist = DL.listbyid(uid); 
                 ArrayList<library> Liblist2 = DL.listbyid(uid);  
                 ArrayList<library> Liblist3 = DL.listbyid(uid);  
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
                     for (library li : Liblist3) { 
                    
                      String [] S  = li.getDefine().split(" ");
                   for(String str :S)   {  
                    List<String> wordsToIgnore = Arrays.asList(str);
                   
                    langTool.addRule(rule);
                    if (rule instanceof SpellingCheckRule) {
                        ((SpellingCheckRule) rule).addIgnoreTokens(wordsToIgnore);
                    } 
                    
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
            //Bao//

            String originalText = text;

            List<RuleMatch> matches = langTool.check(text);

            StringBuilder resultHtml = new StringBuilder();
            // vị trí bắt đầu và cuối
            List<Integer> startPosList = new ArrayList<>();
            List<Integer> endPosList = new ArrayList<>();

            int pos = 0;
            for (RuleMatch match : matches) {
                // không có lỗi
                resultHtml.append(text, pos, match.getFromPos());
                //  có lỗi 
                resultHtml.append("<span class='err'>");
                resultHtml.append(text, match.getFromPos(), match.getToPos());
                resultHtml.append("</span>");
                // lưu vị trí
                startPosList.add(match.getFromPos());
                endPosList.add(match.getToPos());
                pos = match.getToPos();
            }

            resultHtml.append(text.substring(pos));

            StringBuilder suggestionsHtml = new StringBuilder();
            if (matches.isEmpty()) {
                suggestionsHtml.append("<h5 style='color: red; text-align: center;'>No spelling or grammar errors found!</h5>");
                suggestionsHtml.append("<h3 style='color: green; text-align: center; margin-top: 20px;'>Your text is perfect!</h3>");
            } else {
                for (int i = 0; i < matches.size(); i++) {
                    RuleMatch match = matches.get(i);

                    suggestionsHtml.append("From position ").append(startPosList.get(i)).append(" to ").append(endPosList.get(i)).append(": ");
                    suggestionsHtml.append(match.getMessage()).append("<br/>");
                    suggestionsHtml.append("Suggested correction(s): ");

                    String firstSuggestion = match.getSuggestedReplacements().get(0);
                    suggestionsHtml.append("<span class='suggestion' style='color: green;' data-frompos='")
                            .append(startPosList.get(i)).append("' data-topos='").append(endPosList.get(i))
                            .append("'>").append(firstSuggestion).append("</span>");

                    suggestionsHtml.append(" <button type='button' class='accept-btn' onclick='accept(this)'>accept</button>");

                    suggestionsHtml.append("<br/><br/>");
                }
            }
             DAOLoginHistory loginHistoryDAO =  new DAOLoginHistory();
        if( user!= null){
        loginHistoryDAO.insertRecentActivity(user.getId(),25);
        } 
            request.setAttribute("text", text);
            request.setAttribute("originalText", originalText);
            request.setAttribute("CategoryE", categoryId);
            request.setAttribute("result", resultHtml.toString());
            request.setAttribute("suggestions", suggestionsHtml.toString());
        }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "Please input again." + e.getMessage());
            request.setAttribute("suggestions", "Please input again.");
        }
        

        request.getRequestDispatcher("text.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}