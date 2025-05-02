package br.inatel.trabalho.ejb.service;

import java.time.LocalDateTime;

import jakarta.ejb.Local;

@Local
public interface AuditLocal {
  void record(String code, String operation, LocalDateTime timestamp);
}
