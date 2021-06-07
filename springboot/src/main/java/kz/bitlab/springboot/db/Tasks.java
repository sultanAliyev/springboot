package kz.bitlab.springboot.db;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    private Long id;
    private String name;
    private String date;
    private String completed;

}
