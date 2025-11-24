package theater;

public abstract class AbstractPerformanceCalculator {
    protected final Performance performance;
    protected final Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public abstract int amountFor();

    public int volumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }

    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {

        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            default:
                throw new RuntimeException("unknown type: " + play.getType());
        }
    }
}
