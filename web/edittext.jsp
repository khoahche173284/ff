
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document Editor</title>
        <link rel="stylesheet" href="textt.css">
        <style>
            .err{
                color: #EA645A;
                background-color: #F5DBD8;
                border-radius: 5px;
                padding: 0px 1px;
                border: none;
            }
            .accept-btn,.ignore-btn{

                border: none;
                padding: 5px 7px;

                font-size: 10px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s, color 0.3s;
            }
            .accept-btn {
                background-color: #D6F9D3;
                color: white;
            }
            .ignore-btn {
                background-color: pink;
                color: white;
            }
            .accept-btn:hover {
                background-color: #45a049;
            }
            .ignore-btn:hover {
                background-color: #ff3366;
            }
            .fix .suggestion {
                display: inline-block;
                margin: 5px 0;
            }
            .hat4{
                margin: 0px 30px ;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <main class="editor">
                <header class="header">
                    <input placeholder="Untitled">
                </header>
                <form id="mainForm" method="post">
                    <input type="hidden" name="essayId" value="${essayId}">

                    <select class="category" id="select_option" name="category">
                        <option value="0">select category</option>
                        <option value="1" ${CategoryE == 1 ? "selected" : ""}>sentence</option>
                        <option value="2" ${CategoryE == 2 ? "selected" : ""}>essay</option>
                        <option value="3" ${CategoryE == 3 ? "selected" : ""}>summary</option>
                    </select>
                    <div class="textarea-container">
                        <textarea
                            placeholder="Type or paste (Ctrl+V) your text here or upload a document."
                            name="content" style="color:#353535" id="content">${contentE}</textarea>
                    </div>
                    <button class="save" type="submit" onclick="submitForm('updatetext')">Update</button>
                    <button type="submit" class="accept" onclick="submitForm('CheckGrammars')">Check &#x1F50E;</button>
                    <button type="button" id="accept" class="accept tat acceptall" style="background-color:#50c1f5" onclick="acceptall()">Accept all &#x2714;</button>
                    <button type="button" id="return" class="accept tat return" style="display :none ;" onclick="returnall();" >Return &#x21BB;</button>
                    <p id="status" style="color : green; margin-left: 22px;">${emptyy}</p>
                </form>
                <script>
                    const acceptall = () => {

                        const contentTextarea = document.getElementById('content');
                        const fixx = document.getElementById('fixx');
                        let fixed = fixx.innerText;

                        let content = contentTextarea.value;
                        if (fixed.trim() === '') {
                            document.getElementById('status').innerText = "Please Check something  ...";

                        } else {
                            const suggestions = [];
                            document.querySelectorAll('.suggestion').forEach(span => {
                                const fromPos = parseInt(span.dataset.frompos);
                                const toPos = parseInt(span.dataset.topos);
                                const replacement = span.innerText;
                                suggestions.push({fromPos, toPos, replacement});
                            });
                            suggestions.sort((a, b) => b.fromPos - a.fromPos);
                            suggestions.forEach(suggestion => {
                                content = content.substring(0, suggestion.fromPos) + suggestion.replacement + content.substring(suggestion.toPos);
                            });
                            contentTextarea.value = content;
                            fixx.innerText = 'Accept all successfully!';

                            document.getElementById('status').innerText = "Accept all successfully!";
                            document.getElementById('return').style.display = 'inline-block';
                            document.getElementById('accept').style.display = 'none';
                        }

                    };




                    const accept = (button) =>
                    {
                        const suggestionSpan = button.previousElementSibling;
                        const fromPos = parseInt(suggestionSpan.dataset.frompos);
                        const toPos = parseInt(suggestionSpan.dataset.topos);
                        const replacement = suggestionSpan.innerText;

                        const contentTextarea = document.getElementById('content');
                        let content = contentTextarea.value;

                        content = content.substring(0, fromPos) + replacement + content.substring(toPos);
                        contentTextarea.value = content;

                        const offset = replacement.length - (toPos - fromPos);


                        suggestionSpan.nextElementSibling.remove();
                        button.remove();
                        suggestionSpan.remove();


                        updateSuggestionPositions(fromPos, toPos, offset);
                        document.getElementById('return').style.display = 'inline-block';

                    };

                    const updateSuggestionPositions = (fromPos, toPos, offset) => {
                        const suggestionSpans = document.querySelectorAll('.suggestion');
                        suggestionSpans.forEach(span => {
                            const currentFromPos = parseInt(span.dataset.frompos);
                            const currentToPos = parseInt(span.dataset.topos);

                            if (currentFromPos > fromPos) {
                                span.dataset.frompos = currentFromPos + offset;
                                span.dataset.topos = currentToPos + offset;
                            }
                        });
                    };

                    const returnall = () => {
                        const originalText = "${originalText}";
                        const contentTextarea = document.getElementById('content');
                        contentTextarea.value = originalText;

                        document.querySelectorAll('.tat').forEach(button => {
                            button.style.display = 'none';
                        });
                        const fix = document.querySelector('.fixx');
                        if (fix) {
                            fix.style.display = 'none';
                        }

                        document.getElementById('status').innerText = "Returned to original text.";


                    };

                    const submitForm = (action) => {


                        const form = document.getElementById('mainForm');

                        form.action = action;
                        form.submit();

                    }
                    ;

                    setTimeout(() => {
                        const succ = document.getElementById("succ");
                        const nothing = document.getElementById("noti");
                        if (succ || nothing) {
                            nothing.style.display = "none";
                            succ.style.display = "none";
                        }
                    }, 2000);
                </script>
                <p id="succ" style="color:green;">${succ}</p>
                <p id="noti" style="color:red;">${nothing}</p>
            </main>
            <aside class="sidebar">
                <div class="logo">
                    <img onclick="location.href = 'home.jsp';" src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
                    <p>Home</p>
                </div>
                <div class="container-fix">
                    <h4 class="hat4">Grammatical errors</h4>
                    <div class="fix"  style="height: 15vh;margin : 10px 30px;">
                        <p>${result}</p>
                    </div>
                </div>
                <div class="container-fix">
                    <h4 class="hat4" >Review suggestions</h4>
                    <div class="fix" style="height: 15vh; margin : 10px 30px;">
                        <p class="fixx" id ="fixx">${suggestions}</p>
                    </div>
                </div>   
                <h4 class="hat4">Suggestions from library</h4>
                <div class="fix"  style="height: 15vh; margin:10px 30px;">
                    <c:if test="${not empty suggestlib}"> 


                        <table border="1" style="width: 80%; margin: 0px auto; border-collapse: collapse; text-align: left;">
                            <thead>
                                <tr style="background-color: #DEDCFF;">
                                    <th style="padding: 10px; border: 1px solid #ddd;">Words</th>
                                    <th style="padding: 10px; border: 1px solid #ddd;">Definition</th>
                                    <th style="padding: 10px; border: 1px solid #ddd;">Change</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="c" items="${suggestlib}" varStatus="status">
                                    <tr style="border: 1px solid #ddd;">
                                        <td style="padding: 10px; border: 1px solid #ddd;">${c.keyword}</td>
                                        <td style="padding: 10px; border: 1px solid #ddd;">${c.define}</td>
                                        <td style="padding: 10px; border: 1px solid #ddd;">
                                            <form id="changeWordForm${status.index}" action="CheckGrammars" method="post">
                                                <input type="hidden" name="word" value="${c.keyword}">
                                                <input type="hidden" name="defi" value="${c.define}">
                                                <input type="hidden" name="content" value="${contentE}">

                                                <input type="hidden" name="essayId" value=" ${essayId}">
                                            </form>
                                            <button style="padding: 5px 10px; background-color: #9890FF; color: white; border: none; border-radius: 5px; cursor: pointer;" 
                                                    onclick="document.getElementById('changeWordForm${status.index}').submit();">
                                                Change word
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                </div>

            </aside>
        </div>

    </body>
</html>
