package br.inatel.trabalho.ejb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @Column(length = 10)
    private String orderCode;

    private String productCode;
    private String cpf;
    private float amount;

    @Column(length = 15)
    private String dateOrder;

    @Column(length = 10)
    private String orderValue;

    public String getOrderCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
