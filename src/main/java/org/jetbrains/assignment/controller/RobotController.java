package org.jetbrains.assignment.controller;

import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.dto.Move;
import org.jetbrains.assignment.dto.Position;
import org.jetbrains.assignment.model.Location;
import org.jetbrains.assignment.repository.LocationRepository;
import org.jetbrains.assignment.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(path="/")
public class RobotController {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    MoveRepository moveRepository;

    void saveLocations(List<Position> locations) {
        if (locationRepository!= null) locations.forEach(p -> locationRepository.save(new Location(p.x(), p.y())));
    }
    
    void saveMoves(List<Move> moves) {
        if (moveRepository != null) moves.forEach(m -> moveRepository.save(new org.jetbrains.assignment.model.Move(m.direction(), m.steps())));
    }
    @PostMapping(path = "locations")
    public List<Position> locations(@RequestBody List<Move> moves) {
        saveMoves(moves);;
        Position pos = new Position(0,0);
        List<Position> positions = new ArrayList<>(moves.size() + 1);
        positions.add(pos);
        for(Move m:moves) positions.add(pos = pos.move(m));
        saveLocations(positions);
        return positions;
    }
    
    @PostMapping(path = "moves")
    public List<Move> moves(@RequestBody List<Position> locations) {
        saveLocations(locations);
        Iterator<Position> it = locations.iterator();
        Position lastPos = it.next();
        List<Move> moves = new ArrayList<>(locations.size()-1);
        while (it.hasNext()) {
            Position pos = it.next();
            int dx = pos.x() - lastPos.x();
            if (dx != 0) moves.add(new Move(dx > 0 ? Direction.EAST : Direction.WEST, Math.abs(dx)));
            int dy = pos.y() - lastPos.y();
            if (dy != 0) moves.add(new Move(dy > 0 ? Direction.NORTH : Direction.SOUTH, Math.abs(dy)));
            lastPos = pos;
        }
        saveMoves(moves);
        return moves;
    }
}
