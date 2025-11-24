package theater;

public class PastoralCalculator extends AbstractPerformanceCalculator {
    public PastoralCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() {
        return 0;
    }
}
