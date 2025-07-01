package org.bomfim.chapter11;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoadingPropertiesWithResourceBundles {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println(Locale.getDefault());
        ResourceBundle zoo = ResourceBundle.getBundle("Zoo", Locale.FRANCE);
        System.out.println(zoo.getString("hello")); //Bonjour
        System.out.println(zoo.getString("walk")); //marcher
        System.out.println(zoo.getString("open")); //Le zoo est ouvert!
        System.out.println(zoo.getString("sleep")); //sleep default
        System.out.println(zoo.getString("run")); //Run -> Returns from the default
//        System.out.println(zoo.getString("hello2")); //java.util.MissingResourceException: Can't find resource for bundle
        System.out.println(ResourceBundle.getBundle("Zoo").getString("hello"));
        System.out.println(ResourceBundle.getBundle("Zoo").getString("sleep"));
        Locale.setDefault(Locale.of("pt", "BR"));
//        System.out.println(ResourceBundle.getBundle("Zoo").getString("hello")); //Can't find bundle for base name Zoo, locale pt_BR
        System.out.println(ResourceBundle.getBundle("Zoo").getString("hello")); //Get default properties

        System.out.println("""
                \n
                The order is to get the file more specific and then the others.
                Example:
                1. Zoo_fr_FR.properties //Requested locale
                2. Zoo_fr.properties //Language we requested with no country
                3. Zoo.properties //default bundle
                
                BE CAREFUL: the order above is in case there is a match with the requested locale.

                In case there is not match, the ResourceBundle will try to get the config file following this order:

                1. Zoo_en_US.properties //default locale
                2. Zoo_en.properties //default locale's language with no country
                3. Zoo.properties //default bundle
                //Exception if not found
                
                > Once a resource bundle has been selected, only properties along a single hierarchy will be used
                """);

        Locale.setDefault(Locale.of("en"));
        String sleep = ResourceBundle.getBundle("Zoo", Locale.of("br")).getString("sleep");
        System.out.println(sleep); //SLEEP

        ResourceBundle messageResourceBundle = ResourceBundle.getBundle("Message");
        String message = messageResourceBundle.getString("hello");
        System.out.println(MessageFormat.format(message, "Thomas")); //Hello Thomas and {1}
        System.out.println(MessageFormat.format(message, "Thomas", "James")); //Hello Thomas and James
        System.out.println(MessageFormat.format(message, "Thomas", "James", "Tammy")); //Hello Thomas and James

        Properties properties = new Properties();
        properties.put("hello", "Thomas");
        properties.put("sleep", "James");
        System.out.println(properties.get("hello")); //Thomas
        System.out.println(properties.getProperty("hello")); //Thomas
        System.out.println(properties.get("hello2")); //null
//        System.out.println(properties.get("hello2", "defaultValue")); //does not compile
        System.out.println(properties.getProperty("hello2", "defaultValue")); //defaultValue
    }

}
