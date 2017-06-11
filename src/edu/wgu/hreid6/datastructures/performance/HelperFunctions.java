package edu.wgu.hreid6.datastructures.performance;

/**
 * HR
 *
 * Created by hreid6 on 1/29/17.
 */
public class HelperFunctions {

    /**
     * HR
     *
     * Return suggested key used for comparison operations,.
     *
     * @param firstName
     * @param lastName
     * @return
     */
    static public String getRubicKey(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return new StringBuilder()
                    .append(firstName.toUpperCase())
                    .append(lastName.toUpperCase()).toString();
        } else {
            return null;
        }
    }
}
