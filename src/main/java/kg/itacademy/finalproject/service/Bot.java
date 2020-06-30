package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.controller.TelegramController;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//import static controller.TeleButtons.setButtons;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Bot extends TelegramLongPollingBot {

    public  void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
//            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public  void sendMsgWithPhoto(Message message, String text, String photoPath) throws FileNotFoundException {
        SendPhoto msg = new SendPhoto().setPhoto("Статистика", new FileInputStream(new File(photoPath)));
        msg.setChatId(message.getChatId().toString());
        msg.setCaption(text);
        try {
           execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public  void sendMsgNoReply(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
//            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {
        try {
            TelegramController tg = new TelegramController();
            tg.runUpdate(update);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String sendToVoiceApi(Object file) throws IOException {
        HttpResponse response = Unirest.post("https://api.wit.ai/speech")
                .header("Content-Type", "audio/ogg")
                .header("Authorization", "Bearer ORNQQ25XWFYH2LIV7BKMK5ZGDGWNIPLR")
                .field("audio", file)
                .asJson();
        String text =  response.getBody().toString();
        text = text.replace("{\"entities\":{},\"intents\":[],\"text\":\"","");
        text = text.replace("\",\"traits\":{}}", "");
        return text;
    }

    public static String getFilePath(String token, String fileId){
        HttpResponse response = Unirest.get("https://api.telegram.org/bot" + token + "/getFile?file_id=" + fileId)
                .header("cache-control", "no-cache")
                .asJson();
        return response.getBody().toString();
    }

    public static String downloadFile(String token, String webPath){
        String pathName = "C:\\Users\\Азат\\Desktop\\audio\\"+ System.currentTimeMillis() + ".oga";
        HttpResponse response = Unirest.get("https://api.telegram.org/file/bot" + token + "/" + webPath)
                .header("cache-control", "no-cache")
                .asFile(pathName);
        return pathName;
    }

    public static String parserSite(String str){
        str = str.replace("\"file_path\":\"", "①");
        int index = str.indexOf("①");
        str = str.substring(index + 1, str.length()-3);
        return str;
    }


    public String getBotUsername() {
        return "Easy Storage";
    }

    public String getBotToken() {
        return "1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4";
    }
}
