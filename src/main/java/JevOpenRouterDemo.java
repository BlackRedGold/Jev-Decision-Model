import io.github.gudcks0305.jev.NoulQuestion;
import io.github.gudcks0305.jev.openrouter.OpenRouterJevClient;

public final class JevOpenRouterDemo {
    public static void main(String[] args) {
        NoulQuestion refundQuestion =
                NoulQuestion.of("refund", "Is the customer requesting a refund?");

        try (OpenRouterJevClient client = OpenRouterJevClient.builder().build()) {
            var result = client.evaluate(
                    "Please refund the duplicate charge from yesterday.",
                    //"The sun is shining",
                    //"I broke my leg.",
                    //"I broke my leg and cant take the holiday trip I booked.",
                    refundQuestion
            );

            double probability = result.answer(refundQuestion).probability();

            System.out.printf("Refund probability: %.2f%n", probability);

            if (probability >= 0.5) {
                System.out.println("Create a refund-review ticket.");
            } else {
                System.out.println("Send to normal support queue.");
            }
        }
    }
}