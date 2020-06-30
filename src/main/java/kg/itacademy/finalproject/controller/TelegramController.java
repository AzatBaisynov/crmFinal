package kg.itacademy.finalproject.controller;

import com.vdurmont.emoji.EmojiParser;
import kg.itacademy.finalproject.model.*;
import kg.itacademy.finalproject.service.*;


import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;

import java.io.*;
import java.util.List;
import static kg.itacademy.finalproject.service.Bot.*;

public class TelegramController {

    public void runUpdate(Update update) throws IOException {
        Bot bot = new Bot();
        JDBCProducts JDBCProducts = new JDBCProducts();
        Message message = update.getMessage();

        if (message.getText() != null && message.getText().equals("/start")) {
            bot.sendMsg(message, "Введите ключ:");
        }

        if (message.hasText() && message.getText().contains("Key ") && message.getText() != null) {
            Integer companyId = JDBCProducts.selectFromUsers(message.getText());
            String response = JDBCProducts.createTelegramRegistration(message.getText(), message.getChatId().toString(), companyId);
            bot.sendMsg(message, response);
        }

        if (message.getVoice() != null) {
            Voice voice = message.getVoice();
            String fileId = message.getVoice().getFileId();
            String teleFilePath = getFilePath("1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4", fileId);
            String parsed = parserSite(teleFilePath);
            String filePath = downloadFile("1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4", parsed);
            String output = filePath.replace(".oga", ".ogg");
            AudioDecoderFFjpeg.decode(filePath, output);
            String text = sendToVoiceApi(new File(output));

            System.out.println(text);

            String dateString = TextToDateParser.getDate(text);
            System.out.println(dateString);

            if (text.contains("привет")) {
                bot.sendMsg(message, "приветик");
            } else if (text.contains("кто твой создатель")) {
                bot.sendMsg(message, "Мой создатель Байсынов Азат");
            } else if (text.contains("товаров") && !text.equals("товары на складе") || text.contains("товары") && !text.equals("товары на складе") || text.contains("продукты") && !text.equals("товары на складе")
                    || text.contains("продуктов") && !text.equals("товары на складе") || text.contains("товар") && !text.equals("товары на складе")) {
                Integer companyId = JDBCProducts.selectFromTelegramReg(message.getChatId().toString());
                List<TelegramProduct> list = JDBCProducts.selectProductFromUsers(companyId);
                for (TelegramProduct tp : list) {
                    if (tp.getUrl() != null) {
                        String photoPath = tp.getUrl();
                        bot.sendMsgWithPhoto(message, "Название продукта: " + tp.getProductName(), photoPath);
                    } else {
                        bot.sendMsgNoReply(message, "Изображение: отсутствует\nНазвание продукта: " + tp.getProductName());
                    }
                }
            } else if (text.contains("покупки") || text.contains("покупок") || text.contains("закупки") || text.contains("закупок") || text.contains("покуп")) {
                JDBCPurchases jdbcPurchases = new JDBCPurchases();
                Integer companyId = JDBCProducts.selectFromTelegramReg(message.getChatId().toString());
                List<PurchaseSalesProductsListModel> list = jdbcPurchases.selectFromUsers(companyId, dateString);
                bot.sendMsg(message, "Вывожу список покупок за " + dateString + ":");
                list.forEach(x -> bot.sendMsgNoReply(message, x.toString()));
            } else if (text.contains("прибыль") || text.contains("прибыли")) {
                String caseSmile = EmojiParser.parseToUnicode(":briefcase:");
                String finishSmile = EmojiParser.parseToUnicode(":checkered_flag:");
                String calendarSmile = EmojiParser.parseToUnicode(":date:");
                bot.sendMsg(message, "Прибыль за " + dateString + " :");
                Integer companyId = JDBCProducts.selectFromTelegramReg(message.getChatId().toString());
                JDBCProfit jdbcProfit = new JDBCProfit();
                List<JDBCSalesModel> list = jdbcProfit.getModel(companyId, dateString);

                for (JDBCSalesModel model : list) {
                    List<JDBCRevenueModel> list1 = model.getList();
                    String revenue = "";
                    for (JDBCRevenueModel rev : list1) {
                        revenue = revenue + rev.toString();
                    }
                    bot.sendMsgNoReply(message, caseSmile + "Сотрудник: " + model.getName() + "\n\n" +
                            revenue + "\n" + finishSmile + "Итого: " + model.getTotal());
                }
            } else if (text.contains("продажи") || text.contains("продаж") || text.contains("продаже") || text.contains("продажей")) {
                JDBCSales jdbcSales = new JDBCSales();
                Integer companyId = JDBCProducts.selectFromTelegramReg(message.getChatId().toString());
                List<PurchaseSalesProductsListModel> list = jdbcSales.selectFromUsers(companyId, dateString);
                bot.sendMsg(message, "Вывожу список продаж за " + dateString + ":");
                list.forEach(x -> bot.sendMsgNoReply(message, x.toString()));
            } else if (text.contains("склад") || text.contains("хранилище") || text.contains("товары на складе") || text.contains("хранилища") || text.contains("склада")) {
                JDBCStorage jdbcStorage = new JDBCStorage();
                Integer companyId = JDBCProducts.selectFromTelegramReg(message.getChatId().toString());
                List<StorageModel> list = jdbcStorage.selectFromUsers(companyId, dateString);
                bot.sendMsg(message, "Склад:");
                list.forEach(x -> bot.sendMsgNoReply(message, x.toString()));
            }
            File file1 = new File(filePath);
            file1.delete();
            File file2 = new File(output);
            file2.delete();
        }
    }
}