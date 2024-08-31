package umg.principal.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class botCuestionario extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Mau9182bot"; // Pon aquí el nombre de tu bot
    }

    @Override
    public String getBotToken() {
        return "7280178480:AAGHI6raHgLXUnJCwXwk5fC0ZPyfcdzq0vk"; // Pon aquí el token de tu bot
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String receivedText = update.getMessage().getText();

            if (receivedText.equals("/start")) {
                handleUser(chatId, "usuario@ejemplo.com"); // Simulación de email
            } else if (receivedText.matches("\\d+")) { // Verifica si el texto recibido es un número
                handleAgeResponse(chatId, receivedText);
            }
        }
    }

    public void handleAgeResponse(long chatId, String receivedText) {
        try {
            int age = Integer.parseInt(receivedText);
            if (age >= 0 && age <= 120) {
                sendText(chatId, "Edad registrada: " + age + " años.");
                // Continuar con la siguiente pregunta o sección
            } else {
                sendText(chatId, "Por favor, ingresa una edad válida entre 0 y 120.");
            }
        } catch (NumberFormatException e) {
            sendText(chatId, "Entrada no válida. Por favor, ingresa un número para tu edad.");
        }
    }

    public void startQuestionnaire(long chatId) {
        sendText(chatId, "Iniciando cuestionario...");
        sendText(chatId, "Sección 4, Pregunta 2: Por favor, ingresa tu edad:");
    }

    public void handleUser(long chatId, String email) {
        if (isUserRegistered(email)) {
            startQuestionnaire(chatId);
        } else {
            registerUser(email, chatId);
        }
    }

    public boolean isUserRegistered(String email) {
        return true; // Simulación de verificación en la base de datos
    }

    public void registerUser(String email, long chatId) {
        sendText(chatId, "Por favor, regístrese con su correo electrónico: ");
    }

    public void sendText(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}