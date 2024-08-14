package umg.principal;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import umg.principal.botTelegram.Bot;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {

        TelegramBotsApi BotApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot mibot = new Bot();
        BotApi.registerBot(mibot);
        System.out.println("el bot se esta ejecutando");
    }catch (Exception ex){
        System.out.println("erro al iniciar telegram "+ex.getMessage());
    }

}
}