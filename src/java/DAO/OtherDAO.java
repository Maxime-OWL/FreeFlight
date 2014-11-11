/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author ducfpt
 */
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class OtherDAO {

    private static final Set<String> generatedNumbers = new HashSet<>();

    private Connection conn = null;
    private Statement state = null;
    private ResultSet rs = null;
    private PreparedStatement pre = null;
    private Session session = null;
    Message message = null;

    public OtherDAO() {
        if (this.conn == null) {
            try {
                //System.out.println("Connecting to DB using the following details:");
                javax.naming.Context ctx = new javax.naming.InitialContext();
                String host = (String) ctx.lookup("java:comp/env/db-host"); //System.out.println(host);
                String port = (String) ctx.lookup("java:comp/env/db-port"); //System.out.println(port);
                String database = (String) ctx.lookup("java:comp/env/db-database"); //System.out.println(database);
                String username = (String) ctx.lookup("java:comp/env/db-username"); //System.out.println(username);
                String password = (String) ctx.lookup("java:comp/env/db-password"); //System.out.println(password);
                connection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8", username, password);
            } catch (NamingException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void connection(String ulr, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(ulr, username, password);
            //System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        final int maxLength = 7;
        final int maxTry = 10;
        //OtherDAO other = new OtherDAO();
    }

    public String makeRandomString(final int maxLength, final int maxTry) {
        final Random random = new Random(System.nanoTime());
        final int max = (int) Math.pow(10, maxLength);
        final int maxMin = (int) Math.pow(10, maxLength - 1);
        int i = 0;
        boolean unique = false;
        int randomId = -1;
        while (i < maxTry) {
            randomId = random.nextInt(max - maxMin - 1) + maxMin;

            synchronized (generatedNumbers) {
                if (generatedNumbers.contains(randomId) == false) {
                    unique = true;
                    break;
                }
            }
            i++;
        }
        if (unique == false) {
            throw new RuntimeException("Cannot generate unique id!");
        }

        synchronized (generatedNumbers) {
            generatedNumbers.add(String.valueOf(randomId));
        }

        return String.valueOf(randomId);
    }

    public String getMd5FromString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    public String formatDate(String datetime) throws ParseException {

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(datetime);

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("MMMMM dd, yyyy");
        return dt1.format(date);
    }

    public String getEncryptedPassword(String rawPassword, String salt) throws NoSuchAlgorithmException {
        String step1 = this.getMd5FromString(rawPassword);
        String step2 = step1 + salt;
        String encryptedPassword = this.getMd5FromString(step2);
        return encryptedPassword;
    }

    public boolean sendMail(String sendTo, String subject, String body) {
        final String username = "tupvse02404@fpt.edu.vn";
        final String password = "vantu1992";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,
                                password);
                    }
                });
        try {

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(sendTo));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    public boolean cleanToken() {
        try {
            String sql = "DELETE FROM token WHERE (TIME_TO_SEC(TIMEDIFF(dateline,NOW()))/3600) > lifetime ";
            state = (com.mysql.jdbc.Statement) conn.createStatement();
            state.executeUpdate(sql);
            state.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Token failed to clean!");
            return false;
        }
    }

    public boolean cleanUserToken(int userId) {
        try {
            String sql = "DELETE FROM token WHERE userid = " + userId;
            state = (com.mysql.jdbc.Statement) conn.createStatement();
            state.executeUpdate(sql);
            state.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Token failed to clean!");
            return false;
        }
    }

    public String[] getTokenData(String token) {
        String[] tokenData = new String[4];
        try {
            OtherDAO other = new OtherDAO();
            other.cleanToken();
            String sql = "SELECT * FROM token WHERE token = ? LIMIT 1";
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setString(1, token);
            rs = pre.executeQuery();
            rs.next();
            tokenData[0] = rs.getString("id");
            tokenData[1] = rs.getString("token");
            tokenData[2] = rs.getString("userid");
            tokenData[3] = rs.getString("dateline");
            return tokenData;
        } catch (SQLException ex) {
            Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return tokenData;
        }
    }

    public String makeUniqueToken() {
        String newToken = this.makeRandomString(10, 10);
        if (newToken.equals(this.getTokenData(newToken)[1])) {
            return "existed";
        }
        return newToken;
    }

    public String makeTokenForUser(int userId, int tokenLifetime) {
        try {
            OtherDAO other = new OtherDAO();
            other.cleanUserToken(userId);
            String uniqueToken = "existed";
            while (uniqueToken.equalsIgnoreCase("existed")) {
                uniqueToken = other.makeUniqueToken();
            }
            String newToken = other.makeRandomString(10, 10);
            String sql = "INSERT INTO token (token, userid, lifetime) VALUES (?,?,?)";
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setString(1, newToken);
            pre.setInt(2, userId);
            pre.setInt(3, tokenLifetime);
            pre.executeUpdate();

            return newToken;
        } catch (SQLException ex) {
            //Logger.getLogger(OtherDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("makeTokenForUser - Failed.");
            return "existed";
        }
    }

    public double getValidPrice(String str) {
        double d = 0;
        try {
            d = Double.parseDouble(str);
            if (d < 0) {
                return -1;
            }
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return d;
    }

    public boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty() && password.length() >= 6;
    }

    public String getValidYoutubeUrl(String v_youtube) {
        if (v_youtube == null || !v_youtube.contains("?v=")) {
            return "";
        }
        //System.out.println("Processing string: '"+v_youtube+"'");
        int startIndex = v_youtube.indexOf("?v=");
        if (startIndex == -1) {
            return "";
        }
        startIndex = startIndex + 3;
        //System.out.println("Index where v= at is "+startIndex);
        String result = v_youtube.substring(startIndex, startIndex + 11);
        if (result != null && result.length() == 11) {
            return result;
        }
        return "";
    }

    public DateTime getDateTimeFromString(String dateString) {
        if (dateString.isEmpty()) {
            return DateTime.now();
        }
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
        DateTime dt = formatter.parseDateTime(dateString);
        return dt;
    }
    public DateTime getDateTimeFromString2(String dateString) {
        if (dateString.isEmpty()) {
            return DateTime.now();
        }
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime dt = formatter.parseDateTime(dateString);
        return dt;
    }
}
