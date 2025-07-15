package fr.capco.bank.infrastructure.repository;

import fr.capco.bank.infrastructure.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {}