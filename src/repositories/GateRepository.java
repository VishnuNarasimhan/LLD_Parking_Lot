package repositories;

import models.Gate;

import java.util.HashMap;
import java.util.Optional;

public class GateRepository {
    // Gate Table
    private HashMap<Long, Gate> gates = new HashMap<>();

    // CURD operations on Gate table.
    public Optional<Gate> findByGateId(Long id) {
        return Optional.of(gates.get(id));
    }
}
