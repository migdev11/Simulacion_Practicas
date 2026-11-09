package controller;

import model.LCGModel;
import view.LCGView;

public class LCGController {
    private LCGModel model;
    private LCGView view;

    public LCGController() {
        this.model = new LCGModel();
        this.view = new LCGView();
    }

    public LCGController(LCGModel model, LCGView view) {
        this.model = model;
        this.view = view;
    }

    public long[] runSimulation(long seed, int n) {
        model.reset(seed);
        return model.generate(n);
    }

    public long[] changeSeedAndSample(long newSeed, int sampleSize) {
        model.reset(newSeed);
        return model.generate(sampleSize);
    }

    public LCGView getView() {
        return view;
    }
}
