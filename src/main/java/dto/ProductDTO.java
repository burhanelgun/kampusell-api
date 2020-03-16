package dto;

import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.entity.Student;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private double price;

    private StudentDTO student;

    private CategoryDTO category;

    private List<String> imagePaths;
}
