package step3.domain.ladder;

import step3.domain.ladder.dto.LinePointsDTO;
import step3.strategy.MakeLineStrategy;
import step3.type.DirectionType;

import java.util.List;
import java.util.Optional;

public class Line {
    public static final String ERROR_NOT_FOUND_DIRECTION = "진행방향을 찾을 수 없습니다. 현재 위치: x(%s), y(%s)";
    private final List<Boolean> points;

    private Line(List<Boolean> points) {
        this.points = points;
    }

    public static Line of(List<Boolean> points) {
        return new Line(points);
    }

    public static Line of(int countOfPerson, MakeLineStrategy strategy) {
        return new Line(strategy.create(countOfPerson));
    }

    public boolean isExistsPoint(Point targetPoint) {
        return isExistsPoint(targetPoint.getX());

    }
    public boolean isExistsPoint(int position) {
        if (isValidRangePosition(position)) {
            return false;
        }
        return Optional.of(points.get(position))
                .orElse(false);

    }

    private boolean isValidRangePosition(int position) {
        return position < 0 || position > points.size()-1;
    }

    public LinePointsDTO getPoints() {
        return new LinePointsDTO(points);
    }

    public Point nextPosition(Point sourcePoint) {
        if (existsRight(sourcePoint)) {
            return DirectionType.execute(DirectionType.RIGHT, sourcePoint) ;
        }
        if (existsLeft(sourcePoint)) {
            return DirectionType.execute(DirectionType.LEFT, sourcePoint);
        }
        if (isDown(sourcePoint)) {
            return DirectionType.execute(DirectionType.DOWN, sourcePoint);
        }
        String errorMsg = String.format(ERROR_NOT_FOUND_DIRECTION, sourcePoint.getX(), sourcePoint.getY());
        throw new IllegalArgumentException(errorMsg);
    }

    private boolean isDown(Point sourcePoint) {
        return !existsLeft(sourcePoint)&&!existsLeft(sourcePoint);
    }

    private boolean existsLeft(Point sourcePoint) {
        return isExistsPoint(sourcePoint.getX()-1);
    }

    private boolean existsRight(Point sourcePoint) {
        return isExistsPoint(sourcePoint.getX());
    }


}