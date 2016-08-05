package image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * The IdentityServlet class is used to generate four random characters with a JPEG image.
 *
 * @author musenboy
 * @date 2016/7/28
 */
public class IdentityServlet extends HttpServlet {

    /**
     *  An array of characters.
     *  Except 0,1,I,O and o.
     */
    private static final char[] CHARS = {
            '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static Random rand = new Random();

    private static String getRandomString() {
        final StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            buffer.append(CHARS[rand.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    private static Color getRandomColor() {
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    private static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        String randomString = getRandomString();
        request.getSession(true).setAttribute("randomString", randomString);

        int imageWidth = 85;
        int imageHeight = 25;

        Color color = getRandomColor();
        Color reverse = getReverseColor(color);

        BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        g.setColor(color);
        g.fillRect(0, 0, imageWidth, imageHeight);
        g.setColor(reverse);
        g.drawString(randomString, 18, 18);
        for(int i = 0, n = rand.nextInt(100); i < n; i ++) {
            g.drawRect(rand.nextInt(imageWidth), rand.nextInt(imageHeight), 1, 1);
        }

        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bi);
        out.flush();
        out.close();
    }
}
