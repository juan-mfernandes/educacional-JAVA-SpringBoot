package br.grupointegrado.educacional.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaRequestDTO(Integer idDisciplina, Integer idMatricula, BigDecimal nota, LocalDate dataLancamento) {
}
