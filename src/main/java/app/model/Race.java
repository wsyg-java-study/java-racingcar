package app.model;

import app.logic.RaceController;
import app.logic.Receiver;
import app.logic.exception.InputCarNameException;

public class Race {
    RacingCar[] racingCars;
    int raceCount;

    public void setRace() {
        Receiver receiver = new Receiver();
        String[] carNames = receiver.askCarNames();
        try {
            this.racingCars = buildRacingCars(carNames);
            this.raceCount = receiver.askRaceCount();
        } catch(InputCarNameException e) {
            System.err.println(e.getMessage());
            setRace();
        }
    }

    private RacingCar[] buildRacingCars(String[] carNames) {
        RacingCar[] racingCars = new RacingCar[carNames.length];
        for (int i=0; i<carNames.length; i++) {
            racingCars[i] = new RacingCar(carNames[i]);
        }
        return racingCars;
    }

    public void startRace() {
        System.out.println("\n실행 결과");
        RaceController raceController = new RaceController();
        raceController.printStatus(this.racingCars);
        for (int i=0; i<raceCount; i++) {
            raceController.proceedRace(this.racingCars);
            raceController.printStatus(this.racingCars);
        }
    }

    public void finishRace() {
    }
}
