package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {
    private final Invoice invoice;
    private final Map<String, Play> plays;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    @SuppressWarnings({"checkstyle:LineLength", "checkstyle:NeedBraces", "checkstyle:SuppressWarnings"})
    public String statement() {
        int totalAmount = 0;
        int volumeCredits = 0;
        final StringBuilder result = new StringBuilder("Statement for " + invoice.getCustomer() + System.lineSeparator());

        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance p : invoice.getPerformances()) {
            final Play play = plays.get(p.getPlayID());
            final AbstractPerformanceCalculator calculator =
                    AbstractPerformanceCalculator.createPerformanceCalculator(p, play);

            final int amount = calculator.amountFor();
            final int credits = calculator.volumeCredits();

            totalAmount += amount;
            volumeCredits += credits;

            result.append(String.format("  %s: %s (%s seats)%n",
                    play.getName(),
                    frmt.format(amount / Constants.PERCENT_FACTOR),
                    p.getAudience()));
        }

        result.append(String.format("Amount owed is %s%n",
                frmt.format(totalAmount / Constants.PERCENT_FACTOR)));
        result.append(String.format("You earned %s credits%n", volumeCredits));

        return result.toString();
    }
}
