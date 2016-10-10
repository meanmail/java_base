package meanmail.stepic.java.tests;

import java.util.*;
import java.util.function.Consumer;

/**
 * Для тестирования запускать с опцией VM -ea, иначе может получиться ложно-положительный результат,
 * т.к. assert будут игнорироваться
 * <p>
 * Например, в IDEA: Edit Configuration > VM options
 */
class MailSystem {

    /**
     * Проверочный метод, не редактируйте его
     *
     * Приложенние завершенное без исключений - признак того, что тест
     * пройден успешно (при условии что вы запускаете с опцией VM -ea)
     *
     * Если получаете исключение Exception in thread "main" java.lang.NullPointerException
     * значит где-то ошиблись, срабатывает какой-то assert, найти где именно ошибка не сложно,
     * java укажет на номер строки.
     *
     * @param args
     */
    public static void main(String[] args) {

        // Random variables
        String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        // Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

        // Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ): "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ): "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";


        // Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

        // Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
    }

    public interface Message{

    }

    public static class MailMessage<T> implements Message{

        public MailMessage(String from, String to, String content) {
        }

        public String getTo() {
            return null;
        }

        public String getFrom() {
            return null;
        }

        public String getContent() {
            return null;
        }

        // Реализуйте этот класс
    }

    public static class Salary implements Message{

        public Salary(String from, String to, int salary) {
        }

        public String getTo() {
            return null;
        }

        // Реализуйте этот класс
    }

    public static class MailService<T> implements Consumer<Message> {

        @Override
        public void accept(Message mailMessage) {

        }

        public Map<String,List<T>> getMailBox() {
            return null;
        }

        // Реализуйте этот класс
    }
}