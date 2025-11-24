package theater;

public class ComedyCalculator extends AbstractPerformanceCalculator {
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() {
        int result = Constants.COMEDY_BASE_AMOUNT;
        if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
            result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD);
        }
        result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
        return result;
    }

    @Override
    public int volumeCredits() {
        return super.volumeCredits()
                + performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
    }
}
