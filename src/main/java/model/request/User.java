package model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String firstname;
    private String lastname;
    private String contact;
    private String password;
    private String dob;
    private String gender;
    private String day;
    private String month;
    private String year;
}
