package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserAuthentication {
    /** Переменная для хранения имени текущего пользователя */
    private static String currentUser = null;
    public static String getCurrentUser() {
        return currentUser;
    }
    public static void logOut() {
        currentUser = null;
    }
    /** Метод для регистрации нового пользователя
     * @param login имя нового пользователя
     * @see DatabaseConnection#executePreparedStatement(String, String...)
     * @see MessageDigest#digest(byte[]) метод для получения хэша
     * @see UserAuthentication#saltGetter() */
    public static boolean userRegistration(String login, String password) {
        try {
            if (!(login.contains("'") | Objects.requireNonNull(DatabaseConnection.executePreparedStatement("SELECT * FROM USERS WHERE login = ?", login)).next())) {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                String salt = saltGetter();
                byte[] hash = md.digest(("*63&^mVLC(#" + password + salt).getBytes(StandardCharsets.UTF_8));
                DatabaseConnection.executePreparedStatement("INSERT INTO USERS (login, hash, salt) VALUES (?, ?, ?)", login, Arrays.toString(hash), salt);
                System.out.println("Вы успешно прошли регистрацию");
                return true;
            }
        } catch (SQLException | NullPointerException | NoSuchAlgorithmException ignored) {}
        return false;
    }
    /** Метод для вхождения существующего пользователя в программу
     * @param login имя пользователя
     * @see DatabaseConnection#executePreparedStatement(String, String...)
     * @see MessageDigest#digest(byte[]) метод для получения хэша
     * @see UserAuthentication#saltGetter() */
    public static boolean userLoggingIn(String login, String password) {
        try {
            if (!login.contains("'") | Objects.requireNonNull(DatabaseConnection.executePreparedStatement("SELECT * FROM USERS WHERE login = ?", login)).next()) {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                ResultSet resultSetSalt = DatabaseConnection.executePreparedStatement("SELECT salt FROM USERS WHERE login = ?", login);
                resultSetSalt.next();
                String salt = resultSetSalt.getString(1);
                ResultSet resultSetHash = DatabaseConnection.executePreparedStatement("SELECT hash FROM USERS WHERE login = ?", login);
                resultSetHash.next();
                byte[] hash = md.digest(("*63&^mVLC(#" + password + salt).getBytes(StandardCharsets.UTF_8));
                if (Arrays.toString(hash).equals(resultSetHash.getString(1))) {
                    currentUser = login;
                    return true;
                }
            }
        } catch (SQLException | NullPointerException | NoSuchAlgorithmException ignored) {}
        return false;
    }
    /** Метод для получения "соли" для более безопасного хэширования паролей
     * @return возвращает случайный набор из 20 символов */
    private static String saltGetter() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) new Random().nextInt(40, 126));
        }
        return stringBuilder.toString();
    }
}
