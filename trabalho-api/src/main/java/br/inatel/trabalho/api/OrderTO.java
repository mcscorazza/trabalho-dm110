package br.inatel.trabalho.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTO {
    private String orderCode;
    private String productCode;
    private String cpf;
    private float amount;
    private String dateOrder;
    private String orderValue;
}
