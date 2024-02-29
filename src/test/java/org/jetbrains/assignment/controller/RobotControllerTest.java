package org.jetbrains.assignment.controller;


import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.dto.Move;
import org.jetbrains.assignment.dto.Position;
import org.jetbrains.assignment.model.Location;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RobotControllerTest {
    @Test
    void testLocations() {
        List<Move> moves = List.of(new Move(Direction.EAST, 1), new Move(Direction.NORTH, 3), new Move(Direction.EAST, 3), new Move(Direction.SOUTH, 5), new Move(Direction.WEST, 2));
        List<Position> expectedLocations = List.of(new Position(0,0), new Position(1,0), new Position(1,3), new Position(4,3), new Position(4,-2), new Position(2,-2));
        List<Position> locations = new RobotController().locations(moves);
        assertEquals(expectedLocations, locations);
    }

    @Test
    void testMoves() {
        List<Position> locations = List.of(new Position(0,0), new Position(1,0), new Position(1,3), new Position(4,3), new Position(4,-2), new Position(2,-2));
        RobotController robot =  new RobotController();
        List<Move> moves = robot.moves(locations);
        List<Position> visited = robot.locations(moves);
        assertTrue(visited.containsAll(locations));
    }
    @Test
    void testMoves2() {
        List<Position> locations = List.of(new Position(0,0), new Position(4,0), new Position(2,2), new Position(0,4));
        RobotController robot =  new RobotController();
        List<Move> moves = robot.moves(locations);
        List<Position> visited = robot.locations(moves);
        assertTrue(visited.containsAll(locations));
    }
}
