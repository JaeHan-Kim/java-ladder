package nextstep.ladder.entity.ladder;

import java.util.Objects;

/**
 * 사다리의 층을 추상화
 */
public class Floor {

    private Point startPoint;
    private Floor nextFloor;

    protected Floor(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void next(Floor nextFloor) {
        this.nextFloor = nextFloor;
    }

    public boolean hasNext() {
        return Objects.nonNull(nextFloor);
    }

    public Floor getNextFloor() {
        return nextFloor;
    }

    public Point moveTo(int position) {

        Point current;

        for (current = startPoint; current.hasNext(); current = current.getNext()) {
            if (current.isOnPositionOf(position)) {
                return current;
            }
        }

        if (current.isOnPositionOf(position)) {
            return current;
        }

        throw new IndexOutOfBoundsException("해당 위치로 이동할 수 없습니다.");
    }


}