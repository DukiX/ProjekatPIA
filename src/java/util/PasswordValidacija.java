/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dusan
 */
public class PasswordValidacija {

    private static Matcher matcher;

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[a-zA-Z]{1}[A-Za-z\\d$@$!%*?&]{7,11}";
    private static final String PASSWORD_PATTERN2 = ".*?[a-z].*?[a-z].*?[a-z].*?";
    private static final String PASSWORD_PATTERN3 = ".*?(.)\\1{2,}.*?";

    private static Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static Pattern pattern2 = Pattern.compile(PASSWORD_PATTERN2);
    private static Pattern pattern3 = Pattern.compile(PASSWORD_PATTERN3);

    public static boolean validate(String password) {

        matcher = pattern.matcher(password);
        boolean temp = matcher.matches();
        if (temp) {
            matcher = pattern2.matcher(password);
            boolean temp2 = matcher.matches();
            if (temp2) {
                matcher = pattern3.matcher(password);
                boolean temp3 = matcher.matches();
                return !temp3;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
