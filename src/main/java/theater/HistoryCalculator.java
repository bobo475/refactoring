package theater;

public class HistoryCalculator extends AbstractPerformanceCalculator {
    public HistoryCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() {
        return 0;
    }
}
