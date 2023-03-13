package shop.mtcoding.springetc2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor // 디폴트 생성자 없음
@Setter
@Getter
public class User {
    private Integer id;
    private String username;
    private String password;
    private String tel;
}
