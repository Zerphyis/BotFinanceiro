    package dev.Zerphyis.botFinanceiro.application.service;

    import dev.Zerphyis.botFinanceiro.application.chat.ChatRequest;
    import dev.Zerphyis.botFinanceiro.application.chat.ChatResponse;
    import dev.Zerphyis.botFinanceiro.model.expense.Expense;
    import dev.Zerphyis.botFinanceiro.model.repositorys.ExpenseRepository;
    import dev.Zerphyis.botFinanceiro.model.repositorys.UserRepository;
    import dev.Zerphyis.botFinanceiro.model.user.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.time.LocalDate;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
    import java.util.stream.Collectors;

    @Service
    public class ChatService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ExpenseRepository expenseRepository;

        public ChatResponse processMessage(ChatRequest request) {
            String msg = request.message().toLowerCase();
            Optional<User> optionalUser = userRepository.findById(request.userId());

            if (optionalUser.isEmpty()) {
                return new ChatResponse("Usuário não encontrado.");
            }

            User user = optionalUser.get();

            if (msg.startsWith("custer")) {
                Pattern pattern = Pattern.compile("gastei (\\d+(\\.\\d{1,2})?) com (.+)");
                Matcher matcher = pattern.matcher(msg);

                if (matcher.find()) {
                    BigDecimal value = new BigDecimal(matcher.group(1));
                    String category = matcher.group(3).trim();

                    Expense e = new Expense();
                    e.setAmount(value);
                    e.setCategory(category);
                    e.setDate(LocalDate.now());
                    e.setUser(user);

                    expenseRepository.save(e);

                    return new ChatResponse("Anotado: R$" + value + " com '" + category + "' no dia " + LocalDate.now() + ".");
                } else {
                    return new ChatResponse("Não consegui entender o valor e categoria do gasto.");
                }

            } else if (msg.contains("saldo")) {
                BigDecimal total = expenseRepository.findByUserId(user.getId())
                        .stream()
                        .map(Expense::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal restante = user.getSalary().subtract(total);
                return new ChatResponse("Seu saldo atual é R$" + restante);

            } else if (msg.contains("fechar mês") || msg.contains("resumo")) {
                LocalDate now = LocalDate.now();
                LocalDate start = now.withDayOfMonth(1);
                LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

                List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(user.getId(), start, end);

                byte[] chartBytes = ChatGenerator.generateDailyChart(expenses);
                return new ChatResponse("Resumo do mês: veja os gastos por dia.", chartBytes);

            } else if (msg.contains("categorias") || msg.contains("histórico")) {
                List<Expense> all = expenseRepository.findByUserId(user.getId());

                Map<String, BigDecimal> porCategoria = all.stream()
                        .collect(Collectors.groupingBy(
                                Expense::getCategory,
                                Collectors.mapping(Expense::getAmount,
                                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

                StringBuilder resumo = new StringBuilder("Gastos por categoria:\n");
                porCategoria.forEach((cat, val) -> resumo.append(cat).append(": R$").append(val).append("\n"));
                return new ChatResponse(resumo.toString());

            } else {
                return new ChatResponse("Desculpe, não entendi. Você pode dizer algo como 'gastei 100 com mercado'.");
            }
        }
    }
