package com.multimedia.aes.gestnet_ssl.Utils;

public class FormatStringTicket {

    public static int TICKETMAXCHARS = 32;

    public static String format(String texto, String valor){
        int spaces = TICKETMAXCHARS;
        spaces -= texto.length();
        spaces -= valor.length();
        spaces -= "\n".length();
        String finaltext = texto;
        for (int i = 0; i < spaces; i++){
            finaltext += " ";
        }
        finaltext += valor + "\n";
        return finaltext;
    }
}
