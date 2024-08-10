package controller.check_plagiarism;

import model.DAOEssay;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckPlagiarismServlet extends HttpServlet {

    // Danh sách các văn bản từ nguồn ngoài cơ sở dữ liệu
    private static final List<String> externalReferenceTexts = Arrays.asList(
            "This is a sample external reference text for plagiarism checking.",
            "Another example of external reference text.",
            // Thêm các văn bản khác tại đây
            "In the realm of possibilities, where the sun sets over distant horizons, there lies a world uncharted by mankind. It is a place where dreams are woven into the fabric of reality, where every star in the night sky tells a story, and where the whispers of the wind carry secrets of the ages. This is a world of wonder and mystery, a place where the boundaries of imagination are tested and the spirit of adventure thrives.",
            "As dawn breaks over the serene landscape, the first rays of sunlight dance across the dewdrops, creating a spectacle of color and light. The forest awakens, with birds chirping in harmony and the gentle rustle of leaves as the breeze sweeps through the trees. It is a new day, a fresh start, where every living creature feels the promise of endless possibilities and the beauty of the natural world.",
            "In the bustling city, where the streets are lined with towering skyscrapers and the hum of activity never ceases, there is a sense of relentless pursuit. People from all walks of life converge here, each with their own dreams and aspirations, striving to carve out a space for themselves. The city is a living, breathing entity, pulsating with energy and vitality, a testament to human ambition and the relentless quest for progress.",
            "On the shores of a tranquil lake, the water mirrors the azure sky, and the gentle lapping of the waves provides a soothing soundtrack. It is a place of reflection and solitude, where one can escape the cacophony of everyday life and find peace within. The lake is a sanctuary, a haven for those seeking to reconnect with nature and their inner selves, a reminder of the simple joys and profound tranquility that can be found in the world around us.",
            "High in the mountains, where the air is crisp and the peaks pierce the heavens, there lies a realm of majesty and grandeur. The rugged terrain challenges the adventurer, demanding resilience and strength, yet offering unparalleled rewards. The view from the summit, where the world stretches out in all directions, is a humbling experience, a reminder of the vastness of the earth and the boundless potential that lies within each of us.",
            "In the heart of the desert, where the sands shift and the horizon seems to stretch into infinity, there is a stark beauty that captivates the soul. The desert is a land of extremes, where survival demands ingenuity and perseverance. Yet, amidst the harshness, there is a delicate balance, a fragile ecosystem that thrives in the face of adversity, embodying the resilience and tenacity of life itself.",
            "In the depths of the ocean, where sunlight fades and the mysteries of the deep unfold, there exists a world unlike any other. The ocean is a realm of wonder, home to creatures both strange and beautiful, and a source of endless fascination for those who explore its depths. It is a reminder of the unknown, the uncharted territories that lie beyond our reach, and the endless possibilities that await discovery.",
            "In the quiet of the countryside, where rolling hills meet vast fields of green, there is a sense of timelessness. The pace of life is slower here, allowing for moments of introspection and appreciation for the simple pleasures. The countryside is a place of connection, where communities are built on shared experiences and the rhythms of nature dictate the flow of life.",
            "On the edge of the forest, where the wilderness meets the cultivated land, there is a harmony between man and nature. It is a place of balance, where the beauty of the wild is preserved and respected, and the fruits of human labor are nurtured and harvested. This boundary, where the known meets the unknown, is a testament to the delicate relationship between humanity and the natural world.",
            "In the ancient ruins, where history whispers through the stones and the echoes of the past linger in the air, there is a profound sense of heritage. These remnants of bygone eras tell stories of civilizations long gone, of triumphs and tragedies, and the indomitable spirit of human endeavor. The ruins are a bridge to the past, a reminder of the enduring legacy of those who came before us and the timeless quest for meaning and understanding.",
            "Beneath the starry expanse of the night sky, where constellations form patterns that have guided explorers for centuries, there lies a universe of infinite possibilities. The cosmos, with its galaxies, nebulae, and black holes, is a testament to the grandeur and mystery of creation. It is a realm that challenges our understanding and inspires us to reach beyond the confines of our planet, to seek out the unknown and unravel the secrets of the universe.",
            "In the quiet moments of twilight, when the world transitions from day to night, there is a sense of magic in the air. The sky is painted with hues of orange, pink, and purple, creating a breathtaking canvas that captures the beauty of nature's cycles. This fleeting time, when the day bids farewell and the night embraces the earth, is a reminder of the continuous flow of time and the ever-changing nature of life.",
            "Amidst the rolling waves of the open sea, where the horizon seems to merge with the sky, there is a feeling of boundless freedom. The ocean, with its vast expanse and hidden depths, is a place of adventure and discovery. It calls to the hearts of those who seek to explore its mysteries, to sail its waters, and to experience the thrill of the unknown. The sea is a symbol of endless possibilities and the indomitable spirit of exploration.",
            "In the heart of the rainforest, where the canopy towers above and the undergrowth teems with life, there exists a world of incredible biodiversity. The rainforest is a living tapestry, woven with the threads of countless species, each playing a role in the intricate balance of the ecosystem. It is a place of wonder and awe, where the beauty and complexity of nature are on full display, and where the importance of conservation and respect for the natural world is profoundly evident.",
            "In the stillness of a library, where shelves are lined with books that hold the collective knowledge and wisdom of humanity, there is a profound sense of reverence. The library is a sanctuary for the mind, a place where ideas are preserved and shared, and where the quest for understanding is nurtured. It is a testament to the power of written word and the enduring impact of literature on our lives and our societies.",
            "On a snowy mountain peak, where the air is thin and the silence is profound, there is a feeling of isolation and introspection. The mountain, with its challenging ascent and breathtaking views, is a symbol of personal growth and achievement. It stands as a testament to the strength and resilience required to overcome obstacles and reach new heights, both physically and metaphorically.",
            "In the bustling marketplace, where the air is filled with the sounds of bargaining and the scents of diverse cuisines, there is a vibrant energy. The marketplace is a melting pot of cultures, a place where people come together to exchange goods, stories, and traditions. It is a microcosm of the larger world, reflecting the richness of human diversity and the interconnectedness of global communities.",
            "In the glow of a campfire, where stories are shared and bonds are strengthened, there is a sense of camaraderie and warmth. The campfire is a gathering place, where the barriers of daily life fade away and the simple pleasures of conversation and connection come to the fore. It is a timeless tradition, a reminder of our shared humanity and the importance of community and friendship."
    );

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckPlagiarismServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckPlagiarismServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputText = request.getParameter("content");
        request.setAttribute("text", inputText);
        // Lấy các văn bản tham chiếu từ cơ sở dữ liệu
        DAOEssay daoEssay = new DAOEssay();
        List<String> referenceTexts = daoEssay.getAllEssayContents();

        int maxSimilarityOutside = 0;
        int maxSimilarityInside = 0;

        // Kiểm tra với các văn bản tham chiếu từ nguồn ngoài cơ sở dữ liệu
        for (String refText : externalReferenceTexts) {
            int similarity = FuzzySearch.ratio(inputText, refText);
            if (similarity > maxSimilarityOutside) {
                maxSimilarityOutside = similarity;
            }
        }

        // Kiểm tra với các văn bản tham chiếu trong cơ sở dữ liệu
        for (String refText : referenceTexts) {
            int similarity = FuzzySearch.ratio(inputText, refText);
            if (similarity > maxSimilarityInside) {
                maxSimilarityInside = similarity;
            }
        }

        // Đặt kết quả vào request attribute
        request.setAttribute("maxSimilarityOutside", maxSimilarityOutside);
        request.setAttribute("maxSimilarityInside", maxSimilarityInside);

        // Chuyển tiếp tới JSP
        request.getRequestDispatcher("text.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
