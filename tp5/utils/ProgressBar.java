package utils;

public class ProgressBar {
    int max;
    int iter;
    int barSize = 20;
    int lastPercent = 0;
    String lastBar;
    String prefix;

    public ProgressBar(int max, String prefix) {
        this.max = max;
        this.iter = 0;
        this.prefix = prefix;

        this.lastBar = format();
    }

    public void iter() {
        this.iter++;
    }

    public void show() {
        if (lastPercent != percent()) {
            lastBar = format();
        }
        System.err.print(lastBar);
    }

    private int percent() {
        return 100 * iter / max;
    }

    private String format() {
        String percents = String.format("%3d", 100 * iter / max);
        String bar = "|";

        int size = barSize * iter / max;
        
        for (int i = 0; i < size - 1; i++) {
            bar += "=";
        }

        if (size == barSize) {
            bar += "=";
        } else {
            bar += ">";
        }

        for (int i = size; i < this.barSize; i++) {
            bar += " ";
        }

        bar = this.prefix + " " +  percents + "% " + bar + "|";

        if (iter == max) {
            bar += "\n";
        } else {
            bar += "\r";
        }

        return bar;
    }
}
