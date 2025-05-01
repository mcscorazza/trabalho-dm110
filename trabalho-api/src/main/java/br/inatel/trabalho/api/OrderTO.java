package br.inatel.trabalho.api;

import lombok.*;

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
