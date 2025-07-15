package fr.capco.bank.domain.port.output;

import fr.capco.bank.domain.model.Operation;

import java.util.List;

public interface OperationPersistencePort {

    void save(Operation operation);

    List<Operation> findAll();

}
