package core;

/*
* Copyright (c) 2015, Gush Vadim Vadimovich
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this
* list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those
* of the authors and should not be interpreted as representing official policies,
* either expressed or implied, of the FreeBSD Project.
*/

public class Language {

    private static String high = "High";
    private static String low = "Low";
    private static String help = "Help";
    private static String sell = "Sell";
    private static String buy = "Buy";
    private static String donate = "Donate";
    private static String rate = "Rate";
    private static String about = "About";

    public static void setRussian() {
        high = "Макс.";
        low = "Мин.";
        help = "Помощь";
        sell = "Продать";
        buy = "Купить";
        donate = "Помочь";
        rate = "Курс";
        about = "О программе";
    }

    public static void setEnglish() {
        high = "High";
        low = "Low";
        help = "Help";
        sell = "Sell";
        buy = "Buy";
        donate = "Donate";
        rate = "Rate";
        about = "About";
    }

    public static String getAbout() {
        return about;
    }

    public static String getBuy() {
        return buy;
    }

    public static String getDonate() {
        return donate;
    }

    public static String getHelp() {
        return help;
    }

    public static String getHigh() {
        return high;
    }

    public static String getLow() {
        return low;
    }

    public static String getRate() {
        return rate;
    }

    public static String getSell() {
        return sell;
    }

}
