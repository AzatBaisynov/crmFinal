package kg.itacademy.finalproject.service;

import java.time.LocalDate;

public class TextToDateParser {
    public static String getDate(String text){
        String fullDate;
        String day;

        if(text.contains("за первое")){
            day = "01";
        } else if(text.contains("за второе")) {
            day = "02";
        } else if(text.contains("за третье")) {
            day = "03";
        } else if(text.contains("за четвертое")) {
            day = "04";
        } else if(text.contains("за пятое")) {
            day = "05";
        } else if(text.contains("за шестое")) {
            day = "06";
        } else if(text.contains("за седьмое")) {
            day = "07";
        } else if(text.contains("за восьмое")) {
            day = "08";
        } else if(text.contains("за девятое")) {
            day = "09";
        } else if(text.contains("за десятое")) {
            day = "10";
        } else if(text.contains("за одинадцатое")) {
            day = "11";
        } else if(text.contains("за двенадцатое")) {
            day = "12";
        } else if(text.contains("за тринадцатое")) {
            day = "13";
        } else if(text.contains("за четырнадцатое")) {
            day = "14";
        } else if(text.contains("за пятнадцатое")) {
            day = "15";
        } else if(text.contains("за шестнадцатое")) {
            day = "16";
        } else if(text.contains("за семнадцатое")) {
            day = "17";
        } else if(text.contains("за восемнадцатое")) {
            day = "18";
        } else if(text.contains("за девятнадцатое")) {
            day = "19";
        } else if(text.contains("за двадцатое")) {
            day = "20";
        } else if(text.contains("за двадцать первое")) {
            day = "21";
        } else if(text.contains("за двадцать второе")) {
            day = "22";
        } else if(text.contains("за двадцать третье")) {
            day = "23";
        } else if(text.contains("за двадцать четвертое")) {
            day = "24";
        } else if(text.contains("за двадцать пятое")) {
            day = "25";
        } else if(text.contains("за двадцать шестое")) {
            day = "26";
        } else if(text.contains("за двадцать седьмое")) {
            day = "27";
        } else if(text.contains("за двадцать восьмое")) {
            day = "28";
        } else if(text.contains("за двадцать девятое")) {
            day = "29";
        } else if(text.contains("за тридцатое")) {
            day = "30";
        } else if(text.contains("за тридцать первое")) {
            day = "31";
        } else if(text.contains("за сегодня")) {
            return LocalDate.now().toString();
        } else if(text.contains("за вчера")) {
            return LocalDate.now().minusDays(1).toString();
        } else {
            return null;
        }

        if(text.contains("января")){
            fullDate = "2020-01-" + day;
        } else if(text.contains("февраля")){
            fullDate = "2020-02-" + day;
        } else if(text.contains("марта")){
            fullDate = "2020-03-" + day;
        } else if(text.contains("апреля")){
            fullDate = "2020-04-" + day;
        } else if(text.contains("мая")){
            fullDate = "2020-05-" + day;
        } else if(text.contains("июня")){
            fullDate = "2020-06-" + day;
        } else if(text.contains("июля")){
            fullDate = "2020-07-" + day;
        } else if(text.contains("августа")){
            fullDate = "2020-08-" + day;
        } else if(text.contains("сентября")){
            fullDate = "2020-09-" + day;
        } else if(text.contains("октября")){
            fullDate = "2020-10-" + day;
        } else if(text.contains("ноября")){
            fullDate = "2020-11-" + day;
        } else if(text.contains("декабря")){
            fullDate = "2020-12-" + day;
        } else if(text.contains("число")){
            int monthValue = LocalDate.now().getMonthValue();
            String month;
            if(monthValue<10){
                month = "0"+monthValue;
            } else {
                month = monthValue + "";
            }
            fullDate = "2020-" + month + "-" + day;
        } else {
            return null;
        }
        return fullDate;
    }
}
