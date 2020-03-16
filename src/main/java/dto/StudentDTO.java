package dto;

import com.bestudios.kampusellapi.entity.Product;
import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class StudentDTO {
    Long id;
    String username;
}
