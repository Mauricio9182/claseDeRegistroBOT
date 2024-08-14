package umg.principal.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bot extends TelegramLongPollingBot {

    private static final double EURO_TO_QUETZAL_RATE = 8.47; // Tipo de cambio de Euros a Quetzales
    private static final long[] CHAT_IDS = { 6082604734L, 2085251453L, 6421826691L, 1534824490L, 2064783549L};
    @Override
    public String getBotUsername() {
        return "Mau9182bot";
    }

    @Override
    public String getBotToken() {
        return "7280178480:AAGHI6raHgLXUnJCwXwk5fC0ZPyfcdzq0vk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Asegurarse de que el mensaje existe y tiene texto.
        if (update.hasMessage() && update.getMessage().hasText()) {
            String nombre = update.getMessage().getFrom().getFirstName();
            String apellido = update.getMessage().getFrom().getLastName();
            String nickname = update.getMessage().getFrom().getUserName();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            System.out.println("Hola " + nombre + ", tu nombre es: " + nombre + " y tu apellido es: " + apellido);
            System.out.println("Enviaste: " + message_text);
            System.out.println("El chat_id del usuario es: " + chat_id);
            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM, yyyy 'a las' HH:mm");
            String formattedDateTime = now.format(formatter);

            // Responder al mensaje "/hola"
            if (message_text.toLowerCase().equals("/hola")) {
                sendText(chat_id, "Hola, " + nombre + ", hoy es " + formattedDateTime);
            } else if (message_text.toLowerCase().equals("hola")) {
                sendText(chat_id, "Qué onda pa, " + nombre + ", ¿cómo estás?");
            } else if (message_text.toLowerCase().equals("bye")) {
                sendText(chat_id, "Órale " + nombre + ", cuídate, mi amor");
            } else if (message_text.toLowerCase().equals("/info")) {
                // Información personal del usuario
                String carnet = "0905-23-14811";  // Número de carnet
                String semestre = "4to semestre";  // Semestre cursando
                String info = "Nombre: " + nombre + " " + apellido + "\n" +
                        "Carnet: " + carnet + "\n" +
                        "Semestre: " + semestre;
                sendText(chat_id, info);
            } else if (message_text.toLowerCase().equals("/progra")) {
                // Comentarios sobre la clase de programación
                String comentarios = "Me está pareciendo muy buena, algo matada, pero todo calidad. " +
                        "Quiero seguir aprendiendo de esta onda, " +
                        "espero que algún día esto me lleve comida a mi mesa 😭😭😭";
                sendText(chat_id, comentarios);
            } else if (message_text.toLowerCase().startsWith("/cambio")) {
                // Convertir Euros a Quetzales
                try {
                    double euros = Double.parseDouble(message_text.split(" ")[1]);
                    double quetzales = euros * EURO_TO_QUETZAL_RATE;
                    sendText(chat_id, euros + " Euros son " + String.format("%.2f", quetzales) + " Quetzales.");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    sendText(chat_id, "Por favor, ingresa un valor válido después del comando /cambio.");
                }
            }
            if (message_text.startsWith("/grupal ")) {
                String mensajeParaEnviar = message_text.substring(8); // Obtiene el texto después de "/grupal "

                // Enviar el mensaje a cada compañero
                for (long chatId : CHAT_IDS) {
                    sendText(chatId, mensajeParaEnviar);
                }
            }
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}






