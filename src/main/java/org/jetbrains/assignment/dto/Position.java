package org.jetbrains.assignment.dto;

public record Position(int x, int y) {
    public Position move(Move m) {
        int dx = 0, dy = 0;
        switch (m.direction()) {
            case EAST -> {
                dx = m.steps();
            }
            case NORTH -> {
                dy = m.steps();
            }
            case WEST -> {
                dx = -m.steps();
            }
            case SOUTH -> {
                dy = -m.steps();
            }
        }
        return new Position(x + dx, y + dy);
    }
}
