package antonio.crud.core.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3 , max = 80)
    @Column(length = 80, nullable = false)
    private String nome;

    @Size(min = 8 , max = 12)
    @Column(length = 12)
    private String telefone;
    @CPF
    @Column(length = 11)
    private String cpf;

}
