package racingcar.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCarGame {

        private final String[] carNames;
        private final List<Car> cars;
        private int round;

        public RacingCarGame(int round, String[] carNames) {
                this.round = round;
                this.carNames = carNames;
                this.cars = joinCars();
        }

        public boolean isOngoing(){
                return --round != -1;
        }

        public void moveOneRound() {
                cars.forEach(Car::movedForwardIfCan);
        }

        public List<Car> findWinners() {
                int maxPosition = getMaxPosition();
                return cars.stream().filter(car -> car.currentPosition().isAtPosition(maxPosition)).collect(Collectors.toList());
        }

        private int getMaxPosition(){
                int maxPosition = 0;
                for (Car car : cars) {
                        maxPosition = car.currentPosition().max(maxPosition);
                }
                return maxPosition;
        }

        private List<Car> joinCars() {
                return Arrays.stream(carNames).map(Car::new).collect(Collectors.toList());
        }

        public List<Car> cars(){
                return this.cars;
        }

}