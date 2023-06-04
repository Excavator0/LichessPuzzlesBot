package org.chesspuzzlesbot.bot;

import org.chesspuzzlesbot.dao.PuzzleDao;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class ChessPuzzlesBot extends TelegramLongPollingBot {
    PuzzleDao puzzleDao = new PuzzleDao();
    int randomId = 0;

    boolean puzzleSent = false;



    static final String bot_name = "LichessPuzzlesBot";
    static final String bot_token = "6173108352:AAFjm9t9b_yhr0tSna4O_sz4KeH0QBgxEM4";

    public static void main(String[] args) throws TelegramApiException {
        ChessPuzzlesBot bot = new ChessPuzzlesBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        if (message.hasText()) {
            String text = message.getText();
            switch (text) {
                case "/new_puzzle" -> {
                    if (puzzleSent) {
                        execute(
                            SendMessage.builder()
                                    .chatId(message.getChatId().toString())
                                    .text("Solve the puzzle first!")
                                    .build());
                    } else {
                        execute(
                                SendPhoto.builder()
                                        .photo(new InputFile(getRandomLichessUrl()))
                                        .chatId(message.getChatId().toString())
                                        .build());
                        puzzleSent = true;
                    }

                }
                default -> {
                    if (puzzleSent) {
                        execute(
                                SendMessage.builder()
                                        .chatId(message.getChatId().toString())
                                        .text(checkAnswer(text))
                                        .build());
                        puzzleSent = false;
                    }
                }
            }
        }
    }

    private String getRandomLichessUrl() {
        randomId = (int) ((Math.random() * 9) + 1);
        return "lichess.org/training/export/gif/thumbnail/" + puzzleDao.getPuzzleLichessIdById(randomId) + ".gif";
    }

    private String checkAnswer(String answer) {
        if (answer.equals(puzzleDao.getSolutionById(randomId))) {
            return "Correct";
        } else {
            return "Incorrect";
        }
    }


    @Override
    public String getBotUsername() {
        return bot_name;
    }

    @Override
    public String getBotToken() {
        return bot_token;
    }
}